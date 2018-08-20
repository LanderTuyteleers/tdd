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
import java.util.List;
import java.util.TreeSet;

@WebServlet("/GetAllFav")
public class GetAllFavoritesServlet extends HttpServlet {
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        TreeSet<String> favs = favoriteService.getAllFavorites(user.getUsername(), user.getPassword());
        session.setAttribute("user", user);

        if (favs.size() > 0) {
            session.setAttribute("favorites", favs);
            session.setAttribute("error", null);
            resp.sendRedirect("rootUser.html");
        } else {
            session.setAttribute("favorites", null);
            session.setAttribute("error", "No favorites");
        }
    }
}
