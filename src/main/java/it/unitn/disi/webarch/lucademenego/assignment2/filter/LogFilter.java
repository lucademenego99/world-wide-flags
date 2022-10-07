package it.unitn.disi.webarch.lucademenego.assignment2.filter;

import jakarta.servlet.*;

import java.util.Date;

/**
 * Filter only used to log the request
 */
public class LogFilter implements Filter {
    public void init(FilterConfig config) {
        System.out.println("[LogFilter] Initialized");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        // Get the IP address of the client machine
        String ipAddress = request.getRemoteAddr();

        // Log the IP address and current timestamp.
        System.out.println("IP " + ipAddress + ", Time " + new Date());

        // Pass request back down the filter chain
        chain.doFilter(request,response);
    }
}
