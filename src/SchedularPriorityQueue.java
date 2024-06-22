import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class SchedularPriorityQueue extends Schedular {
    private ArrayList<Task> tasks;
    private Processor[] processors;
    private Clock[] cycles;
    private PriorityQueue<Task> priorityQueue;

    public SchedularPriorityQueue(ArrayList<Task> tasks, Processor[] processors, Clock[] cycles) {
        this.processors = processors;
        this.cycles = cycles;
        this.tasks = tasks;
        this.priorityQueue = new PriorityQueue<>(new TaskComparator());
    }

    public void tasksSchedule() {
        System.out.println("================================ Simulation Start ================================");
        System.out.println("Simulator Information: ");
        System.out.println("Number of Processors: "+processors.length);
        System.out.println("Number of Cycles: "+cycles.length);
        System.out.println("Number of tasks: " + tasks.size());

        for (int currentCycle = 1; currentCycle <= cycles.length; currentCycle++) {
            System.out.println("================================================================================");
            System.out.println("                                  Cycle " + currentCycle);
            System.out.println("================================================================================\n");

            System.out.println("Task/Tasks created: ");
            // Add/queue Tasks arrived into PriorityQueue and Print tasks created in the current cycle
            for (Task task : tasks) {
                if (task.getCreationTime() == currentCycle) {
                    System.out.println(task);
                    priorityQueue.add(task);
                }
            }
            System.out.println();

            // Assign tasks to available processors
            for (Processor processor : processors) {
                if (processor.isFinished() && !priorityQueue.isEmpty()) {
                    Task task = priorityQueue.poll();
                    processor.setTask(task);
                }
            }
            printCurrentProcessorsInformation();
            System.out.println();

            System.out.println("Task/Tasks completed: ");
            // Execute tasks on processors and check for completion
            for (Processor processor : processors) {
                Task task = processor.getTask();
                if (task != null) {
                    task.setExecutionTime(task.getExecutionTime() - 1);
                    if (task.getExecutionTime() == 0) {
                        processor.setFinished(true);
                        System.out.println(task);
                    }
                }
            }
            System.out.println();

        }
        System.out.println();
        System.out.println("=============================== Simulation End =================================");
    }

    private void printCurrentProcessorsInformation(){
        System.out.println("Processors States: ");
        for(Processor processor:processors){
            if(processor.isFinished())
                System.out.println("Processor-"+processor.getProcessorId()+": Idle");
            else
                System.out.println("Processor-"+processor.getProcessorId()+": Task-"+processor.getTask().getTaskId()+", The Remaining Excution Time is "+processor.getTask().getExecutionTime());
        }
    }

}
