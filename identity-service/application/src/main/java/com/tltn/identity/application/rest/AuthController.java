package com.tltn.identity.application.rest;

import com.tltn.identity.application.dto.LoginRequestDto;
import com.tltn.identity.application.dto.LogoutRequestDto;
import com.tltn.identity.application.dto.RegisterRequestDto;
import com.tltn.identity.application.usecase.LoginUseCase;
import com.tltn.identity.application.usecase.LogoutUseCase;
import com.tltn.identity.application.usecase.RegisterAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final RegisterAccountUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;

    public AuthController(
            RegisterAccountUseCase registerUseCase,
            LoginUseCase loginUseCase,
            LogoutUseCase logoutUseCase) {
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterAccountUseCase.RegisterResponse> register(
            @Valid @RequestBody RegisterRequestDto request) {
            
        RegisterAccountUseCase.RegisterCommand command = new RegisterAccountUseCase.RegisterCommand(
                request.username(),
                request.email(),
                request.password()
        );
        RegisterAccountUseCase.RegisterResponse response = registerUseCase.execute(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUseCase.LoginResponse> login(
            @Valid @RequestBody LoginRequestDto request) {
            
        LoginUseCase.LoginCommand command = new LoginUseCase.LoginCommand(
                request.usernameOrEmail(),
                request.password()
        );
        LoginUseCase.LoginResponse response = loginUseCase.execute(command);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @Valid @RequestBody LogoutRequestDto request) {
            
        LogoutUseCase.LogoutCommand command = new LogoutUseCase.LogoutCommand(
                request.refreshToken()
        );
        logoutUseCase.execute(command);
        return ResponseEntity.noContent().build();
    }
}
