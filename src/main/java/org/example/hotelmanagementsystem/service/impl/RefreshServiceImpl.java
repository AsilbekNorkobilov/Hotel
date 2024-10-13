package org.example.hotelmanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.security.CustomUserDetailsService;
import org.example.hotelmanagementsystem.security.JwtUtil;
import org.example.hotelmanagementsystem.service.RefreshService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    public String refresh() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return "Bearer "+jwtUtil.generateToken(userDetails);
    }
}
