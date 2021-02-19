package com.example.mvcp.controllers;

import com.example.mvcp.restclient.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckoutController {

    final CategoryService categoryService;

    public CheckoutController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("catlist", categoryService.categoryList());
        return "checkout";
    }
}
