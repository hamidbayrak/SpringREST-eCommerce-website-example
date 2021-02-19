package com.example.mvcp.controllers;

import com.example.mvcp.restclient.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    final CategoryService categoryService;

    public ContactController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("catlist", categoryService.categoryList());
        return "contact";
    }

}
