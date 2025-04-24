package com.example.houseOfMarkTechBackend.service;

import com.example.houseOfMarkTechBackend.model.User;
import com.example.houseOfMarkTechBackend.repository.UserStorage;

public class AuthService {


    public String register(String username, String password) {
        if (UserStorage.users.containsKey(username)) return null;
        String token = UserStorage.generateToken();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setToken(token);
        UserStorage.users.put(username, user);
        return token;
    }

    public String login(String username, String password) {
        User user = UserStorage.users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user.getToken();
        }
        return null;
    }

    public User getUserByToken(String token) {
        return UserStorage.users.values().stream()
                .filter(user -> user.getToken().equals(token))
                .findFirst()
                .orElse(null);
    }
}
