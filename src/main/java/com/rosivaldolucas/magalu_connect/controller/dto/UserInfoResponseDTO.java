package com.rosivaldolucas.magalu_connect.controller.dto;

import com.rosivaldolucas.magalu_connect.entity.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserInfoResponseDTO(
        UUID id,
        String name,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

  public static UserInfoResponseDTO create(User user) {
    return new UserInfoResponseDTO(user.getId(), user.getName(), user.getUsername(), user.getCreatedAt(), user.getUpdatedAt());
  }

}
