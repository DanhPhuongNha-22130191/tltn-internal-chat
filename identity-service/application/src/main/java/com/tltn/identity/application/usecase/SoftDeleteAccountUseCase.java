package com.tltn.identity.application.usecase;

import java.util.UUID;

public interface SoftDeleteAccountUseCase {
    void execute(SoftDeleteCommand command);

    record SoftDeleteCommand(UUID accountId) {}
}
