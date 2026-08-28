public class TypingSpeedAccuracyChecker {
    public static void main(String[] args) {
        String original = "Java programming is fun";
        String typed = "Java programming is fun";

        int correct = 0;
        int length = Math.min(original.length(), typed.length());

        for (int i = 0; i < length; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                correct++;
            }
        }

        double accuracy = (correct * 100.0) / original.length();

        System.out.println("Accuracy: " + accuracy + "%");
    }
}