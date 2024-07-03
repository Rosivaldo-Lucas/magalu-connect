package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.controller.dto.UserSignupDTO;
import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSignupService {

  private final UserRepository userRepository;

  public UserSignupService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void signup(UserSignupDTO userSignupDTO) {
    User newUser = User.crateWith(userSignupDTO.name(), userSignupDTO.username(), userSignupDTO.password());

    this.userRepository.save(newUser);
  }

}
