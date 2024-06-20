public class Processor {
    private int processorId;
    private boolean finished = true;
    Task task;
    Clock clockCycle;

    public Processor(int processorId) {
        this.processorId = processorId;
    }

    public int getProcessorId() {
        return processorId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if (finished) {
            this.task = task; // set a new task
            setFinished(false);
        }
    }
    public Clock getClockCycle () {
        return clockCycle;
    }

    public void setClockCycle (Clock clockCycle){
        this.clockCycle = clockCycle;
    }

    public boolean isFinished () {
        return finished;
    }

    public void setFinished ( boolean finished){
        this.finished = finished;
    }

}
