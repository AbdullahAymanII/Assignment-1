public class Processor {
    private int pid;
    Task task;
    Clock clockCycle;

    public Processor(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Clock getClockCycle() {
        return clockCycle;
    }

    public void setClockCycle(Clock clockCycle) {
        this.clockCycle = clockCycle;
    }
}
