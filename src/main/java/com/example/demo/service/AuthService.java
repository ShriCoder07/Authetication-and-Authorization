package com.example.demo.service;

import com.example.demo.entity.User;

public interface AuthService {
    String login(String username, String password);
    User register(User user);
}
