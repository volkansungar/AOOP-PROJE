package Schedule;

public class PlaneFactory implements ScheduleFactory {
    @Override
    public Schedule createSchedule() {
        return new PlaneSchedule();
    }
}
