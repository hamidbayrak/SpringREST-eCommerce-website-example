package com.example.mvcp.controllers;

import com.example.mvcp.entities.Category;
import com.example.mvcp.repositories.CategoryRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AdmincategoryController {

    int cid = 0;
    final CategoryRepository catRepo;

    public AdmincategoryController(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    @GetMapping("/admincategory")
    public String dashboard(Model model) {
        model.addAttribute("catlist", catRepo.findAll());
        return "admincategory";
    }

    @PostMapping("/categoryAdd")
    public String categoryAdd(Category category) {
        if (cid != 0) {
            category.setCid(cid);
        }
        catRepo.saveAndFlush(category);
        cid = 0;
        return "redirect:/admincategory";
    }

    @GetMapping("/categoryDelete/{tcid}")
    public String categoryDelete(@PathVariable String tcid) {
        try {
            cid = Integer.parseInt(tcid);
            catRepo.deleteById(cid);
        } catch (EmptyResultDataAccessException e) {
            return "redirect:/admincategory";
        }
        return "redirect:/admincategory";
    }

    String page = "";
    @GetMapping("/categoryUpdate{tcid}")
    public String categoryUpdate(@RequestParam(value = "id", required = true) String tcid, Model model) {

        try {
            cid = Integer.parseInt(tcid);
            Optional<Category> oCategory = catRepo.findById(cid);
            oCategory.ifPresent(item -> { // not null
                model.addAttribute("update", item);
                model.addAttribute("catlist", catRepo.findAll());
                page = "admincategory";
            });

            if (!oCategory.isPresent()) { // null
                page = "redirect:/admincategory";
                return page;
            }
        } catch (NumberFormatException e) {
            page = "redirect:/adminlogin";
        }

        return page;
    }
}