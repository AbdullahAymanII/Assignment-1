//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.PriorityQueue;
//import java.io.*;
//
//public class Schedular {
//    private ArrayList<Task> tasks;
//    private Processor[] processors;
//    private Clock[] cycles;
//    private String path;
//    private ArrayList<Task> completedTasks;
//    private JFrame frame;
//    private JPanel mainPanel;
//
//    public Schedular(String path, Processor[] processors, Clock[] cycles) {
//        this.path = path;
//        this.processors = processors;
//        this.cycles = cycles;
//        this.tasks = new ArrayList<>();
//        this.completedTasks = new ArrayList<>();
//
//        loadTasksFromFile();
//
//        frame = new JFrame("Processor Execution Simulator");
//        frame.setSize(800, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//        frame.add(new JScrollPane(mainPanel));
//        frame.setVisible(true);
//    }
//
//    private void loadTasksFromFile() {
//        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//            String line = br.readLine();
//            if (line != null) {
//                int numberOfTasks = Integer.parseInt(line);
//
//                int taskId = 1;
//                while ((line = br.readLine()) != null) {
//                    String[] parts = line.split("\\s+");
//                    if (parts.length == 3) {
//                        int creationTime = Integer.parseInt(parts[0]);
//                        int executionTime = Integer.parseInt(parts[1]);
//                        boolean priority = Integer.parseInt(parts[2]) == 1;
//
//                        tasks.add(new Task(taskId++, creationTime, executionTime, priority));
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void tasksSchedule() {
//        for (int currentCycle = 1; currentCycle <= cycles.length; currentCycle++) {
//            updateTasks(currentCycle);
//            updateProcessors();
//            updateGUI(currentCycle);
//
//            try {
//                Thread.sleep(1000); // Delay for 1 second
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void updateTasks(int currentCycle) {
//        for (Task task : tasks) {
//            if (task.getCreationTime() == currentCycle) {
//                for (Processor processor : processors) {
//                    if (processor.isFinished()) {
//                        processor.setTask(task);
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    private void updateProcessors() {
//        for (Processor processor : processors) {
//            Task task = processor.getTask();
//            if (task != null) {
//                task.setExecutionTime(task.getExecutionTime() - 1);
//                if (task.getExecutionTime() == 0) {
//                    processor.setFinished(true);
//                    completedTasks.add(task);
//                }
//            }
//        }
//    }
//
//    private void updateGUI(int currentCycle) {
//        CyclePanel cyclePanel = new CyclePanel(currentCycle, processors, completedTasks);
//        mainPanel.add(cyclePanel);
//        frame.revalidate();
//        frame.repaint();
//    }
//}
//
//
//






import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Schedular {
    private ArrayList<Task> tasks;
    private PriorityQueue<Task> priorityQueue;
    private Processor[] processors;
    private Clock[] cycles;
    private String path;

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
            String line = br.readLine();
            if (line != null) {
                int numberOfTasks = Integer.parseInt(line);
                System.out.println("Number of tasks: " + numberOfTasks);

                int taskId = 1;
                while ((line = br.readLine()) != null) {
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
            } else {
                System.err.println("The file is empty.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + path);
            e.printStackTrace();
        }
    }

    public void tasksSchedule() {
        for (int currentCycle = 1; currentCycle <= cycles.length; currentCycle++) {
            System.out.println("Cycle " + currentCycle);

            // Add new tasks to the priority queue
            for (Task task : tasks) {
                if (task.getCreationTime() == currentCycle) {
                    priorityQueue.add(task);
                    System.out.println("Task created: " + task);
                }
            }

            // Assign tasks to available processors
            for (Processor processor : processors) {
                if (processor.isFinished() && !priorityQueue.isEmpty()) {
                    Task task = priorityQueue.poll();
                    processor.setTask(task);
                    System.out.println("Assigned " + task + " to " + processor);
                }
            }

            // Update processor states
            for (Processor processor : processors) {
                Task task = processor.getTask();
                if (task != null) {
                    task.setExecutionTime(task.getExecutionTime() - 1);
                    if (task.getExecutionTime() == 0) {
                        processor.setFinished(true);
                        System.out.println("Task completed: " + task);
                    }
                }
            }

        }
    }

}
