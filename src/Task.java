public class Task {
    private int taskId;
    private int creationTime;
    private int executionTime;
    private boolean priority;
    private boolean finished = false;

    public Task(int taskId, int creationTime, int executionTime, boolean priority) {
        this.taskId = taskId;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(int executionTime) {
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
        return "Task{" +
                "taskId=" + taskId +
                ", creationTime=" + creationTime +
                ", executionTime=" + executionTime +
                ", priority=" + priority +
                ", finished=" + finished +
                '}';
    }
}




//public class Task {
//    private int taskId;
//    private int creationTime;
//    private int executionTime;
//    private boolean priority;
//    private boolean finished = false;
//
//    public Task(int taskId, int creationTime, int executionTime, boolean priority) {
//        try{
//            if(taskId <0)
//                throw new Exception();
//            else if (creationTime<0)
//                throw new Exception();
//            else if (executionTime<0)
//                throw new Exception();
//
//            this.taskId = taskId;
//            this.creationTime = creationTime;
//            this.executionTime = executionTime;
//            this.priority = priority;
//
//        }catch (Exception e){
//            System.out.println("Task creation failed !!!");
//        }
//    }
//
//    public int getTaskId() {
//        return taskId;
//    }
//
//    public int getCreationTime() {
//        return creationTime;
//    }
//
//    public void setCreationTime(int creationTime) {
//        this.creationTime = creationTime;
//    }
//
//    public int getExecutionTime() {
//        return executionTime;
//    }
//
//    public void setExecutionTime(int executionTime) {
//        this.executionTime = executionTime;
//    }
//
//    public boolean isPriority() {
//        return priority;
//    }
//
//    public boolean isFinished() {
//        return finished;
//    }
//
//    public void setFinished(boolean finished) {
//        this.finished = finished;
//    }
//}
