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
            if (numberOfProcessors < 1)
                throw new Exception();
            else if (numberOfClockCycles < 1)
                throw new Exception();
            else if (path == null)
                throw new Exception();

            this.numberOfProcessors = numberOfProcessors;
            this.numberOfClockCycles = numberOfClockCycles;
            this.path = path;
            processors=new Processor[numberOfProcessors];
            cycles=new Clock[numberOfClockCycles];
            for (int i = 1; i <= numberOfClockCycles; i++) {
                this.cycles[i]=new Clock(i);
            }

            for (int i = 1; i <= numberOfProcessors; i++) {
                processors[i]=new Processor(i);
            }
            this.schedular=new Schedular(path,processors,cycles);

        }
        catch(Exception e) {
            System.out.println("Simulator Error!!!");
        }
    }
    public void run(){
        schedular.tasksSchedule();
    }
}
