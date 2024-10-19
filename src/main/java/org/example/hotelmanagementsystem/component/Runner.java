package org.example.hotelmanagementsystem.component;

import lombok.RequiredArgsConstructor;
import org.example.hotelmanagementsystem.entity.Role;
import org.example.hotelmanagementsystem.entity.User;
import org.example.hotelmanagementsystem.entity.enums.RoleName;
import org.example.hotelmanagementsystem.repo.RoleRepository;
import org.example.hotelmanagementsystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
        if (ddl.equals("create")){
            Role roleAdmin=new Role(1, RoleName.ROLE_ADMIN);
            Role roleUser=new Role(2,RoleName.ROLE_CUSTOMER);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleUser);
            User admin=User.builder()
                    .email("a@gmail.com")
                    .firstName("A")
                    .lastName("AA")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleAdmin))
                    .build();
            User user=User.builder()
                    .email("b@gmail.com")
                    .firstName("B")
                    .lastName("BB")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleUser))
                    .build();
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
