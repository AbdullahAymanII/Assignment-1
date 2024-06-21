public class Processor {
    private int processorId;
    private boolean finished = true;
    private Task task;

    public Processor(int processorId) {
        try{
            if(processorId <= 0)
                throw new Exception("Invalid Processor ID");
        this.processorId = processorId;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
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


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "processorId=" + processorId +
                ", task=" + task +
                ", finished=" + finished +
                '}';
    }
}