package com.tltn.identity.application.service;

import com.tltn.identity.application.port.EmailSender;
import com.tltn.identity.application.port.PasswordHasher;
import com.tltn.identity.application.port.TokenGenerator;
import com.tltn.identity.application.usecase.LoginUseCase;
import com.tltn.identity.application.usecase.LogoutUseCase;
import com.tltn.identity.application.usecase.RegisterAccountUseCase;
import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.entities.RefreshToken;
import com.tltn.identity.domain.entities.VerificationToken;
import com.tltn.identity.domain.repositories.AccountRepository;
import com.tltn.identity.domain.repositories.RefreshTokenRepository;
import com.tltn.identity.domain.repositories.VerificationTokenRepository;
import com.tltn.identity.domain.valueobjects.Email;
import com.tltn.identity.domain.valueobjects.Password;
import com.tltn.identity.domain.valueobjects.TokenString;
import com.tltn.identity.domain.valueobjects.TokenType;
import com.tltn.identity.domain.valueobjects.Username;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class AuthenticationService implements RegisterAccountUseCase, LoginUseCase, LogoutUseCase {

    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordHasher passwordHasher;
    private final TokenGenerator tokenGenerator;
    private final EmailSender emailSender;

    public AuthenticationService(
            AccountRepository accountRepository,
            RefreshTokenRepository refreshTokenRepository,
            VerificationTokenRepository verificationTokenRepository,
            PasswordHasher passwordHasher,
            TokenGenerator tokenGenerator,
            EmailSender emailSender) {
        this.accountRepository = accountRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordHasher = passwordHasher;
        this.tokenGenerator = tokenGenerator;
        this.emailSender = emailSender;
    }

    @Override
    public RegisterResponse execute(RegisterCommand command) {
        Username username = new Username(command.username());
        Email email = new Email(command.email());

        if (accountRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (accountRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        String hashedPassword = passwordHasher.hash(command.password());
        Password password = new Password(hashedPassword);

        Account newAccount = Account.create(username, email, password);
        accountRepository.save(newAccount);

        // Generate email verification token
        String tokenStr = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.create(
                new TokenString(tokenStr),
                newAccount.getId(),
                TokenType.EMAIL_VERIFICATION,
                Instant.now().plus(24, ChronoUnit.HOURS)
        );
        verificationTokenRepository.save(verificationToken);

        emailSender.sendVerificationEmail(email.getValue(), tokenStr);

        return new RegisterResponse(newAccount.getId(), "Registration successful. Please check your email to verify.");
    }

    @Override
    public LoginResponse execute(LoginCommand command) {
        // Assume find by username only for simplicity
        Username username = new Username(command.usernameOrEmail());
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordHasher.matches(command.password(), account.getPassword().getValue())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        if (account.isDeleted()) {
            throw new IllegalStateException("Account has been deleted");
        }

        if (!account.isActive()) {
            throw new IllegalStateException("Account is not verified or active");
        }

        String accessTokenStr = tokenGenerator.generateAccessToken(account);
        String refreshTokenStr = tokenGenerator.generateRefreshToken(account);

        RefreshToken refreshToken = RefreshToken.create(
                new TokenString(refreshTokenStr),
                account.getId(),
                Instant.now().plus(7, ChronoUnit.DAYS)
        );
        refreshTokenRepository.save(refreshToken);

        return new LoginResponse(accessTokenStr, refreshTokenStr, "Login successful");
    }

    @Override
    public void execute(LogoutCommand command) {
        if (command == null || command.refreshToken() == null || command.refreshToken().isBlank()) {
            throw new IllegalArgumentException("Refresh token is required for logout");
        }
        
        TokenString tokenString = new TokenString(command.refreshToken());
        RefreshToken refreshToken = refreshTokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new IllegalArgumentException("Invalid refresh token"));
                
        refreshToken.revoke();
        refreshTokenRepository.save(refreshToken);
    }
}
