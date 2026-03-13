package com.tltn.identity.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "Username or Email cannot be blank")
        String usernameOrEmail,

        @NotBlank(message = "Password cannot be blank")
        String password
) {}
