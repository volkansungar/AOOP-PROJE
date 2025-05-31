package mvc.model;

import java.util.ArrayList;
import java.util.List;

public class UserModel {
    private List<User> users; // Uygulamanın verisini tutar

    public UserModel() {
        this.users = new ArrayList<>();
        users.add(new User("admin", "admin", true));
        users.add(new User("test", "test", false));
    }

    // prototype pattern KULLANILABİLİR GÖZDEN GEÇİR
    public void addUser(User user) {
        if (user != null && !user.getName().trim().isEmpty()) {
            this.users.add(user);
            System.out.println("DEBUG: User added to model: " + user.getName());
        } else {
            System.out.println("DEBUG: Cannot add empty user.");
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
}