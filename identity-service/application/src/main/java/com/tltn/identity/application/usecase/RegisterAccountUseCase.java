package com.tltn.identity.application.usecase;

import java.util.UUID;

public interface RegisterAccountUseCase {
    
    RegisterResponse execute(RegisterCommand command);
    
    record RegisterCommand(String username, String email, String password) {}
    
    record RegisterResponse(UUID accountId, String message) {}
}
