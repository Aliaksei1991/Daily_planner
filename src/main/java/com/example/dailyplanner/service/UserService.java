package com.example.dailyplanner.service;

import com.example.dailyplanner.entity.Product;
import com.example.dailyplanner.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();

    public User getUser(int id);

    public void saveUser(User user);

    public void deleteUser(int id);

    public void addProductToUser(User user, Product product, int productWeight);

    public void deleteProductFromUser(User user, Product product);

    public User findUserByUsername(String username);

    public boolean checkLoginAndPassword(User user);
}
