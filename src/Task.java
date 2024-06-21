import java.io.PrintStream;

public class Task {
    private int taskId;
    private int creationTime;
    private int executionTime;
    private boolean priority;
    private boolean finished = false;

    public Task(int taskId, int creationTime, int executionTime, boolean priority) {

        try {
            if (taskId <= 0 || creationTime <= 0 || executionTime <= 0)
                throw new Exception("Invalid Task information!");

            this.taskId = taskId;
            this.creationTime = creationTime;
            this.executionTime = executionTime;
            this.priority = priority;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public int getTaskId() {
        return taskId;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        try{
            if(creationTime<0)
                throw new Exception("Invalid creation time!");
            this.creationTime = creationTime;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {

            if ((getExecutionTime() - 1) == 0)
                setFinished(true);
            this.executionTime = executionTime;
    }

    public boolean isPriority() {
        return priority;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", creationTime=" + creationTime + ", executionTime=" + executionTime + ", priority=" + priority + ", finished=" + finished + '}';
    }
}