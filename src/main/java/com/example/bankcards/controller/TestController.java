package com.example.bankcards.controller;

import com.example.bankcards.dto.JwtAuthenticationDto;
import com.example.bankcards.dto.RefreshTokenDto;
import com.example.bankcards.dto.UserCredentialsDto;
import com.example.bankcards.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/auth")
public class TestController {

    private final UserService userService;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody UserCredentialsDto userCredentials){
        try{
            JwtAuthenticationDto jwtAuthenticationDto = userService.singIn(userCredentials);
            return ResponseEntity.ok(jwtAuthenticationDto);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/refresh")
    public JwtAuthenticationDto refresh(@RequestBody RefreshTokenDto refreshTokenDto) throws Exception {
        return userService.refreshToken(refreshTokenDto);
    }
}
