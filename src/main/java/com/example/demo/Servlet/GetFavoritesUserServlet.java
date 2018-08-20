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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetFavUser")
public class GetFavoritesUserServlet extends HttpServlet{
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name=req.getParameter("favuser");
        HttpSession session= req.getSession();
        User user= (User) session.getAttribute("user");

        if (favoriteService.checkUser(name)){
            String pass=favoriteService.getPassword(user.getUsername(), user.getPassword(),name);
            List<String> favs = favoriteService.getFavorites(name, pass);
            session.setAttribute("user", user);
            session.setAttribute("favorites", favs);
            session.setAttribute("error", null);
            resp.sendRedirect("rootUser.html");
        }else{
            session.setAttribute("user", user);
            session.setAttribute("favorites", null);
            String error = "User does not exist";
            session.setAttribute("error", error);
            resp.sendRedirect("rootUser.html");
        }
    }

}
