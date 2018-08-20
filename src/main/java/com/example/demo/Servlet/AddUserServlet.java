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

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet{
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("newusername");
        String password = req.getParameter("newpassword");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        favoriteService.addUser(user.getUsername(),user.getPassword(),name,password);
        resp.sendRedirect("rootUser.html");
    }
}
