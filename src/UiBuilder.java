import javax.swing.*;

public class UiBuilder {
    public void createFrame() {
        JFrame frame = new JFrame("GPA Calculator (6.0 Scale)");
        JPanel panel = new JPanel();

        panel.setBounds(30, 20, 420, 360);
        panel.setLayout(null);

        ComponentBuilder componentBuilder = new ComponentBuilder();

        JPanel gradesPanel = componentBuilder.createGradesPanel();
        JPanel toolbarPanel = componentBuilder.createToolbarPanel(frame, gradesPanel);
        JButton calculateButton = componentBuilder.createCalculateButton(frame, gradesPanel);

        panel.add(toolbarPanel);
        panel.add(gradesPanel);

        frame.add(panel);
        frame.add(calculateButton);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}