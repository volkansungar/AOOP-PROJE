import mvc.controller.UserController;
import mvc.model.UserModel;
import mvc.view.UserView;
import mvc.view.Gui;
import mvc.model.ScheduleManager;
import javax.swing.SwingUtilities;

/**
 * Main class to run the application.
 * This class is responsible for creating the MVC components and starting the application.
 */
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure that all GUI-related tasks are
        // executed on the Event Dispatch Thread (EDT). This is the standard and safe
        // way to start a Swing application.
        SwingUtilities.invokeLater(() -> {
            // Create the main GUI window.
            Gui gui = new Gui();

            // Create the model, which holds the application's data.
            UserModel model = new UserModel();

            // Create the view, which is responsible for the UI.
            UserView view = new UserView(gui);

            ScheduleManager scheduleManager = new ScheduleManager();
            // Create the controller, which handles user input and updates the model and view.
            UserController controller = new UserController(model, scheduleManager, view);

            // Set the controller in the GUI so that UI events can be passed to it.
            gui.setController(controller);
            // *** THE CRITICAL CONNECTION STEP ***
            // Tell the VoyageManager that VoyageListPanel wants to be notified of changes.
            scheduleManager.addObserver(gui.getScheduleListPanel());
            // You would also register AdminVoyagePanel if it needs updates
            // voyageManager.addObserver(gui.getAdminVoyagePanel());

            // Start the application logic by showing the initial login page.
            controller.startApplication();

            // *** FIX: Make the main window visible after all components are initialized. ***
            gui.showFrame();
        });
    }
}
