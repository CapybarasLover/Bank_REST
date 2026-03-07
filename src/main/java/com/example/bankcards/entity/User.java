package com.example.bankcards.entity;

import jakarta.persistence.*;

@Entity(name="users")
public class User {
    public User() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length=255, unique=true, nullable=false)
    String username;

    @Column(length=255, nullable=false)
    String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Role role;

    @Column(nullable=false)
    Boolean enabled;

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
