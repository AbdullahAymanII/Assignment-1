import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean continueSimulation = true;
        while (continueSimulation) {

            System.out.println("Please enter the number of processors you wish to run: ");
            int numberOfProcessors = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter the number of cycles you wish to run: ");
            int numberOfCycles = Integer.parseInt(sc.nextLine());

            System.out.println("Please enter the path of tasks you wish to run : ");
            String path = sc.nextLine();

            Simulator simulator = new Simulator(numberOfProcessors, numberOfCycles, path);
            simulator.run();

            System.out.println("Do you wish to continue the simulation? (Y/N) : ");
            char answer = sc.next().charAt(0);
            if (answer == 'N' || answer == 'n') {
                continueSimulation = false;
            }
            sc.nextLine();

        }
    }

}