package com.tltn.identity.application.dto;

import jakarta.validation.constraints.NotBlank;

public record LogoutRequestDto(
        @NotBlank(message = "Refresh token cannot be blank")
        String refreshToken
) {}
