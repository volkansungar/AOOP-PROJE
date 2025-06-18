package mvc.model;

import java.util.ArrayList;
import java.util.List;
/*
 * User Model part of the MVC architecture
 * A model is responsible for storing data
 * this class is only responsible for user data
 */
public class UserModel {
    private List<User> users;

    public UserModel() {
        this.users = new ArrayList<>();
        users.add(new User("admin", "admin", true));
        users.add(new User("test", "test", false));
    }

    public void addUser(User user) {
        if (user != null && !user.getName().trim().isEmpty()) {
            this.users.add(user);
        }
    }

    public User searchUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}