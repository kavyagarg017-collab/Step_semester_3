package string.assignment_problems;

import java.util.*;

public class StopWordFilteredWordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        Set<String> stopWords = new HashSet<>(
            Arrays.asList("the", "was", "and", "a", "is", "of", "in")
        );

        String cleaned = feedback
                .toLowerCase()
                .replace(".", "")
                .replace(",", "");

        String[] words = cleaned.split("\\s+");

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            if (stopWords.contains(word)) {
                continue;
            }

            frequency.put(
                word,
                frequency.getOrDefault(word, 0) + 1
            );
        }

        List<Map.Entry<String, Integer>> entries =
                new ArrayList<>(frequency.entrySet());

        entries.sort(
            (a, b) -> b.getValue().compareTo(a.getValue())
        );

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(
                entry.getKey() + ": " + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}