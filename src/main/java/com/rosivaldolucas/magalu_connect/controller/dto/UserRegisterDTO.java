package com.rosivaldolucas.magalu_connect.controller.dto;

public record UserRegisterDTO(
        String name,
        String email,
        String username,
        String password
) {
}
