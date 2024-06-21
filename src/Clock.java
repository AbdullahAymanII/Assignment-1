public class Clock {
    private int clockId;
    private final static int CYCLE_DURATION = 1;

    public Clock(int clockId) {
        try {
            if( clockId <= 0 )
                throw new Exception("Clock id must be greater than zero");
            this.clockId = clockId;
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public int getClockId() {
        return clockId;
    }
}