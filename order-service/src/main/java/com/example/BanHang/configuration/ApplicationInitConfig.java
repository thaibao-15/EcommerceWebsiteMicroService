package com.example.BanHang.configuration;

import com.example.BanHang.entity.User;
import com.example.BanHang.enums.Role;
import com.example.BanHang.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {
    // tao tai khoan admin khi lần đầu insert vào database
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
//                        .roles(roles)
                        .build();
                userRepository.save(user);
                log.warn("user has been created with default password, please change");
            }
        };
    }
}
