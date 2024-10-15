package org.example.hotelmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.dto.CodeRequestDto;
import org.example.hotelmanagementsystem.dto.RegisterDto;
import org.example.hotelmanagementsystem.dto.ResponseDto;
import org.example.hotelmanagementsystem.dto.TokenDto;
import org.example.hotelmanagementsystem.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody RegisterDto reqDto){
        TokenDto token = authService.login(reqDto);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }


    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody RegisterDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok("Confirm "+confirmToken);
    }

    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        TokenDto token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(ResponseDto.builder().message("Authorization token").body(token).build());
    }
}

