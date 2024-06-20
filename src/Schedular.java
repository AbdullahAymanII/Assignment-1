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
                            boolean priority = Boolean.parseBoolean(parts[2]);

                            tasks.add(new Task(taskId, creationTime, executionTime, priority));
                            taskId++;
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
        int currentCycle = 1;
        Task[] queuedTasks = new Task[this.processors.length];
        while (currentCycle <= cycles.length) {
            updateAvailableTasks(queuedTasks);
            updateTasksCreation(currentCycle);
            fillQueuedTasks(queuedTasks);
            setProcessorsTasks(queuedTasks);
            currentCycle++;
        }

    }
    private void updateTasksCreation(int currentCycle){
        for (int i = 0; i < tasks.size(); i++) {
            if ( (tasks.get(i).getCreationTime()-currentCycle) <= 0 )
                tasks.get(i).setCreationTime(currentCycle);
            else
                tasks.get(i).setCreationTime( tasks.get(i).getCreationTime() - currentCycle );
        }
    }
    private void updateAvailableTasks(Task[] tasks){
        int processorsIndex=0;
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < this.tasks.size(); j++) {

                if ((this.tasks.get(j).getTaskId() == tasks[i].getTaskId()) && !this.tasks.get(j).isFinished()) {
                    this.tasks.get(j).setExecutionTime(this.tasks.get(j).getExecutionTime()-1);
                    if (( this.tasks.get(j).getExecutionTime() <= 0)) {
                        this.tasks.remove(j);
                        this.processors[processorsIndex].setFinished(true);
                        processorsIndex= (processorsIndex+1)%processors.length;
                    }
                }

            }
        }
    }
    private void fillQueuedTasks(Task[] queuedTasks) {

        ArrayList<Task> copyTasks = (ArrayList<Task>) tasks.clone();

        for (int i = 0; i < queuedTasks.length; i++) {
            for (int j = i+1; j < copyTasks.size(); j++) {
                if (copyTasks.get(j).getCreationTime() <= queuedTasks[i].getCreationTime()) {
                    queuedTasks[i]=copyTasks.get(j);
                    copyTasks.remove(j);
                }
            }
            if(queuedTasks[i+1].getCreationTime()<queuedTasks[i].getCreationTime()&& (i+1)!=queuedTasks.length){
                Task temp =queuedTasks[i];
                queuedTasks[i]=queuedTasks[i+1];
                queuedTasks[i+1]= temp;
            } else if (queuedTasks[i+1].isPriority()==true && queuedTasks[i].isPriority()==false && queuedTasks[i+1].getCreationTime()==queuedTasks[i].getCreationTime()&&(i+1)!=queuedTasks.length) {
                Task temp =queuedTasks[i];
                queuedTasks[i]=queuedTasks[i+1];
                queuedTasks[i+1]= temp;
            } else if (queuedTasks[i+1].isPriority()==queuedTasks[i].isPriority() && queuedTasks[i+1].getCreationTime()==queuedTasks[i].getCreationTime() && queuedTasks[i+1].getExecutionTime()>=queuedTasks[i].getExecutionTime()&&(i+1)!=queuedTasks.length) {
                Task temp =queuedTasks[i];
                queuedTasks[i]=queuedTasks[i+1];
                queuedTasks[i+1]= temp;
            }
        }


    }
    public void setProcessorsTasks(Task[] queuedTasks){
        for (int i = 0; i < processors.length; i++) {
            if (processors[i].isFinished()) {
                processors[i].setTask(queuedTasks[i]);
            }
        }
    }
}
