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
import java.util.Collection;

@WebServlet("/AddFavorite")
public class AddFavoriteServlet extends HttpServlet {
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String favorite = req.getParameter("favorite");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        favoriteService.addFavorite(user.getUsername(), user.getPassword(), favorite);
        user.setFavorites(favoriteService.getFavorites(user.getUsername(), user.getPassword()));
        session.setAttribute("user", user);


        resp.sendRedirect("normalUser.html");
    }
}
