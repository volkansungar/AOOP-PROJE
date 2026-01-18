import Schedule.BusFactory;
import Schedule.PlaneFactory;
import mvc.controller.Controller;
import mvc.model.ReservationManager;
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

        SwingUtilities.invokeLater(() -> {
            // Create the main GUI window. Its components are not initialized yet.
            Gui gui = new Gui();

            // Create the model, which holds the application's data.
            UserModel model = new UserModel();

            // Create the view, which is responsible for the UI.
            UserView view = new UserView(gui);

            ScheduleManager scheduleManager = new ScheduleManager();
            ReservationManager reservationManager = new ReservationManager();
            PlaneFactory planeFactory = new PlaneFactory();
            BusFactory busFactory = new BusFactory();
            // Create the controller, which handles user input and updates the model and view.
            Controller controller = new Controller(model, scheduleManager, reservationManager, view, busFactory, planeFactory);

            // Set the controller in the GUI so that UI events can be passed to it.
            gui.setController(controller);

            // Initialize the GUI components now that the controller is available.
            gui.initComponents();

            // Start the application logic by showing the initial login page.
            controller.startApplication();

            // Make the main window visible after all components are initialized.
            gui.showFrame();
        });
    }
}
