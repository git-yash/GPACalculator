import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    public double getAverageGPA(JPanel gradesPanel) {
        ArrayList<Double> GPAList = new ArrayList<>();
        ArrayList<Double> gradesList = searchTextFieldsForGrades(gradesPanel);
        ArrayList<Boolean> isWeightedList = searchCheckBoxInputs(gradesPanel);

        double totalGPA = 0.0;

        for (int i = 0; i < gradesList.size(); i++) {
            for (int j = 0; j < isWeightedList.size(); j++) {
                GPAList.add(getGPA(gradesList.get(i), isWeightedList.get(i)));
            }
        }

        for (Double aDouble : GPAList) {
            totalGPA += aDouble;
        }

        return totalGPA / GPAList.size();
    }

    private double getGPA(double grade, boolean isWeighted) {
        double GPA = 0.0;
        String strGrade = String.valueOf(grade);
        String[] strListGPA = String.valueOf(grade).split("");
        ArrayList<String> arrayListGPA = new ArrayList<>(Arrays.asList(strListGPA));
        int positionOfDecimal = arrayListGPA.indexOf(".");
        String amountAfterDecimal = strGrade.substring(positionOfDecimal + 1);
        double amountToAdd = Double.parseDouble("0." + arrayListGPA.get(positionOfDecimal - 1) + amountAfterDecimal);

        if (grade >= 100) {
            GPA = 5;
        } else if (grade >= 90) {
            GPA += 4 + amountToAdd;
        } else if (grade >= 80) {
            GPA += 3 + amountToAdd;
        } else if (grade >= 70) {
            GPA += 2 + amountToAdd;
        } else if (grade >= 60) {
            GPA += 1 + amountToAdd;
        }

        if (isWeighted) {
            GPA += 1;
        }

        if (GPA > 6.0) {
            GPA = 6.0;
        }

        return GPA;
    }

    private ArrayList<Double> searchTextFieldsForGrades(JPanel gradesPanel) {
        ArrayList<Double> gradesList = new ArrayList<>();
        JPanel validClassPanels = getValidClassPanels(gradesPanel);

        for (int i = 0; i < validClassPanels.getComponentCount(); i++) {
            JPanel classPanel = (JPanel) validClassPanels.getComponent(i);
            JTextField gradeInput = (JTextField) classPanel.getComponent(1);
            gradesList.add(Double.parseDouble(gradeInput.getText()));
        }

        return gradesList;
    }

    private ArrayList<Boolean> searchCheckBoxInputs(JPanel gradesPanel) {
        ArrayList<Boolean> isWeightedList = new ArrayList<>();
        JPanel validClassPanels = getValidClassPanels(gradesPanel);

        for (int i = 0; i < validClassPanels.getComponentCount(); i++) {
            JPanel classPanel = (JPanel) validClassPanels.getComponent(i);
            JCheckBox checkBox = (JCheckBox) classPanel.getComponent(2);
            isWeightedList.add(checkBox.isSelected());
        }

        return isWeightedList;
    }

    private JPanel getValidClassPanels(JPanel gradesPanel) {
        JPanel validClassPanels = new JPanel();

        for (int i = 0; i < gradesPanel.getComponentCount(); i++) {
            JPanel classPanel = (JPanel) gradesPanel.getComponent(i);
            JTextField gradeInput = (JTextField) classPanel.getComponent(1);

            if (!gradeInput.getText().equals("")) {
                validClassPanels.add(classPanel);
            }
        }

        return validClassPanels;
    }
}