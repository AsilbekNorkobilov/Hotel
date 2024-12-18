package org.example.hotelmanagementsystem.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.component.MailMessageSender;
import org.example.hotelmanagementsystem.model.reqDto.RegisterDto;
import org.example.hotelmanagementsystem.model.resDto.TokenDto;
import org.example.hotelmanagementsystem.entity.Role;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.entity.enums.RoleName;
import org.example.hotelmanagementsystem.repo.RoleRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.example.hotelmanagementsystem.security.JwtUtil;
import org.example.hotelmanagementsystem.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final MailMessageSender mailCodeSender;
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
            Role userRole = roleRepository.findRoleByRoleName(RoleName.ROLE_CUSTOMER);
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

    @Override
    public HttpEntity<?> forgotPassword(String email) {
        Optional<User> opt = userRepository.findUserByEmail(email);
        if (opt.isPresent()){
            User user = opt.get();
            Integer code=new Random().nextInt(1000,10000);
            user.setPassword(passwordEncoder.encode(code.toString()));
            userRepository.save(user);
            mailCodeSender.sendPassword(code,email);
            return ResponseEntity.ok("Check your email");
        }else {
            return ResponseEntity.ok("There is no user with this email");
        }

    }
}
