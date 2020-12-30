import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

    public JButton createCalculateButton(JPanel gradesPanel) {
        JButton calculateButton = createButton("Calculate!");

        calculateButton.setBounds(150, 390, 160, 40);

        calculateButton.addActionListener(actionEvent -> {
            Calculator calculator = new Calculator();
            try {
                double averageGPA = calculator.getAverageGPA(createGradeClass(gradesPanel));
                JOptionPane.showMessageDialog(null, "Your GPA is: " + averageGPA);
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(null, "INVALID INPUT");
            }
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

    private ArrayList<GradeClass> createGradeClass(JPanel gradesPanel) {
        ArrayList<GradeClass> gradeClasses = new ArrayList<>();

        for (int i = 0; i < gradesPanel.getComponentCount(); i++) {
            JPanel classPanel = (JPanel) gradesPanel.getComponent(i);
            JTextField gradeInput = (JTextField) classPanel.getComponent(1);
            JCheckBox isWeighted = (JCheckBox) classPanel.getComponent(2);

            if (!gradeInput.getText().equals("")) {
                GradeClass gradeClass = new GradeClass(Double.parseDouble(gradeInput.getText()), isWeighted.isSelected());
                gradeClasses.add(gradeClass);
            }
        }

        return gradeClasses;
    }
}