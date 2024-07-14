package com.rosivaldolucas.magalu_connect.exception;

public class MessageNotFoundException extends RuntimeException {

  private final String message;

  public MessageNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

}
