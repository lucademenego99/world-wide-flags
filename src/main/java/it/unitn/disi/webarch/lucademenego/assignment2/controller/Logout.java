package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session and redirect to log-in
        HttpSession session = request.getSession();

        UserBean user = (UserBean) session.getAttribute("user");

        session.invalidate();

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

        if (user != null)
            usersBean.removeActiveUser(user);

        response.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session and redirect to log-in
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login");
    }
}
