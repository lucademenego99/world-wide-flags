package it.unitn.disi.webarch.lucademenego.assignment2.model;

import java.util.Objects;

/**
 * Class exposing methods to interact with the users' information
 */
public class UserService {
    private UserService() {}

    /**
     * Perform the login
     *
     * @param username user's username
     * @param password user's password
     * @param users data source containing the information about the users
     * @return null if the user is found, otherwise the User itself
     */
    public static UserBean login(String username, String password, UsersBean users) {

        for (UserBean user : users.getAllUsers().getUsers()) {
            if (Objects.equals(user.getUsername(), username) && Objects.equals(user.getPassword(), password)) {
                // Reset the score of the user
                user.setScore(0);

                // Add the user to the active ones
                if (!users.getActiveUsers().getUsers().contains(user)) {
                    users.addActiveUser(user);
                }
                return user;
            }
        }
        return null;
    }

    /**
     * Create a new user
     * @param username user's username
     * @param password user's password
     * @param users data source containing the information about the users
     * @throws Exception the parameters are not correct or there was an error creating the user
     * @return the newly created user
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

        if (!users.getActiveUsers().getUsers().contains(user))
            users.addActiveUser(user);
        users.addUser(user);

        return user;
    }
}
