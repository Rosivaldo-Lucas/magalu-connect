package com.rosivaldolucas.magalu_connect.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
