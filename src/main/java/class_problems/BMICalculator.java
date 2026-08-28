import java.util.Scanner;

public class BMICalculator {

    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("\nPerson | Height (m) | Weight (kg) | BMI   | Status");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);

            System.out.printf(
                "Person %d | %.2f       | %.2f       | %.2f | %s%n",
                i + 1,
                heights[i],
                weights[i],
                bmi,
                getBmiStatus(bmi)
            );
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = 10;

        double[] heights = new double[n];
        double[] weights = new double[n];

        System.out.println("BMI Calculator for a Team");
        System.out.println("Enter height in meters and weight in kg.");

        for (int i = 0; i < n; i++) {
            System.out.print("Person " + (i + 1) + " - Height (m): ");
            heights[i] = sc.nextDouble();

            System.out.print("Person " + (i + 1) + " - Weight (kg): ");
            weights[i] = sc.nextDouble();
        }

        printWellnessReport(heights, weights);

        sc.close();
    }
}