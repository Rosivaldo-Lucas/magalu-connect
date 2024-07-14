package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.enums.ChannelMessageType;
import com.rosivaldolucas.magalu_connect.exception.MessageNotFoundException;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;
import com.rosivaldolucas.magalu_connect.exception.UserNotFoundException;
import com.rosivaldolucas.magalu_connect.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final UserService userService;
  private final Map<ChannelMessageType, MessageSenderStrategy> messageSenderStrategies;

  public MessageService(MessageRepository messageRepository, EmailMessageSender emailMessageSender, SmsMessageSender smsMessageSender, UserService userService) {
    this.messageRepository = messageRepository;
    this.userService = userService;
    this.messageSenderStrategies = Map.of(ChannelMessageType.EMAIL, emailMessageSender, ChannelMessageType.SMS, smsMessageSender);
  }

  public Message consult(UUID userId, UUID messageId) {
    if (this.userService.existsById(userId)) {
      throw new UserNotFoundException("User with ID " + userId + " not found");
    }

    return this.messageRepository.findByIdAndUserSenderId(messageId, userId)
            .orElseThrow(() -> new MessageNotFoundException("Message with ID " + messageId + " for user with ID " + userId + " not found"));
  }

  public void sendMessage(LocalDateTime currentDateTime) {
    List<Message> allMessagesToSend = this.messageRepository.findAllByStatusScheduledAndScheduledAtBefore(currentDateTime);

    allMessagesToSend.forEach(message -> {
      try {
        MessageSenderStrategy messageSender = messageSenderStrategies.get(message.getChannelType());

        messageSender.send(message);

        message.markAsSent();
      } catch (MessageSendException ex) {
        message.markAsFailed();
      } finally {
        this.messageRepository.save(message);
      }
    });
  }

  public void cancel(UUID userId, UUID messageId) {
    Message messageToCancel = this.consult(userId, messageId);
    messageToCancel.markAsCanceled();

    this.messageRepository.save(messageToCancel);
  }

}
