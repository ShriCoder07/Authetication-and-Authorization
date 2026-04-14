package com.example.demo.service.impl;

import com.example.demo.config.JwtService;
import com.example.demo.entity.User;
import com.example.demo.exception.UserAlredyREgister;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String login(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        User user = userRepository.findByUsername(username).orElseThrow(() ->new UsernameNotFoundException("Not found user"));

        System.out.print("User "+ user);

        String token = jwtService.generateToken(user);

        return token;
    }

    @Override
    public User register(User user) {


        if(userRepository.existsByUsername(user.getUsername())){
            throw new UserAlredyREgister("User already registered with username");
        }

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        userRepository.save(user1);
        return user1;
    }
}
