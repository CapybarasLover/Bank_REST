package com.example.bankcards.dto;

import com.example.bankcards.entity.Role;
import jakarta.persistence.*;

import java.util.Objects;

public class UserDto {
    Long id;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) && Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password) && role == userDto.role && Objects.equals(enabled, userDto.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role, enabled);
    }

    String username;
    String password;
    Role role;
    Boolean enabled;

    public UserDto(Long id, String username, String password, Role role, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}