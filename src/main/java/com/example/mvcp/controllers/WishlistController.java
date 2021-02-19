package com.example.mvcp.controllers;

import com.example.mvcp.entities.Users;
import com.example.mvcp.restclient.CategoryService;
import com.example.mvcp.restclient.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WishlistController {

    final CategoryService categoryService;
    final WishlistService wishlistService;

    public WishlistController(CategoryService categoryService, WishlistService wishlistService) {
        this.categoryService = categoryService;
        this.wishlistService = wishlistService;
    }

    @GetMapping("/wishlist")
    public String wishlist(Model model, HttpServletRequest request) {
        model.addAttribute("catlist", categoryService.categoryList());
        Users usr = (Users) request.getSession().getAttribute("user");
        request.setAttribute("user_info", usr);
        model.addAttribute("wl", wishlistService.fillWishlist(usr.getUid()));
        return "wishlist";
    }

    @PostMapping("/wishDelete")
    public String wishDelete(int wid) {
        wishlistService.delFromWishlist(wid);
        return "redirect:/wishlist";
    }

    @PostMapping("/wishlistAdd")
    public String wishlistAdd(HttpServletRequest request, int pid) {
        Users usr = (Users) request.getSession().getAttribute("user");
        request.setAttribute("user_info", usr);
        if (usr != null) {
            wishlistService.addToWishlist(pid, usr.getUid());
        } else {
            return "redirect:/";
        }
        return "redirect:/wishlist";
    }
}
