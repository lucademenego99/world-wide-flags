package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Class representing a user
 */
public class UserBean implements Serializable {
    /**
     * The user's username
     */
    private String username;

    /**
     * The user's password
     */
    private String password;

    /**
     * Is the user an administrator?
     */
    private boolean isAdmin;

    /**
     * Score of the user
     */
    private int score;

    /**
     * Last login of the user
     */
    private Timestamp lastAccess;

    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Timestamp lastAccess) {
        this.lastAccess = lastAccess;
    }

    /**
     * Write the user in basic HTML format, with
     * just a <li></li> containing info about the user
     *
     * @param styles custom styles to be applied, defined in the JSP
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styles) {
        return "<li class='" + styles + "'>" +
                "<p>" + this.username + "</p>" +
                "<p>Score: " + this.score + "</p>" +
                "</li>";
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ",isAdmin=" + isAdmin + ",score=" + score + "}";
    }
}
