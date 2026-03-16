package com.example.bankcards.service;

import com.example.bankcards.dto.JwtAuthenticationDto;
import com.example.bankcards.dto.RefreshTokenDto;
import com.example.bankcards.dto.UserCredentialsDto;
import com.example.bankcards.dto.UserDto;
import org.springframework.data.crossstore.ChangeSetPersister;

import javax.naming.AuthenticationException;

public interface UserService {
    JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException;
    JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception;
    UserDto getUserByUsername(String username) throws ChangeSetPersister.NotFoundException;
    String addUser(UserDto user);
}
