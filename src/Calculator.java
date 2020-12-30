import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {
    public double getAverageGPA(ArrayList<GradeClass> gradeClasses) {
        ArrayList<Double> GPAList = new ArrayList<>();

        double totalGPA = 0.0;

        for (int i = 0; i < gradeClasses.size(); i++) {
            GradeClass gradeClass = gradeClasses.get(i);
            GPAList.add(getGPA(gradeClass));
        }

        for (Double aDouble : GPAList) {
            totalGPA += aDouble;
        }

        return totalGPA / GPAList.size();
    }

    private double getGPA(GradeClass gradeClass) {
        double GPA = 0.0;
        double grade = gradeClass.grade;
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

        if (gradeClass.isWeighted) {
            GPA += 1;
        }

        if (GPA > 6.0) {
            GPA = 6.0;
        }

        return GPA;
    }
}