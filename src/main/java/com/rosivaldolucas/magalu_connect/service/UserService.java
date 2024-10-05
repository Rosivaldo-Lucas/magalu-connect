package com.rosivaldolucas.magalu_connect.service;

import com.rosivaldolucas.magalu_connect.controller.dto.UserRegisterDTO;
import com.rosivaldolucas.magalu_connect.entity.User;
import com.rosivaldolucas.magalu_connect.exception.DomainException;
import com.rosivaldolucas.magalu_connect.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordService passwordService;
  private final GroupAuthorityService groupAuthorityService;

  public void create(UserRegisterDTO userRegisterDTO) {
    this.verifyInput(userRegisterDTO);

    String encryptedPassword = this.passwordService.encryptPassword(userRegisterDTO.password());

    User newUser = new User(
            userRegisterDTO.name(),
            userRegisterDTO.email(),
            userRegisterDTO.username(),
            encryptedPassword,
            Set.of(this.groupAuthorityService.getGroupAuthoritiesUser())
    );

    this.userRepository.save(newUser);
  }

  private void verifyInput(UserRegisterDTO userRegisterDTO) {
    boolean existsByEmail = this.userRepository.existsByEmail(userRegisterDTO.email());
    if (existsByEmail) {
      throw new DomainException("Email already exists");
    }

    boolean existsByUsername = this.userRepository.existsByUsername(userRegisterDTO.username());
    if (existsByUsername) {
      throw new DomainException("Username already exists");
    }

    if (userRegisterDTO.password().length() < 6) {
      throw new DomainException("Password must be at least 6 characters");
    }
  }

}
