package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Java Bean containing all information about the webapp's users
 * A normal user will only make use of the variable "user",
 * while an administrator will also have access to "allUsers".
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

    public Users getAllUsers() {
        return allUsers;
    }

    public Users getActiveUsers() {
        ArrayList<UserBean> filteredUsers = (ArrayList<UserBean>) activeUsers.getUsers().stream().filter(u -> {
            return (new Date(u.getLastAccess().getTime()).toInstant().plusSeconds(sessionTimeout).isAfter(new Date().toInstant()));
        }).collect(Collectors.toList());

        activeUsers.setUsers(filteredUsers);

        return activeUsers;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    synchronized public void setAllUsers(Users allUsers) {
        this.allUsers = allUsers;
    }

    synchronized public void addUser(UserBean user) {
        allUsers.add(user);
    }

    synchronized public void addActiveUser(UserBean user) {
        activeUsers.add(user);
    }

    synchronized public void removeActiveUser(UserBean user) {
        activeUsers.remove(user);
    }
}
