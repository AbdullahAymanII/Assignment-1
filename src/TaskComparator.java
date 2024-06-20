import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task t1, Task t2) {
        if (t1.isPriority() && !t2.isPriority()) return -1;
        if (!t1.isPriority() && t2.isPriority()) return 1;
        if (t1.getExecutionTime() > t2.getExecutionTime()) return -1;
        if (t1.getExecutionTime() < t2.getExecutionTime()) return 1;
        return 0;
    }

}