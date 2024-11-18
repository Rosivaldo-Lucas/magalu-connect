package com.rosivaldolucas.magalu_connect.controller;

import com.rosivaldolucas.magalu_connect.controller.dto.UserRegisterDTO;
import com.rosivaldolucas.magalu_connect.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody @Valid UserRegisterDTO userRegisterDTO) {
    this.userService.create(userRegisterDTO);

    return ResponseEntity.noContent().build();
  }

}
