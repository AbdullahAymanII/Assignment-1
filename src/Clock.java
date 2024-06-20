public class Clock {
    private int cid;
    private final static int CYCLEDURATION=1;

    public Clock(int cid) {
        this.cid = cid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
