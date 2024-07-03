package com.rosivaldolucas.magalu_connect.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserSignupDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @NotBlank
        @Size(min = 5, max = 50)
        String username,

        @NotBlank
        @Size(min = 8, max = 100)
        String password
) {
}
