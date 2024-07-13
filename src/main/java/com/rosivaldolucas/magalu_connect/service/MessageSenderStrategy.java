package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.Message;
import com.rosivaldolucas.magalu_connect.exception.MessageSendException;

public interface MessageSenderStrategy {

  void send(Message message) throws MessageSendException;

}
