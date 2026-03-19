package com.example.bankcards.dto;

import com.example.bankcards.entity.Role;
import lombok.Data;

import java.util.Objects;

@Data
public class UserDto {
    Long id;
    String username;
    String password;
    Role role;
    Boolean enabled;
}