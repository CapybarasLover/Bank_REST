package com.example.bankcards.dto;

import java.util.Objects;

public class JwtAuthenticationDto {
    private String token;
    private String refreshToken;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JwtAuthenticationDto that = (JwtAuthenticationDto) o;
        return Objects.equals(token, that.token) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, refreshToken);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
