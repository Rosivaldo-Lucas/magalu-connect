package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.controller.dto.UserRegisterDTO;
import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final GroupAuthorityService groupAuthorityService;

  public void create(UserRegisterDTO userRegisterDTO) {
    this.verifyInput(userRegisterDTO);

    this.groupAuthorityService.getGroupAuthoritiesUser();

    User newUser = new User(
            userRegisterDTO.name(),
            userRegisterDTO.email(),
            userRegisterDTO.username(),
            userRegisterDTO.password(),
            Set.of(this.groupAuthorityService.getGroupAuthoritiesUser())
    );

    this.userRepository.save(newUser);
  }

  private void verifyInput(UserRegisterDTO userRegisterDTO) {
    boolean existsByEmail = this.userRepository.existsByEmail(userRegisterDTO.email());
    if (existsByEmail) {
      throw new RuntimeException("Email already exists");
    }

    boolean existsByUsername = this.userRepository.existsByUsername(userRegisterDTO.username());
    if (existsByUsername) {
      throw new RuntimeException("Username already exists");
    }
  }

}
