package com.example.mvcp.controllers;

import com.example.mvcp.entities.Users;
import com.example.mvcp.util.LoginControl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AdminloginController {

    String error_msg = "";
    final LoginControl loginControl;

    public AdminloginController(LoginControl loginControl) {
        this.loginControl = loginControl;
    }


    @GetMapping("/adminlogin")
    public String login(Model model) {
        if (!error_msg.equals("")) {
            model.addAttribute("error_msg", error_msg);
        }
        error_msg = "";
        return "adminlogin";
    }


    @PostMapping("/adminLogin")
    public String adminlogin(Users user) {
        Users usr = loginControl.userControl(user);
        if (usr != null && usr.isAdmin()) {
            return "redirect:/dashboard";
        }
        error_msg = "Mail or Password Wrong!";
        return "redirect:/adminlogin";
    }

    @GetMapping("/adminlogout")
    public String adminlogout() {
        loginControl.logout();
        return "redirect:/adminlogin";
    }

}