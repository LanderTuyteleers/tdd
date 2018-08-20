package com.example.demo.Servlet;

import com.example.demo.FavoriteService;
import com.example.demo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();


        if ("root".equals(username) && "rootpasswd".equals(password)) {
            User user = new User(username, password);
            session.setAttribute("error", null);
            session.setAttribute("user", user);
            resp.sendRedirect("rootUser.html");
        } else if (favoriteService.checkLogin(username, password)) {
            User user = new User(username, password);
            session.setAttribute("user", user);
            session.setAttribute("error", null);
            resp.sendRedirect("normalUser.html");
        } else {
            String error = "Wrong username or password";
            session.setAttribute("error", error);
            resp.sendRedirect("login.html");
        }
    }
}
