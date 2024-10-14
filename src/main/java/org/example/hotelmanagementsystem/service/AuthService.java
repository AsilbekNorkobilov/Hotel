package org.example.hotelmanagementsystem.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.hotelmanagementsystem.dto.RegisterDto;
import org.example.hotelmanagementsystem.dto.TokenDto;


public interface AuthService {
    TokenDto login(RegisterDto loginDto);

    String register(RegisterDto reqDto);

    TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request);
}
