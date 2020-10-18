package com.global.coffeeshop.controller.dto.response;

public class AuthenticationResponseDto {
    String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
