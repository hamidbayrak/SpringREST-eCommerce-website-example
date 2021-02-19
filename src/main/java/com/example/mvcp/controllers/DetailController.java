package com.example.mvcp.controllers;

import com.example.mvcp.restclient.CategoryService;
import com.example.mvcp.restclient.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailController {

    final ProductService productService;
    final CategoryService categoryService;

    public DetailController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/detail{tpid}")
    public String detail(Model model, @RequestParam(value = "id", required = true) String tpid) {
        model.addAttribute("catlist", categoryService.categoryList());
        int pid = Integer.parseInt(tpid);
        model.addAttribute("detail", productService.findByPid(pid));
        return "detail";
    }
}
