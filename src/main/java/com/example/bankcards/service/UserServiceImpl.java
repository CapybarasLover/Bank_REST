package com.example.bankcards.service;

import com.example.bankcards.dto.JwtAuthenticationDto;
import com.example.bankcards.dto.RefreshTokenDto;
import com.example.bankcards.dto.UserCredentialsDto;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.mapper.UserMapper;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, Object> claims =  new HashMap<String, Object>();

    @Override
    public JwtAuthenticationDto singIn(UserCredentialsDto userCredentialsDto) throws AuthenticationException {
        User user = findByCredentials(userCredentialsDto);
        return jwtService.generateAuthToken(claims, user.getUsername());
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

    @Override
    public UserDto getUserByUsername(String username) throws ChangeSetPersister.NotFoundException {
        return userMapper.toDto(userRepository.findByUsername(username)
                .orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public UserDto getUserById(Long id) throws ChangeSetPersister.NotFoundException {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }

    @Override
    public String addUser(UserDto userData) {
        userData.setRole(Objects.requireNonNullElse(userData.getRole(), Role.ROLE_USER));
        userData.setEnabled(Objects.requireNonNullElse(userData.getEnabled(), true));
        User user = userMapper.toEntity(userData);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User added";
    }

    private User findByUsername(String usernameFromToken) throws Exception {
        return userRepository.findByUsername(usernameFromToken).orElseThrow(()->
                new Exception(String.format("User with username %s not found", usernameFromToken)));
    }


    private User findByCredentials(UserCredentialsDto userCredentialsDto) throws AuthenticationException{
        Optional<User> optionalUser = userRepository.findByUsername(userCredentialsDto.getUsername());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(passwordEncoder.matches(userCredentialsDto.getPassword(), user.getPassword())){
                return user;
            }

        }
        throw new AuthenticationException("Username or password is not correct");
    }
}
