package com.example.dailyplanner.service;

import com.example.dailyplanner.dao.UserRepository;
import com.example.dailyplanner.entity.Product;
import com.example.dailyplanner.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent())
            user = optional.get();
        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addProductToUser(User user, Product product, int productWeight) {
        List<Product> products = user.getProducts();
        if(products == null) {
            products = new ArrayList<>();
        }
        product.setProductWeight(productWeight);
        product.setProteins(product.getProteins() / 100 * productWeight);
        product.setFats(product.getFats() / 100 * productWeight);
        product.setCarbohydrates(product.getCarbohydrates() / 100 * productWeight);
        product.setCalories(product.getCalories() / 100 * productWeight);
        products.add(product);
        saveUser(user);
    }

    @Override
    public void deleteProductFromUser(User user, Product product) {
        List<Product> products = user.getProducts();
        if(products == null) {
            products = new ArrayList<>();
        }
        products.remove(product);
        saveUser(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public boolean checkLoginAndPassword(User user) {
        for (User us : getAllUsers()) {
            if (us.getUsername().equals(user.getUsername()) && us.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }


}
