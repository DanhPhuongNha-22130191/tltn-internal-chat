package com.tltn.identity.application.usecase;

import java.util.UUID;

public interface DeactivateAccountUseCase {
    void execute(DeactivateCommand command);

    record DeactivateCommand(UUID accountId) {}
}
