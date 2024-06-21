import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Schedular {
    private ArrayList<Task> tasks;
    private PriorityQueue<Task> priorityQueue;
    private Processor[] processors;
    private Clock[] cycles;
    private String path;
    private int numberOfTasks;

    public Schedular(String path, Processor[] processors, Clock[] cycles) {
        this.path = path;
        this.processors = processors;
        this.cycles = cycles;
        this.tasks = new ArrayList<>();
        this.priorityQueue = new PriorityQueue<>(new TaskComparator());

        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int countNumberOfTasks=0;
            String line = br.readLine();
            if (line != null) {
                numberOfTasks = Integer.parseInt(line);

                int taskId = 1;
                while ((line = br.readLine()) != null) {
                    countNumberOfTasks++;
                    String[] parts = line.split("\\s+");
                    if (parts.length == 3) {
                        try {
                            int creationTime = Integer.parseInt(parts[0]);
                            int executionTime = Integer.parseInt(parts[1]);
                            boolean priority = Integer.parseInt(parts[2]) == 1;

                            tasks.add(new Task(taskId++, creationTime, executionTime, priority));
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing line: " + line);
                            System.err.println("Invalid format: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Line format is incorrect: " + line);
                    }
                }

                try {
                    if (countNumberOfTasks != numberOfTasks)
                        throw new Exception("Invalid Number of Tasks!!");
                } catch (Exception e){
                    System.out.println(e.getMessage());
                    System.exit(0);
                }

            } else {
                System.err.println("The file is empty.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + path);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void tasksSchedule() {
        System.out.println("================================ Simulation Start ================================");
        System.out.println("Simulator Information: ");
        System.out.println("Number of Processors: "+processors.length);
        System.out.println("Number of Cycles: "+cycles.length);
        System.out.println("Number of tasks: " + numberOfTasks);
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
