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


@WebServlet("/RemoveUser")
public class RemoveUserServlet extends HttpServlet {
    @Autowired
    private FavoriteService favoriteService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String name=req.getParameter("removeuser");
        HttpSession session= req.getSession();
        User user= (User) session.getAttribute("user");

        if (favoriteService.checkUser(name)){
            favoriteService.removeUser(user.getUsername(),user.getPassword(),name);
            session.setAttribute("user", user);
            resp.sendRedirect("rootUser.html");
        }else{
            session.setAttribute("user", user);
            String error = "User does not exist";
            session.setAttribute("error", error);
            resp.sendRedirect("rootUser.html");
        }
    }
}
