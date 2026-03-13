package com.tltn.identity.application.usecase;

public interface LoginUseCase {

    LoginResponse execute(LoginCommand command);

    record LoginCommand(String usernameOrEmail, String password) {}

    record LoginResponse(String accessToken, String refreshToken, String message) {}
}
