package org.example.hotelmanagementsystem.security;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRepository.findUserByEmail(username);
        return opt.orElse(null);
    }
}
