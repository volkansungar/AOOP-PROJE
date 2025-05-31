package mvc.controller;

import mvc.model.User;
import mvc.model.UserModel;
import mvc.view.Gui;
import mvc.view.UserView;

import javax.swing.*;
import java.util.List;

public class UserController {
    private UserModel model;
    private UserView view;

    public UserController(UserModel model, UserView view) {
        this.model = model;
        this.view = view;
    }

    // Uygulamanın başlangıç akışını yöneten metot
    public void tryLogin(String name, String password) {
        User user = model.searchUser(name);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                if (user.checkAdmin()) {
                    view.retrieveControlPanel();
                } else {view.retrieveUserPanel();}
            } else {view.notifyUser("wrong password");}
        } else {view.notifyUser("no user found");}
    }

    public void tryRegister(String name, String password, String pass_repeat) {
        User user = model.searchUser(name);
        if (user != null) {
            view.notifyUser("Already existing user!");
        } else if (!password.equals(pass_repeat)) {
            view.notifyUser("Passwords do not match");
        } else {
            model.addUser(new User(name, password, false));
        }
    }

    public void logOut() {
        view.retrieveLoginPage();
    }

    public void startApplication() {
        view.retrieveLoginPage();
    }


}
