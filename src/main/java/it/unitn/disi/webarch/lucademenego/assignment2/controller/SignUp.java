package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UserService;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "SignUp", value = "/signup")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("auth") == null || !Boolean.parseBoolean(session.getAttribute("auth").toString()) ) {
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
        } else {
            response.sendRedirect("user/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("password");
        String repeatedPwd = request.getParameter("repeat-password");

        // Check that the two passwords match
        if (!Objects.equals(pwd, repeatedPwd)) {
            request.setAttribute("error", "The passwords do not match");
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        try {
            // Get the users bean
            UsersBean usersBean = (UsersBean) getServletContext().getAttribute("users");
            if (usersBean == null) {
                request.setAttribute("status", "500");
                request.setAttribute("errorTitle", "Server Error");
                request.setAttribute("error", "The servers seems in an inconsistent state. Please retry later.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
                return;
            }

            UserBean user = UserService.signup(username, pwd, usersBean);

            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(usersBean.getSessionTimeout());

            // Set the user in the session
            session.removeAttribute("user");
            session.setAttribute("user", user);
            session.setAttribute("auth", true);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/signup.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("user/home");
    }
}
