package com.rosivaldolucas.magalu_connect.controller;

import com.rosivaldolucas.magalu_connect.controller.dto.UserSignupDTO;
import com.rosivaldolucas.magalu_connect.service.UserSignupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/signup")
public class UserSignupController {

  private final UserSignupService userSignupService;

  public UserSignupController(UserSignupService userSignupService) {
    this.userSignupService = userSignupService;
  }

  @PostMapping
  public ResponseEntity<?> signup(@RequestBody @Valid UserSignupDTO userSignupDTO) {
    this.userSignupService.signup(userSignupDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
