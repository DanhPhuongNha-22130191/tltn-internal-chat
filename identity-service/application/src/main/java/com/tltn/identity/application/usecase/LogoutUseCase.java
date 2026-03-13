package com.tltn.identity.application.usecase;

public interface LogoutUseCase {

    void execute(LogoutCommand command);

    record LogoutCommand(String refreshToken) {}
}
