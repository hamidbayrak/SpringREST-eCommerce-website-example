package com.example.mvcp.util;

import com.example.mvcp.entities.Users;
import com.example.mvcp.repositories.UsersRepository;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class LoginControl {

    final UsersRepository usersRepo;
    final HttpServletRequest req;
    final HttpServletResponse res;

    public LoginControl(UsersRepository usersRepo, HttpServletRequest req, HttpServletResponse res) {
        this.usersRepo = usersRepo;
        this.req = req;
        this.res = res;
    }

    public Users userControl(Users user) {
        Users usr = usersRepo.findByMailAndPassword(user.getMail(), user.getPassword());
        if (usr != null && !usr.isAdmin()) {
            req.getSession().setAttribute("user", usr);
            if (user.getRemember() != null) {
                Cookie cookie = new Cookie("user", "" + usr.getUid()); // create user cookie
                cookie.setMaxAge(60 * 60 * 24);
                res.addCookie(cookie);
            }
        } else if (usr != null && usr.isAdmin()) {
            req.getSession().setAttribute("admin", usr);
            if (user.getRemember() != null) {
                Cookie cookie = new Cookie("admin", "" + usr.getUid()); // create admin cookie
                cookie.setMaxAge(60 * 60 * 24);
                res.addCookie(cookie);
            }
        }
        return usr;
    }

    public void cookieControl() {
        if (req.getCookies() != null) {
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("user") || cookie.getName().equalsIgnoreCase("admin") ) {
                    String val = cookie.getValue();
                    int uid = Integer.parseInt(val);
                    Users user = usersRepo.findById(uid);
                    if (!user.isAdmin()) {
                        req.getSession().setAttribute("user", user);
                    } else if (user.isAdmin()){
                        req.getSession().setAttribute("admin", user);
                    }
                    break;
                }
            }
        }
    }

    public void logout() {
        //single session remove
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("admin");

        //all session remove
        req.getSession().invalidate();

        //cookie remove
        Cookie cookie1 = new Cookie("user", "");
        Cookie cookie2 = new Cookie("admin", "");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);
        res.addCookie(cookie1);
        res.addCookie(cookie2);
    }

}
