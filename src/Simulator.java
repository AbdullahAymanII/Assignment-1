import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulator {
    private int numberOfProcessors;
    private int numberOfClockCycles;
    private String path;
    private Clock[] cycles;
    private Processor[] processors;
    private SchedularPriorityQueue schedular;
    private ArrayList<Task> tasks;

    public Simulator(int numberOfProcessors, int numberOfClockCycles, String path) {
        try {
            if (numberOfProcessors < 1 || numberOfClockCycles < 1 || path == null) {
                throw new Exception("Invalid input parameters");
            }

            this.numberOfProcessors = numberOfProcessors;
            this.numberOfClockCycles = numberOfClockCycles;
            this.path = path;
            processors = new Processor[numberOfProcessors];
            cycles = new Clock[numberOfClockCycles];
            tasks=new ArrayList<Task>();

            for (int i = 0; i < numberOfClockCycles; i++) {
                cycles[i] = new Clock(i + 1);
            }

            for (int i = 0; i < numberOfProcessors; i++) {
                processors[i] = new Processor(i + 1);
            }
            loadTasksFromFile();

            this.schedular = new SchedularPriorityQueue(tasks,processors, cycles);
        } catch (Exception e) {
            System.out.println("Simulator Error111: " + e.getMessage());
            System.exit(0);
        }
    }

    private void loadTasksFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int countNumberOfTasks=0;


            String line = br.readLine();
            if (line != null) {
                int numberOfTasks= Integer.parseInt(line);

                int taskId = 1;
                while ((line = br.readLine()) != null) {
                    countNumberOfTasks++;
                    String[] parts = line.split("\\s+");
                    if (parts.length == 3) {
                        try {
                            int creationTime = Integer.parseInt(parts[0]);
                            int executionTime = Integer.parseInt(parts[1]);
                            boolean priority = Integer.parseInt(parts[2]) == 1;

                            if(creationTime<=0 || executionTime<=0 ){
                                throw new NumberFormatException();
                            }

                            tasks.add(new Task(taskId++, creationTime, executionTime, priority));
                        } catch (Exception e) {
                            System.err.println("Error parsing line: " + line);
                            System.err.println("Invalid format: " + e.getMessage());
                            System.exit(0);
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
                System.exit(0);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + path);
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void run() {
        schedular.tasksSchedule();
    }

}
