package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.enums.ChannelMessageType;
import com.rosivaldolucas.magalu_connect.exception.MessageNotFoundException;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;
import com.rosivaldolucas.magalu_connect.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MessageService {

  private final MessageRepository messageRepository;
  private final Map<ChannelMessageType, MessageSenderStrategy> messageSenderStrategies;

  public MessageService(MessageRepository messageRepository, EmailMessageSender emailMessageSender, SmsMessageSender smsMessageSender) {
    this.messageRepository = messageRepository;
    this.messageSenderStrategies = Map.of(ChannelMessageType.EMAIL, emailMessageSender, ChannelMessageType.SMS, smsMessageSender);
  }

  public Message consult(UUID messageId) {
    return this.messageRepository.findById(messageId).orElseThrow(() -> new MessageNotFoundException("messageId not found"));
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

  public void cancel(UUID messageId) {
    Message messageToCancel = this.consult(messageId);
    messageToCancel.markAsCanceled();

    this.messageRepository.save(messageToCancel);
  }

}
