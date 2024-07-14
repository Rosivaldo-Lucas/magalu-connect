package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.exception.UserNotFoundException;
import com.rosivaldolucas.magalu_connect.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

  public final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User findById(UUID userId) {
    return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
  }

  public boolean existsById(UUID userId) {
    return userRepository.existsById(userId);
  }

}
