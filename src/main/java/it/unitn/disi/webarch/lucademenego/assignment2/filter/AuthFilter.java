package it.unitn.disi.webarch.lucademenego.assignment2.filter;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UserDAO;
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
            ((HttpServletResponse) response).sendRedirect(hreq.getContextPath() +  "/login");
        } else {
            UserDAO.setAccess((UserBean) session.getAttribute("user"));
            // Pass request back down the filter chain
            chain.doFilter(request,response);
        }
    }
}
