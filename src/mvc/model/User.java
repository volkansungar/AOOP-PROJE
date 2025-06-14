package mvc.model;

public class User {
    private String userId;
    private String name;
    private String password;
    private boolean admin;

    public User(String name, String password, boolean admin) {
        this.userId = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
