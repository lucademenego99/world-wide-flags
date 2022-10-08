package it.unitn.disi.webarch.lucademenego.assignment2.controller;

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
        session.invalidate();
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
