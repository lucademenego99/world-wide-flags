package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class representing the list of users available in the webapp
 */
public class Users implements Serializable {
    private List<UserBean> users;

    public Users() {
        users = new ArrayList<>();
    }

    /**
     * Write the list of users in basic HTML format, with
     * just a <ul></ul> containing every user
     * @param styles custom styles to be applied, defined in the JSP
     * @return HTML format ready to be shown to the user
     */
    public String toHTML(String styles) {
        StringBuilder usersList = new StringBuilder();
        for (UserBean user : users) {
            usersList.append(user.toHTML(styles));
        }
        return "<ul class='flex flex-col items-center justify-center'>" +
                usersList +
                "</ul>";
    }

    /**
     * Add a user to the list of users
     * @param user the user to add to the list of users
     */
    synchronized public void add(UserBean user) {
        users.add(user);
    }

    /**
     * Remove a user from the list of users, based on the provided username
     * The username of each user is unique, so there cannot be inconsistencies
     * @param user user to remove
     */
    synchronized public void remove(UserBean user) {
        Optional<UserBean> toRemove = users.stream().filter(u -> {
            return Objects.equals(u.getUsername(), user.getUsername());
        }).findFirst();
        toRemove.ifPresent(userBean -> users.remove(userBean));
    }

    synchronized public List<UserBean> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "Users{" + "users=[" + users.stream().map(UserBean::toString).map(String::valueOf).collect(Collectors.joining(",")) + "]}";
    }
}
