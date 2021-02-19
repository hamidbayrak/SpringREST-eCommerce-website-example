package com.example.mvcp.util;

import com.example.mvcp.entities.Users;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig implements Filter {

    final LoginControl loginControl;
    final HttpServletRequest request;

    public SecurityConfig(LoginControl loginControl, HttpServletRequest request){
        this.loginControl = loginControl;
        this.request = request;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String url = req.getRequestURI();
        boolean permission = pagePermission(url);
        boolean admin_session_status = req.getSession().getAttribute("admin") == null;
        boolean user_session_status= req.getSession().getAttribute("user") == null;

        if (!user_session_status){
            Users usr = (Users) req.getSession().getAttribute("user");
            req.setAttribute("user_info", usr);
        }

        if (!permission) {
            if (admin_session_status) {      // Cookie Control
                loginControl.cookieControl();
                admin_session_status = req.getSession().getAttribute("admin") == null;
            }

            if (admin_session_status) {
                try {
                    res.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                    res.setHeader("Location", "/adminlogin");
                } catch (Exception e) {}
            } else {
                Users usr = (Users) req.getSession().getAttribute("admin");
                req.setAttribute("admin_info", usr);
            }
        }
        chain.doFilter(req, res);
    }

    public boolean pagePermission(String url) {
        boolean status = false;
        String[] pages = { "/", "/adminLogin", "/adminlogin", "/contact", "/account", "/cart", "/checkout", "/detail", "/index", "/product", "/wishlist", "/search"};
        String[] types = { ".css", ".js", ".jpg", ".png", ".gif", ".map", ".woff", ".woff2", ".ttf" };
        String[] restServices = {"/product/list", "/wishlist/list", "/category/list", "/cart/list", "/product/findbycid", "/product/findbypid"};

        for (String item : pages) {
            if (item.equals(url)) {
                status = true;
                break;
            }
        }

        for (String item : types) {
            if (url.endsWith(item)) {
                status = true;
                break;
            }
        }

        for (String item : restServices){
            if (item.equals(url)){
                status = true;
                break;
            }
        }
        return status;
    }
}
