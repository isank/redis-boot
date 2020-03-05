package com.example.redisboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("user")
public class User {

  @Id
  private String id;

  private String firstName;

  private String lastName;

  private List<String> skills;
}
