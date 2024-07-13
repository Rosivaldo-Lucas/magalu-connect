package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;
import com.rosivaldolucas.magalu_connect.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final MessageSenderStrategy messageSender;

  public MessageService(MessageRepository messageRepository, MessageSenderStrategy messageSender) {
    this.messageRepository = messageRepository;
    this.messageSender = messageSender;
  }

  public void sendMessage(LocalDateTime currentDateTime) {
    List<Message> allMessagesToSend = this.messageRepository.findAllByStatusScheduledAndScheduledAtBefore(currentDateTime);

    allMessagesToSend.forEach(message -> {
      try {
        this.messageSender.send(message);

        message.markAsSent();
      } catch (MessageSendException ex) {
        message.markAsFailed();
      } finally {
        this.messageRepository.save(message);
      }
    });
  }

}
