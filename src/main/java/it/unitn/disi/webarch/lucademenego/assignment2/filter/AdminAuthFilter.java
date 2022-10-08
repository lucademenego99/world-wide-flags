package it.unitn.disi.webarch.lucademenego.assignment2.filter;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Filter used to protect routes for which the user should be an administrator
 */
public class AdminAuthFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        System.out.println("[AdminAuthFilter] Initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        HttpServletResponse hres = (HttpServletResponse) response;

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (session == null || session.getAttribute("auth") == null || !Boolean.parseBoolean(session.getAttribute("auth").toString())) {
            // If the user is not logged in, redirect to the login page
            hres.sendRedirect(((HttpServletRequest) request).getContextPath() +  "/login");
        } else if (!((UserBean) session.getAttribute("user")).getIsAdmin()) {
            // If the user is not an admin, error 401
            request.setAttribute("status", "401");
            request.setAttribute("errorTitle", "Unauthorized");
            request.setAttribute("error", "You are not an administrator, you cannot access /admin pages");
            hres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        } else {
            // Pass request back down the filter chain
            chain.doFilter(request,response);
        }
    }
}
