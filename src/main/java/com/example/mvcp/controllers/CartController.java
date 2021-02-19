package com.example.mvcp.controllers;

import com.example.mvcp.entities.Users;
import com.example.mvcp.restclient.CartService;
import com.example.mvcp.restclient.CategoryService;
import com.example.mvcp.restclient.models.cartprops.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {

    final CategoryService categoryService;
    final CartService cartService;

    public CartController(CategoryService categoryService, CartService cartService) {
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request) {
        model.addAttribute("catlist", categoryService.categoryList());
        Users usr = (Users) request.getSession().getAttribute("user");
        request.setAttribute("user_info", usr);
        if (usr != null) {
            List<Result> cartPropsList = cartService.fillCart(usr.getUid());
            int total = 0;
            for (Result item : cartPropsList) {
                total = total + (Integer.parseInt(item.getPrice()) * item.getQuantity());
            }
            model.addAttribute("cartlist", cartPropsList);
            model.addAttribute("total", total);
        } else {
            return "redirect:/";
        }
        return "cart";
    }

    @PostMapping("/cartDelete")
    public String cartDelete(int cartid) {
        cartService.delFromCart(cartid);
        return "redirect:/cart";
    }

    @PostMapping("/cartAdd")
    public String cartAdd(HttpServletRequest request, int pid, int quantity) {
        Users usr = (Users) request.getSession().getAttribute("user");
        request.setAttribute("user_info", usr);
        if (usr != null) {
            cartService.addToCart(usr.getUid(), pid, quantity);
        } else {
            return "redirect:/";
        }
        return "redirect:/cart";
    }
}
