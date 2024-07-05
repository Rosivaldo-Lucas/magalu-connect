package com.rosivaldolucas.magalu_connect.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosivaldolucas.magalu_connect.enums.ChannelMessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ScheduleMessageDTO(
        @NotNull
        UUID senderId,

        @NotNull
        Set<String> recipients,

        @NotBlank
        @Size(max = 300)
        String message,

        @NotNull
        ChannelMessageType channelMessageType,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
        LocalDateTime scheduledDate
) {
}
