package mvc.model;
import Schedule.Schedule;
import mvc.patterns.Observer;
import mvc.patterns.Subject;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager implements Subject {
    private List<Observer> observers;
    private List<Schedule> schedules;

    public ScheduleManager() {
        this.observers = new ArrayList<>(); // Initialize the observer list
        this.schedules = new ArrayList<>();
    }

    // Implement Subject methods
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    // Modify methods that change the state to notify observers
    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        System.out.println("Schedule added. Notifying observers...");
        notifyObservers(); // Notify that the data has changed
    }
    public void deleteSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
        System.out.println("Schedule deleted. Notifying observers...");
        notifyObservers(); // Notify that the data has changed
    }

    public List<Schedule> getAllSchedules() {
        return this.schedules;
    }
    // ... other methods like removeVoyage would also call notifyObservers()
    public Schedule lookupSchedule(String id) {
        for (Schedule schedule : schedules) {
            if (schedule.get_id() == id) return schedule;
        }
        return null;
    }
}

