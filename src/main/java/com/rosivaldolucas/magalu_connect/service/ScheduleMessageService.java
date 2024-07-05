package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.controller.dto.ScheduleMessageDTO;
import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.repository.MessageRepository;
import com.rosivaldolucas.magalu_connect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleMessageService {

  private final MessageRepository messageRepository;
  private final UserRepository userRepository;

  public ScheduleMessageService(MessageRepository messageRepository, UserRepository userRepository) {
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
  }

  public void schedule(ScheduleMessageDTO scheduleMessageDTO) {
    Optional<User> senderUserOpt = this.userRepository.findById(scheduleMessageDTO.senderId());

    if (senderUserOpt.isEmpty()) {
      throw new RuntimeException("Sender User not found");
    }

    Message message = Message.createWith(
            scheduleMessageDTO.message(),
            scheduleMessageDTO.channelMessageType(),
            scheduleMessageDTO.scheduledDate(),
            senderUserOpt.get(),
            scheduleMessageDTO.recipients()
    );

    this.messageRepository.save(message);
  }

}
