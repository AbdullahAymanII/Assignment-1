import java.util.ArrayList;

public class Simulator {
    private int numberOfProcessors;
    private int numberOfClockCycles;
    private String path;
    private Clock[] cycles;
    private Processor[] processors;
    private Schedular schedular;

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

            for (int i = 0; i < numberOfClockCycles; i++) {
                cycles[i] = new Clock(i + 1);
            }

            for (int i = 0; i < numberOfProcessors; i++) {
                processors[i] = new Processor(i + 1);
            }

            this.schedular = new Schedular(path, processors, cycles);
        } catch (Exception e) {
            System.out.println("Simulator Error: " + e.getMessage());
        }
    }

    public void run() {
        schedular.tasksSchedule();
    }

}
