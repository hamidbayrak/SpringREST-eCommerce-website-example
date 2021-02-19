package com.example.mvcp.controllers;

import com.example.mvcp.restclient.CategoryService;
import com.example.mvcp.restclient.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    final CategoryService categoryService;
    final ProductService productService;

    public IndexController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("catlist", categoryService.categoryList());
        model.addAttribute("menlist", productService.findByCid(1));
        model.addAttribute("womenlist", productService.findByCid(2));
        model.addAttribute("kidslist", productService.findByCid(3));
        model.addAttribute("allproduct", productService.productList()); // for quick view modal
        return "index";
    }

}
