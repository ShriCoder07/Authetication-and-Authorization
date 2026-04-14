package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        return ResponseEntity.ok(service.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody User user
    ){
        System.out.println("Name"+user.getUsername());
        return ResponseEntity.ok(service.login(user.getUsername(), user.getPassword()));
    }

}
