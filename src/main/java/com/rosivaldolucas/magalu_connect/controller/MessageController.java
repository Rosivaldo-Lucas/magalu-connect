package com.rosivaldolucas.magalu_connect.controller;

import com.rosivaldolucas.magalu_connect.controller.dto.ConsultMessageResponseDTO;
import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

  private final MessageService messageService;

  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{messageId}")
  public ResponseEntity<ConsultMessageResponseDTO> consult(@PathVariable UUID messageId) {
    Message consultedMessage = this.messageService.consult(messageId);

    ConsultMessageResponseDTO consultMessageResponseDTO = ConsultMessageResponseDTO.create(consultedMessage);

    return ResponseEntity.status(HttpStatus.OK).body(consultMessageResponseDTO);
  }

  @DeleteMapping("/{messageId}")
  public ResponseEntity<Void> cancel(@PathVariable UUID messageId) {
    this.messageService.cancel(messageId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}
