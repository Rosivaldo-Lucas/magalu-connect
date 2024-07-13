package com.rosivaldolucas.magalu_connect.exception;

public class MessageSendException extends RuntimeException {

  private final String message;

  public MessageSendException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

}
