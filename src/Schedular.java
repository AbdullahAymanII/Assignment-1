import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;

public class Schedular {
    ArrayList<Task> tasks;
    PriorityQueue<Task> priorityQueue;

    Processor[] processors;
    Clock[] cycles;
    String path;

    public Schedular(String path, Processor[] processors, Clock[] cycles) {
        this.path = path;
        this.processors = processors;
        this.cycles = cycles;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            int taskId = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 3) {
                    try {
                        int creationTime = Integer.parseInt(parts[0]);
                        int executionTime = Integer.parseInt(parts[1]);
                        boolean priority = Boolean.parseBoolean(parts[2]);

                        tasks.add(new Task(taskId, creationTime, executionTime, priority));

                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line: " + line);
                        System.err.println("Invalid format: " + e.getMessage());
                    }
                } else {
                    System.err.println("Line format is incorrect: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + path);
            e.printStackTrace();
        }

    }
}
