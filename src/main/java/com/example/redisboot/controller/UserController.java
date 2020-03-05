package com.example.redisboot.controller;

import com.example.redisboot.dto.UserDto;
import com.example.redisboot.entity.User;
import com.example.redisboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

  private final UserRepository repository;

  @PostMapping("user")
  public ResponseEntity<Object> addUser(@RequestBody UserDto userDto) {

    final User save = repository.save(toUser(userDto));

    return ResponseEntity.ok(save);
  }

  private User toUser(final UserDto userDto) {

    final User user = new User();

    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setSkills(userDto.getSkills());

    return user;
  }
}
