package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // If the user is already authenticated, redirect to user/home or admin/dashboard
        if (session == null ||
                session.getAttribute("auth") == null ||
                !Boolean.parseBoolean(session.getAttribute("auth").toString()) ||
                session.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            UserBean user = (UserBean) session.getAttribute("user");
            response.sendRedirect(user.getIsAdmin() ? "admin/dashboard" : "user/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get username and password from the request
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");

        // Read the users bean
        UsersBean usersBean = (UsersBean) getServletContext().getAttribute("users");
        if (usersBean == null) {
            request.setAttribute("status", "500");
            request.setAttribute("errorTitle", "Server Error");
            request.setAttribute("error", "The servers seems in an inconsistent state. Please retry later.");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
            return;
        }

        // Try to perform the login
        UserBean user = UserService.login(username, pwd, usersBean);

        // Check the login's result
        if (user == null) {
            request.setAttribute("error", "Invalid username/password. Please try again.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(usersBean.getSessionTimeout());

            // Set the user in the session
            session.removeAttribute("user");
            session.setAttribute("user", user);
            session.setAttribute("auth", true);

            // Add the list of users to the user object if the user is an administrator
            response.sendRedirect(user.getIsAdmin() ? "admin/dashboard" : "user/home");
        }
    }
}
