package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WWFServletContextListener implements ServletContextListener {
    private String dataSource;
    private Integer sessionTimeout;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("Initializing Application");

        // Read information about the users

        // Get various information set in the environment:
        // - admin credentials
        // - path where the file with the UsersBean can be found
        // - session timeout
        String adminUsername, adminPassword;
        UserBean admin;
        try {
            Context ctx = new InitialContext();
            adminUsername = (String) ctx.lookup("java:comp/env/AdminUsername");
            adminPassword = (String) ctx.lookup("java:comp/env/AdminPassword");
            dataSource = (String) ctx.lookup("java:comp/env/UsersPath");

            // We will need the session timeout later - now we just check if it is provided
            sessionTimeout = (Integer) ctx.lookup("java:comp/env/SessionTimeout");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        try {
            // Load the users from the data source
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(dataSource)));
            UsersBean usersBean = (UsersBean) ois.readObject();
            usersBean.setSessionTimeout(sessionTimeout);

            System.out.println("[ServletContextListener] users: " + usersBean.getAllUsers());

            // Save global information in the servlet context
            arg0.getServletContext().setAttribute("users", usersBean);

            ois.close();
        } catch(IOException e) {
            System.out.println("[ServletContextListener] IOException, no user found");

            UsersBean usersBean = new UsersBean();
            admin = new UserBean();
            admin.setUsername(adminUsername);
            admin.setPassword(adminPassword);
            admin.setIsAdmin(true);
            admin.setScore(999);
            usersBean.addUser(admin);
            usersBean.setSessionTimeout(sessionTimeout);

            arg0.getServletContext().setAttribute("users", usersBean);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("Destroying Application");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(dataSource)));

            UsersBean users = (UsersBean) arg0.getServletContext().getAttribute("users");
            System.out.println("[ServletContextListener] users: " + users.getAllUsers());
            oos.writeObject(users);
            oos.close();
        } catch (IOException e) {
            System.err.println("[ServletContextListener] IOException, could not save the UsersBean");
            throw new RuntimeException(e);
        }
    }
}
