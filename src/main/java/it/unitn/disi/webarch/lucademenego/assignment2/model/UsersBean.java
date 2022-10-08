package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;

/**
 * Java Bean containing all information about the webapp's users
 * The class exposes both all the available users and the currently active users
 */
public class UsersBean implements Serializable {
    /**
     * All the webapp's users
     */
    private Users allUsers;

    /**
     * The users currently logged in
     */
    private Users activeUsers;

    /**
     * Currently set session timeout
     */
    private Integer sessionTimeout;

    public UsersBean() {
        allUsers = new Users();
        activeUsers = new Users();
    }

    synchronized public Users getAllUsers() {
        return allUsers;
    }

    synchronized public Users getActiveUsers() {
        return activeUsers;
    }

    synchronized public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    synchronized public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    synchronized public void setAllUsers(Users allUsers) {
        this.allUsers = allUsers;
    }

    /**
     * Add a new user to the list of users
     * @param user the new user to add
     */
    synchronized public void addUser(UserBean user) {
        allUsers.add(user);
    }

    /**
     * Add a new user to the list of active users
     * @param user the new user to add
     */
    synchronized public void addActiveUser(UserBean user) {
        activeUsers.add(user);
    }

    /**
     * Remove a user from the list of active users
     * @param user the user to remove
     */
    synchronized public void removeActiveUser(UserBean user) {
        activeUsers.remove(user);
    }
}
