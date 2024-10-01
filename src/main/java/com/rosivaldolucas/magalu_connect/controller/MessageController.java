package com.rosivaldolucas.magalu_connect.controller;

import com.rosivaldolucas.magalu_connect.controller.dto.ConsultMessageResponseDTO;
import com.rosivaldolucas.magalu_connect.entity.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{messageId}/consult")
  public ResponseEntity<ConsultMessageResponseDTO> consult(@PathVariable UUID userId, @PathVariable UUID messageId) {
    Message consultedMessage = this.messageService.consult(userId, messageId);

    ConsultMessageResponseDTO consultMessageResponseDTO = ConsultMessageResponseDTO.create(consultedMessage);

    return ResponseEntity.status(HttpStatus.OK).body(consultMessageResponseDTO);
  }

  @DeleteMapping("/{messageId}/cancel")
  public ResponseEntity<Void> cancel(@PathVariable UUID userId, @PathVariable UUID messageId) {
    this.messageService.cancel(userId, messageId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
