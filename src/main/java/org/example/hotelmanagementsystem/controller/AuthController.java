package org.example.hotelmanagementsystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.CodeRequestDto;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.TokenResDto;
import org.example.hotelmanagementsystem.model.resDto.TokenDto;
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
        return ResponseEntity.ok(TokenResDto.builder().message("Authorization and refresh token").body(token).build());
    }


    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody RegisterDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok("Confirm "+confirmToken);
    }

    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        TokenDto token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(TokenResDto.builder().message("Authorization and refresh token").body(token).build());
    }
}


