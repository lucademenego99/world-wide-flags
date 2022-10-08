package it.unitn.disi.webarch.lucademenego.assignment2.controller;

import it.unitn.disi.webarch.lucademenego.assignment2.model.UserBean;
import it.unitn.disi.webarch.lucademenego.assignment2.model.UsersBean;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * Listen to the HttpSessions
 * When a session is destroyed, remove the corresponding user from the active users
 */
public class WWFSessionListener implements HttpSessionListener {
    /**
     * On session destroyed, remove the corresponding user from the list of active users
     * @param event fired event
     */
    @Override
    public void sessionDestroyed(final HttpSessionEvent event) {
        System.out.println("[WWFSessionListener] a session has been destroyed");
        UsersBean users = (UsersBean) event.getSession().getServletContext().getAttribute("users");
        UserBean user = (UserBean) event.getSession().getAttribute("user");
        if (users != null && user != null) {
            System.out.println("Removing " + user.getUsername() + " from active users");
            users.removeActiveUser(user);
        }
    }
}
