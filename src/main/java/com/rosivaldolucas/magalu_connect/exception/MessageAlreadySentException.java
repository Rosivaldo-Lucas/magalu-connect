package com.rosivaldolucas.magalu_connect.exception;

public class MessageAlreadySentException extends RuntimeException {

  private final String message;

  public MessageAlreadySentException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

}
