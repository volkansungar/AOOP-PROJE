package mvc.view;

public class UserView {
    Gui gui;
    public UserView(Gui gui) {
        this.gui = gui;
    }


    public void notifyUser(String message) {
        gui.currentPage.messageBox.setText(message);
    }

    public void retrieveControlPanel() {
        gui.controlPanel.renderPage();
        gui.showFrame();
    }

    public void retrieveLoginPage() {
        gui.loginPage.renderPage();
        gui.showFrame();
    }

    public void retrieveUserPanel() {
        gui.userPanel.renderPage();
        gui.showFrame();
    }

    public void retrieveRegisterPage() {
        gui.registerPage.renderPage();
        gui.showFrame();
    }
}
