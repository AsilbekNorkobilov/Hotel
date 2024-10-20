package org.example.hotelmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.model.reqDto.CodeRequestDto;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.TokenResDto;
import org.example.hotelmanagementsystem.model.resDto.TokenDto;
import org.example.hotelmanagementsystem.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/auth")
@RestController
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Authenticate user and generate JWT token",
            description = "Authenticates a user based on the provided credentials and returns a JWT token for authentication.")
            @PostMapping("login")
    public HttpEntity<?> login(@RequestBody RegisterDto reqDto){
        TokenDto token = authService.login(reqDto);
        return ResponseEntity.ok(TokenResDto.builder().message("Authorization and refresh token").body(token).build());
    }


    @Operation(summary = "Register new User",description = "Sends a verification code by email and returns a Confirm token which should be added to the Header upon confirmation")
    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody RegisterDto reqDto) {
        String confirmToken = authService.register(reqDto);
        return ResponseEntity.ok("Confirm "+confirmToken);
    }

    @Operation(summary = "Confirmation registration", description = "Add Confirm token to header and enter verification code to get JWT token")
    @PostMapping("confirm")
    public HttpEntity<?> confirmMailCode(@RequestBody CodeRequestDto codeDto, HttpServletRequest request){
        TokenDto token = authService.confirmMailCodeAndRegister(codeDto.getCode(), request);
        return ResponseEntity.ok(TokenResDto.builder().message("Authorization and refresh token").body(token).build());
    }

    @Operation(summary = "Forgot password", description = "This Endpoint will create new password and send it to your email. You Should login with this password and change it immediately")
    @PostMapping("forgot")
    public HttpEntity<?> forgotPassword(@RequestParam String email){
        return authService.forgotPassword(email);
    }
}


