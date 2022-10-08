package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;

/**
 * JavaBean representing a user of the web-application
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

    public UserBean() {
    }

    synchronized public String getUsername() {
        return username;
    }

    synchronized public void setUsername(String username) {
        this.username = username;
    }

    synchronized public String getPassword() {
        return password;
    }

    synchronized public void setPassword(String password) {
        this.password = password;
    }

    synchronized public boolean getIsAdmin() {
        return isAdmin;
    }

    synchronized public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    synchronized public int getScore() {
        return score;
    }

    synchronized public void setScore(int score) {
        this.score = score;
    }

    /**
     * Write the user in basic HTML format, with
     * just a <li></li> containing info about the user
     *
     * @param styles custom styles to be applied, defined in the JSP
     * @return HTML code to embed in the page
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
