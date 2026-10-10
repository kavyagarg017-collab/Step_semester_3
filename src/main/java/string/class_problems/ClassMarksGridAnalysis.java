
import java.util.*;
import java.util.regex.*;

public class ClassMarksGridAnalysis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        Pattern pattern = Pattern.compile("-?\\d+");

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String name = line.split("\\s+")[0];
            Matcher matcher = pattern.matcher(line.substring(name.length()));

            int[] marks = new int[3];
            int i = 0;

            while (matcher.find() && i < 3) {
                marks[i++] = Integer.parseInt(matcher.group());
            }

            if (i == 3) {
                names.add(name);
                marksList.add(marks);
            }
        }

        int n = names.size();
        int topperIndex = -1;
        int highestTotal = -1;
        int[] subjectTotals = new int[3];

        System.out.print("Totals ");

        for (int i = 0; i < n; i++) {
            int[] marks = marksList.get(i);
            int total = marks[0] + marks[1] + marks[2];

            System.out.print(names.get(i) + " " + total);
            if (i < n - 1) System.out.print(", ");

            for (int j = 0; j < 3; j++)
                subjectTotals[j] += marks[j];

            if (total > highestTotal) {
                highestTotal = total;
                topperIndex = i;
            }
        }

        System.out.println();
        System.out.printf(Locale.US, "averages %.2f, %.2f, %.2f%n",
                subjectTotals[0] / (double) n,
                subjectTotals[1] / (double) n,
                subjectTotals[2] / (double) n);

        if (topperIndex >= 0) {
            System.out.println("topper " + names.get(topperIndex) +
                    " (" + highestTotal + ")");
        }

        sc.close();
    }
}
