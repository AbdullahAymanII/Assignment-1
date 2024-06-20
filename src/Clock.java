public class Clock {
    private int clockId;
    private final static int CYCLE_DURATION = 1;

    public Clock(int clockId) {
        this.clockId = clockId;
    }

    public int getClockId() {
        return clockId;
    }
}