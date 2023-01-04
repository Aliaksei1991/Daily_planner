package com.example.dailyplanner.controller;

import com.example.dailyplanner.entity.Product;
import com.example.dailyplanner.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "p_index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "p_show";
    }

    @GetMapping("/new")
    public String addNewProduct(@ModelAttribute("product") Product product) {
        return "p_new";
    }

    @PostMapping("/new")
    public String createProduct(@ModelAttribute("product") @Valid Product product,
                                BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            return "p_new";
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") int id, Model model) {
        model.addAttribute("product", productService.getProduct(id));
        return "p_edit";
    }

    @PostMapping("{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("product") @Valid Product product,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "p_edit";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

}
