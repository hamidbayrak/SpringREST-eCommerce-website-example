package com.example.mvcp.controllers;

import com.example.mvcp.entities.Product;
import com.example.mvcp.repositories.CategoryRepository;
import com.example.mvcp.repositories.ProductRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AdminproductController {

    int pid = 0;
    final ProductRepository proRepo;
    final CategoryRepository catRepo;
    public AdminproductController(ProductRepository proRepo, CategoryRepository catRepo) {
        this.proRepo = proRepo;
        this.catRepo = catRepo;
    }

    @GetMapping("/adminproduct")
    public String adminproduct(Model model){
        model.addAttribute("prolist", proRepo.findAll());
        model.addAttribute("catlist", catRepo.findAll());
        return "adminproduct";
    }

    @PostMapping("/productAdd")
    public String productAdd(Product product) {
        if (pid != 0) {
            product.setPid(pid);
        }
        proRepo.saveAndFlush(product);
        pid = 0;
        return "redirect:/adminproduct";
    }

    @GetMapping("/productDelete/{tpid}")
    public String productDelete(@PathVariable String tpid){
        try {
            pid = Integer.parseInt(tpid);
            proRepo.deleteById(pid);
        } catch (EmptyResultDataAccessException e){
            return "redirect:/adminproduct";
        }
        return "redirect:/adminproduct";
    }

    String page = "";
    @GetMapping("/productUpdate{tpid}")
    public String productUpdate(@RequestParam(value = "id", required = true) String tpid, Model model){
        try {
            pid = Integer.parseInt(tpid);
            Optional<Product> oProduct = proRepo.findById(pid);
            oProduct.ifPresent(item ->{
                model.addAttribute("update",item);
                model.addAttribute("prolist",proRepo.findAll());
                model.addAttribute("catlist", catRepo.findAll());
                page = "adminproduct";
            });

            if (!oProduct.isPresent()){
                page = "redirect:/adminproduct";
                return page;
            }
        } catch (NumberFormatException e){
            page = "redirect:/adminlogin";
        }
        return page;
    }

}
