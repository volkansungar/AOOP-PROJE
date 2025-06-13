package Schedule;

public class BusFactory implements ScheduleFactory {
    @Override
    public Schedule createSchedule() {
        return new BusSchedule();
    }
}
