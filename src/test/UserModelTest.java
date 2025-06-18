package test;

import mvc.model.User;
import mvc.model.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        userModel = new UserModel();
    }

    @Test
    void testInitialUsers() {
        // The model should be initialized with an admin and a test user.
        assertNotNull(userModel.searchUser("admin"), "Admin user should exist.");
        assertNotNull(userModel.searchUser("test"), "Test user should exist.");
        assertEquals(2, userModel.getUsers().size(), "Should have 2 initial users.");
    }

    @Test
    void testAddUser() {
        // Create a new user
        User newUser = new User("newUser", "password123", false);
        userModel.addUser(newUser);

        // Verify the user was added
        assertNotNull(userModel.searchUser("newUser"), "Newly added user should be found.");
        assertEquals(3, userModel.getUsers().size(), "User count should be 3 after adding one.");
    }

    @Test
    void testAddNullUser() {
        // Attempting to add a null user should not change the model
        userModel.addUser(null);
        assertEquals(2, userModel.getUsers().size(), "Adding a null user should not increase user count.");
    }

    @Test
    void testAddUserWithEmptyName() {
        // Attempting to add a user with an empty/whitespace name should not work
        User emptyUser = new User("  ", "password", false);
        userModel.addUser(emptyUser);
        assertEquals(2, userModel.getUsers().size(), "Adding a user with an empty name should not increase user count.");
    }

    @Test
    void testSearchExistingUser() {
        // Search for a user that is known to exist
        User foundUser = userModel.searchUser("admin");
        assertNotNull(foundUser, "Should find the admin user.");
        assertEquals("admin", foundUser.getName(), "Found user should have the correct name.");
        assertTrue(foundUser.checkAdmin(), "Admin user should have admin privileges.");
    }

    @Test
    void testSearchNonExistingUser() {
        // Search for a user that does not exist
        User notFoundUser = userModel.searchUser("nonexistent");
        assertNull(notFoundUser, "Should not find a user that does not exist.");
    }
}