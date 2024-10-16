package org.example.hotelmanagementsystem.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.component.MailCodeSender;
import org.example.hotelmanagementsystem.dto.RegisterDto;
import org.example.hotelmanagementsystem.dto.TokenDto;
import org.example.hotelmanagementsystem.entity.Role;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.entity.enums.RoleName;
import org.example.hotelmanagementsystem.repo.RoleRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.example.hotelmanagementsystem.security.JwtUtil;
import org.example.hotelmanagementsystem.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MailCodeSender mailCodeSender;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public TokenDto login(RegisterDto loginDto) {
        var auth = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(auth);
        UserDetails userDetails=(UserDetails)authenticate.getPrincipal();
        return new TokenDto(
                "Bearer "+jwtUtil.generateToken(userDetails),
                "Bearer "+jwtUtil.generateRefreshToken(userDetails)
        );
    }

    @Override
    public String register(RegisterDto reqDto) {
        Integer code=new Random().nextInt(1000,10000);
        mailCodeSender.sendMessage(code,reqDto.getEmail());
        return jwtUtil.generateConfirmToken(reqDto,code);
    }

    @Override
    public TokenDto confirmMailCodeAndRegister(Integer code, HttpServletRequest request) {
        String confirm = request.getHeader("Confirm");
        String confirmToken=confirm.substring(8);
        Integer mailCode = jwtUtil.getMailCode(confirmToken);
        RegisterDto reqDto = jwtUtil.getReqDto(confirmToken);
        if (code.equals(mailCode)){
            Role userRole = roleRepository.findRoleByRoleName(RoleName.ROLE_USER);
            User user=User.builder()
                    .roles(List.of(userRole))
                    .email(reqDto.getEmail())
                    .firstName(reqDto.getFirstName())
                    .lastName(reqDto.getLastName())
                    .password(passwordEncoder.encode(reqDto.getPassword()))
                    .build();
            userRepository.save(user);
            return new TokenDto(
                    "Bearer "+jwtUtil.generateToken(user),
                    "Bearer "+jwtUtil.generateRefreshToken(user)
            );
        }
        throw new BadCredentialsException("Invalid code try again");
    }
}
