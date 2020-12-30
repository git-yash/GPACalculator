import javax.swing.*;
import java.awt.*;

public class ComponentBuilder {
    private int currentIndex = 7;

    public JPanel createToolbarPanel(JFrame frame, JPanel gradesPanel) {
        JPanel panel = new JPanel();

        panel.setBounds(50, 5, 310, 45);
        panel.setLayout(new GridLayout(1, 2, 5, 2));

        panel.add(createAddButton(frame, gradesPanel));
        panel.add(createRemoveButton(frame, gradesPanel));

        return panel;
    }

    public JButton createCalculateButton(JFrame frame, JPanel gradesPanel) {
        JButton calculateButton = createButton("Calculate!");
        Calculator calculator = new Calculator();

        calculateButton.setBounds(150, 390, 160, 40);

        calculateButton.addActionListener(actionEvent -> {
            double averageGPA = calculator.getAverageGPA(gradesPanel);
//            JOptionPane.showMessageDialog(null, "Your GPA is: " + averageGPA);
        });

        return calculateButton;
    }

    public JPanel createGradesPanel() {
        JPanel panel = new JPanel();

        for (int i = 1; i <= 8; i++) {
            JPanel classPanel = createClassPanel(i);
            panel.setBounds(5, 60, 410, 295);
            panel.setLayout(new GridLayout(8, 1, 5, 5));
            panel.add(classPanel);
        }

        return panel;
    }

    private JPanel createClassPanel(int classNumber) {
        JPanel classesPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        JLabel classNumberLabel = new JLabel();
        classNumberLabel.setText("Class " + classNumber + " :");
        classNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JTextField gradeInput = new JTextField();

        JCheckBox isWeightedCheckBox = new JCheckBox();
        isWeightedCheckBox.setText("Weighted?");

        classesPanel.add(classNumberLabel);
        classesPanel.add(gradeInput);
        classesPanel.add(isWeightedCheckBox);

        return classesPanel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton();

        button.setText(text);
        button.setFont(new Font("Arial Black", Font.BOLD, 14));

        return button;
    }

    private JButton createAddButton(JFrame frame, JPanel gradesPanel) {
        JButton addButton = createButton("Add");

        addButton.addActionListener(actionEvent -> {
            if (currentIndex < 7) {
                gradesPanel.add(createClassPanel(currentIndex + 1));
                gradesPanel.revalidate();
                frame.repaint();
                currentIndex++;
            }
        });

        return addButton;
    }

    private JButton createRemoveButton(JFrame frame, JPanel gradesPanel) {
        JButton removeButton = createButton("Remove");

        removeButton.addActionListener(actionEvent -> {
            if (currentIndex > 0) {
                gradesPanel.remove(currentIndex);
                gradesPanel.revalidate();
                frame.repaint();
                currentIndex--;
            }
        });

        return removeButton;
    }

}