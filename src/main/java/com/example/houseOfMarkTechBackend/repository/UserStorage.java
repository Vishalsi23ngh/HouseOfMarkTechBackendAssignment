package com.example.houseOfMarkTechBackend.repository;

import com.example.houseOfMarkTechBackend.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserStorage {
    public static final Map<String, User> users = new HashMap<>();

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}