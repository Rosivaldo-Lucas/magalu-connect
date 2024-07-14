package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.controller.dto.ScheduleMessageDTO;
import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ScheduleMessageService {

  private final MessageRepository messageRepository;
  private final UserService userService;

  public ScheduleMessageService(MessageRepository messageRepository, UserService userService) {
    this.messageRepository = messageRepository;
    this.userService = userService;
  }

  public void schedule(UUID userId, ScheduleMessageDTO scheduleMessageDTO) {
    User userSender = this.userService.findById(userId);

    Message message = Message.createWith(
            scheduleMessageDTO.message(),
            scheduleMessageDTO.channelMessageType(),
            scheduleMessageDTO.scheduledDate(),
            userSender,
            scheduleMessageDTO.recipients()
    );

    this.messageRepository.save(message);
  }

}
