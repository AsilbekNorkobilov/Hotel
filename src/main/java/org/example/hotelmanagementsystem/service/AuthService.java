package org.example.hotelmanagementsystem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.TokenDto;
import org.springframework.http.HttpEntity;


public interface AuthService {
    TokenDto login(RegisterDto loginDto);

    String register(RegisterDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);

    HttpEntity<?> forgotPassword(String email);
}
