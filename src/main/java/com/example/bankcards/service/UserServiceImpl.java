package com.example.bankcards.service;

import com.example.bankcards.dto.JwtAuthenticationDto;
import com.example.bankcards.dto.RefreshTokenDto;
import com.example.bankcards.dto.UserCredentialsDto;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.User;
import com.example.bankcards.mapper.UserMapper;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.security.jwt.JwtService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    Map<String, Object> claims;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder
                           ) {
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        claims = new HashMap<String, Object>();
    }

    @Override
    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        User user = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(claims, user.getUsername());
    }

    private User findByCredentials(UserCredentialsDto userCredentialsDto) {
        return null;
    }

    @Override
    public JwtAuthenticationDto refreshToken(RefreshTokenDto refreshTokenDto) throws Exception {
        String refreshToken = refreshTokenDto.getRefreshToken();
        if (refreshToken != null && jwtService.validateToken(refreshToken)) {
            User user = findByUsername(jwtService.getUsernameFromToken(refreshToken));
            return jwtService.refreshAuthToken(claims, user.getUsername(), refreshToken);
        }
        throw new  AuthenticationException("Invalid refresh token");
    }

    private User findByUsername(String usernameFromToken) throws Exception {
        return userRepository.findByUsername(usernameFromToken).orElseThrow(()->
                new Exception(String.format("User with username %s not found", usernameFromToken)));
    }

    @Override
    public UserDto getUserByUsername(String username) throws ChangeSetPersister.NotFoundException {
        return null;
    }

    @Override
    public String addUser(UserDto user) {
        User
        return "";
    }
}
