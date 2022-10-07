package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class following the DAO architectural patten, exposing methods
 * to access the users saved in the storage.
 * The class is a singleton: its constructor is protected, and the servlets
 * will only get access to it by using the "getInstance" function. In this way,
 * one only instance of it will be actually used among all the requests.
 */
public class UserDAO {
    private UserDAO() {}

    /**
     * Perform the login
     *
     * @param username user's username
     * @param password user's password
     * @param users
     * @return null if the user is found, otherwise the User itself
     */
    public static UserBean login(String username, String password, UsersBean users) {

        for (UserBean user : users.getAllUsers().getUsers()) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)) {
                user.setLastAccess(new Timestamp(System.currentTimeMillis()));
                if (!users.getActiveUsers().getUsers().contains(user))
                    users.addActiveUser(user);
                return user;
            }
        }
        return null;
    }

    /**
     * Create a new user
     * @param username user's username
     * @param password user's password
     * @throws Exception the parameters are not correct or there was an error creating the user
     */
    public static UserBean signup(String username, String password, UsersBean users) throws Exception {
        if (username.isEmpty() || password.isEmpty()) {
            throw new Exception("Username and password cannot be empty");
        }

        if (username.length() > 15) {
            throw new Exception("The username can't be longer than 15 characters");
        }

        // Check if there is already a user with the specified username
        if (users.getAllUsers().getUsers().stream().anyMatch(u -> Objects.equals(u.getUsername(), username))) {
            throw new Exception("Username already taken");
        }

        // Create a new user with the specified username and password
        UserBean user = new UserBean();
        user.setUsername(username);
        user.setPassword(password);
        user.setIsAdmin(false);
        user.setScore(0);
        user.setLastAccess(new Timestamp(System.currentTimeMillis()));

        if (!users.getActiveUsers().getUsers().contains(user))
            users.addActiveUser(user);
        users.addUser(user);

        return user;
    }

    public static void setAccess(UserBean user) {
        user.setLastAccess(new Timestamp(System.currentTimeMillis()));
    }
}
