package it.unitn.disi.webarch.lucademenego.assignment2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Filter used to protect routes for which the user should be authenticated
 */
public class AuthFilter implements Filter {

    public void init(FilterConfig config) {
        System.out.println("[AuthFilter] Initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;

        HttpSession session = hreq.getSession(false);

        if (session == null || session.getAttribute("auth") == null || !Boolean.parseBoolean(session.getAttribute("auth").toString()) ) {
            // If the user is not logged in, redirect to the login page
            ((HttpServletResponse) response).sendRedirect(hreq.getContextPath() +  "/login");
        } else {
            // Pass request back down the filter chain
            chain.doFilter(request,response);
        }
    }
}
