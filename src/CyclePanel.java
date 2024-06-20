import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CyclePanel extends JPanel {
    public CyclePanel(int cycleNumber, Processor[] processors, ArrayList<Task> completedTasks) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Cycle " + cycleNumber));

        JPanel processorPanel = new JPanel();
        processorPanel.setLayout(new GridLayout(1, processors.length));

        for (Processor processor : processors) {
            JPanel pPanel = new JPanel();
            pPanel.setBorder(BorderFactory.createTitledBorder("Processor " + processor.getProcessorId()));
            Task task = processor.getTask();
            if (task != null && !processor.isFinished()) {
                pPanel.add(new JLabel("Task " + task.getTaskId() + " (" + (task.isPriority() ? "P" : "NP") + ")"));
            } else {
                pPanel.add(new JLabel("Idle"));
            }
            processorPanel.add(pPanel);
        }

        add(processorPanel, BorderLayout.CENTER);

        JPanel completedPanel = new JPanel();
        completedPanel.setBorder(BorderFactory.createTitledBorder("Completed Tasks"));
        for (Task task : completedTasks) {
            completedPanel.add(new JLabel("Task " + task.getTaskId()));
        }

        add(completedPanel, BorderLayout.SOUTH);
    }
}
