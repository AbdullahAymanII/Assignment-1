public class Task {
    private int tid;
    private int creationTime;
    private int executionTime;
    private boolean priority;

    public Task(int tid, int creationTime, int executionTime, boolean priority) {
        try{
            if(tid<0)
                throw new Exception();
            else if (creationTime<0)
                throw new Exception();
            else if (executionTime<0)
                throw new Exception();

            this.tid = tid;
            this.creationTime = creationTime;
            this.executionTime = executionTime;
            this.priority = priority;

        }catch (Exception e){
            System.out.println("Task creation failed !!!");
        }
    }

}
