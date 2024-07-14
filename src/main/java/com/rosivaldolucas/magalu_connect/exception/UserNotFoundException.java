package com.rosivaldolucas.magalu_connect.exception;

public class UserNotFoundException extends RuntimeException {

  private final String message;

  public UserNotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

}
