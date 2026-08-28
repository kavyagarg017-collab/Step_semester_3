public class TrafficSignalStreakAnalyzer {
    public static void main(String[] args) {
        String signals = "RRGGGYYGRR";

        int maxStreak = 0;
        int currentStreak = 0;
        char previous = '\0';

        for (int i = 0; i < signals.length(); i++) {
            char current = signals.charAt(i);

            if (current == previous) {
                currentStreak++;
            } else {
                currentStreak = 1;
                previous = current;
            }

            if (currentStreak > maxStreak) {
                maxStreak = currentStreak;
            }
        }

        System.out.println("Longest Signal Streak: " + maxStreak);
    }
}