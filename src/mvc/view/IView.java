package mvc.view;

public interface IView {
    void showMessage(String message, boolean isError);

    void showAdminPanel();

    void showLoginPanel();

    void showUserPanel();

    void showAdminSchedulePanel();

    void showScheduleListPanel(boolean fromAdmin);

    void showReservationPanel();
}
