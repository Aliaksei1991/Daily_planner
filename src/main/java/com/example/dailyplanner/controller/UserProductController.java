package com.example.dailyplanner.controller;

import com.example.dailyplanner.dao.UserRepository;
import com.example.dailyplanner.entity.Product;
import com.example.dailyplanner.entity.User;
import com.example.dailyplanner.exceptions.NoAuthoriseUserFoundException;
import com.example.dailyplanner.exceptions.NoSuchProductException;
import com.example.dailyplanner.service.ProductService;
import com.example.dailyplanner.service.SessionService;
import com.example.dailyplanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/calc")
public class UserProductController {

    private UserService userService;
    private ProductService productService;
    private SessionService sessionService;

    @Autowired
    public UserProductController(UserService userService, ProductService productService, SessionService sessionService) {
        this.userService = userService;
        this.productService = productService;
        this.sessionService = sessionService;
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("products", productService.getAllProducts());
        List<Integer> weights = new ArrayList<>();
        for (int i = 10; i <= 2000; i+=10) {
            weights.add(i);
        }
        model.addAttribute("weights", weights);
        return "calculator";
    }

    @PostMapping("/add")
    public String addProductToList(Model model, @RequestParam("itemName") String itemName,
                                   @RequestParam("productWeight") int productWeight) {
        User user = sessionService.getSession();
        if(user == null) {
            throw new NoAuthoriseUserFoundException("You have to authorise to continue");
        }
        model.addAttribute("user", user);
        Product product = productService.getProductByName(itemName);
        if(product == null) {
            throw new NoSuchProductException("Product not found, try again");
        }
        userService.addProductToUser(user, product, productWeight);
        return "day_result";
    }

    @GetMapping("/day_result")
    public String getUserCalories(Model model) {
        User user = sessionService.getSession();
        if(user == null) {
            throw new NoAuthoriseUserFoundException("You have to authorise to continue");
        }
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "day_result";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(Model model, @PathVariable("id") int id) {
        User user = sessionService.getSession();
        if(user == null) {
            throw new NoAuthoriseUserFoundException("You have to authorise to continue");
        }
        model.addAttribute("user", user);
        Product product = productService.getProduct(id);
        userService.deleteProductFromUser(user, product);
        return "redirect:/calc/day_result";
    }
}
