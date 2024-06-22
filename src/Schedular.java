import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Schedular{
    private ArrayList<Task> tasks;
    private Processor[] processors;
    private Clock[] cycles;

    public abstract void tasksSchedule();
}
