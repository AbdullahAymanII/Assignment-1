public class Clock {
    private int clockId;
    private final static int CYCLEDURATION=1;

    public Clock(int clockId) {
        this.clockId = clockId;
    }

    public int getClockId() {
        return clockId;
    }

    public void setClockId(int clockId) {
        this.clockId = clockId;
    }
}
