import java.util.ArrayList;

public class Simulator {
    private int numberOfProcessors;
    private int numberOfClockCycles;
    private String path;
    private Clock[] cycles;
    private Processor[] processors;

    private ArrayList<Task> tasks;
    private Schedular schedular;

    public Simulator(int numberOfProcessors, int numberOfClockCycles, String path) {
        try {
            if (numberOfProcessors < 1)
                throw new Exception();
            else if (numberOfClockCycles < 1)
                throw new Exception();
            else if (path == null)
                throw new Exception();

            this.numberOfProcessors = numberOfProcessors;
            this.numberOfClockCycles = numberOfClockCycles;
            this.path = path;

            for (int i = 0; i < numberOfClockCycles; i++) {
                this.cycles[i]=new Clock(i);
            }

            for (int i = 0; i < numberOfProcessors; i++) {
                processors[i]=new Processor(i);
            }

        }
        catch(Exception e) {
            System.out.println("Simulator Error!!!");
        }
    }
}
