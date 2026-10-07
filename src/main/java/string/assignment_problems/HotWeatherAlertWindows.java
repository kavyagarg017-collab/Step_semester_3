public class HotWeatherAlertWindows {

    public static int countAlerts(int[] readings,
                                  int k,
                                  int threshold) {

        int count = 0;
        int sum = 0;

        // First window
        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        if (sum >= k * threshold) {
            count++;
        }

        // Sliding window
        for (int i = k; i < readings.length; i++) {

            sum += readings[i];
            sum -= readings[i - k];

            if (sum >= k * threshold) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        int[] readings = {
            2, 2, 2, 2, 5, 5, 5, 8
        };

        int k = 3;
        int threshold = 4;

        System.out.println(
                countAlerts(readings, k, threshold)
        );
    }
}