package com.rosivaldolucas.magalu_connect.controller.dto;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.enums.ChannelMessageType;
import com.rosivaldolucas.magalu_connect.enums.MessageStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConsultMessageResponseDTO(
        UUID id,
        String content,
        MessageStatus status,
        ChannelMessageType channelType,
        LocalDateTime scheduledAt,
        LocalDateTime sentAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UserInfoResponseDTO sender
) {

  public static ConsultMessageResponseDTO create(Message message) {
    UserInfoResponseDTO userInfoResponseDTO = UserInfoResponseDTO.create(message.getSender());

    return new ConsultMessageResponseDTO(
            message.getId(), message.getContent(), message.getStatus(), message.getChannelType(),
            message.getScheduledAt(), message.getSentAt(), message.getCreatedAt(),
            message.getUpdatedAt(), userInfoResponseDTO
    );
  }

}
