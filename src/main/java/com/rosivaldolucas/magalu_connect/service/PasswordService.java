package com.rosivaldolucas.magalu_connect.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PasswordService {

  private final PasswordEncoder passwordEncoder;

  public String encryptPassword(String password) {
    return this.passwordEncoder.encode(password);
  }

}
