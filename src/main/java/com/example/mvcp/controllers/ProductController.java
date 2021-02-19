package com.example.mvcp.controllers;

import com.example.mvcp.restclient.CategoryService;
import com.example.mvcp.restclient.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    final CategoryService categoryService;
    final ProductService productService;

    public ProductController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/product{tcid}")
    public String product(Model model, @RequestParam(value = "cid", required = false) String tcid) {
        model.addAttribute("catlist", categoryService.categoryList());
        if (tcid == null) {
            model.addAttribute("prolist", productService.productList());
        } else {
            int cid = Integer.parseInt(tcid);
            model.addAttribute("prolist", productService.findByCid(cid));
        }
        return "product";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam(value = "search", required = false) String search) {
        model.addAttribute("catlist", categoryService.categoryList());
        if (search != null) {
            //model.addAttribute("prolist", proRepo.findProductByTitle("%" + search + "%"));
        }
        return "product";
    }
}
