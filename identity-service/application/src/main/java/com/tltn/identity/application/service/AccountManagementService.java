package com.tltn.identity.application.service;

import com.tltn.identity.application.usecase.DeactivateAccountUseCase;
import com.tltn.identity.application.usecase.SoftDeleteAccountUseCase;
import com.tltn.identity.domain.entities.Account;
import com.tltn.identity.domain.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountManagementService implements DeactivateAccountUseCase, SoftDeleteAccountUseCase {

    private final AccountRepository accountRepository;

    public AccountManagementService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void execute(DeactivateCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        
        account.deactivate();
        accountRepository.save(account);
    }

    @Override
    public void execute(SoftDeleteCommand command) {
        Account account = accountRepository.findById(command.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        
        account.softDelete();
        accountRepository.save(account);
    }
}
