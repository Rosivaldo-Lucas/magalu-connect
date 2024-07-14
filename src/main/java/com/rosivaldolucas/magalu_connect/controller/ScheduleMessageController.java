package com.rosivaldolucas.magalu_connect.controller;

import com.rosivaldolucas.magalu_connect.controller.dto.ScheduleMessageDTO;
import com.rosivaldolucas.magalu_connect.service.ScheduleMessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/schedules")
public class ScheduleMessageController {

  private final ScheduleMessageService scheduleMessageService;

  public ScheduleMessageController(ScheduleMessageService scheduleMessageService) {
    this.scheduleMessageService = scheduleMessageService;
  }

  @PostMapping
  public ResponseEntity<?> schedule(@PathVariable UUID userId, @RequestBody @Valid ScheduleMessageDTO scheduleMessageDTO) {
    this.scheduleMessageService.schedule(userId, scheduleMessageDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
