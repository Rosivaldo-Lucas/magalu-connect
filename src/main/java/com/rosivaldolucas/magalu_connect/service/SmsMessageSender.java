package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;
import org.springframework.stereotype.Service;

@Service
public class SmsMessageSender implements MessageSenderStrategy {

  @Override
  public void send(Message message) throws MessageSendException {
    try {
      System.out.println("Sending message by SMS: " + message);

      throw new Exception("Error sending message");
    } catch (Exception ex) {
      throw new MessageSendException(ex.getMessage());
    }
  }

}
