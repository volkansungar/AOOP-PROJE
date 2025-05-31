import mvc.controller.UserController;
import mvc.model.UserModel;
import mvc.view.Gui;
import mvc.view.UserView;
public class Main {
    public static void main(String[] args) {

        // 1. Modeli oluştur
        UserModel model = new UserModel();

        Gui gui = new Gui();

        // 2. View'i oluştur
        UserView view = new UserView(gui);

        // 3. Controller'ı oluştur ve Model ile View'i ona bağla
        UserController controller = new UserController(model, view);
        gui.setController(controller);
        gui.setView(view);

        controller.startApplication();
    }
}

