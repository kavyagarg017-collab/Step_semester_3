import java.util.*;

public class MostPopularCanteenOrder {

    public static String[] mostPopular(String[] orders) {

        Map<String, Integer> count = new HashMap<>();

        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        String bestItem = orders[0];
        int bestCount = count.get(bestItem);

        for (String item : orders) {
            if (count.get(item) > bestCount) {
                bestItem = item;
                bestCount = count.get(item);
            }
        }

        return new String[]{bestItem, String.valueOf(bestCount)};
    }

    public static void main(String[] args) {

        String[] orders = {
            "dosa", "idli", "vada",
            "dosa", "idli", "dosa", "tea"
        };

        String[] result = mostPopular(orders);

        System.out.println(
                "(\"" + result[0] + "\", " + result[1] + ")"
        );

        String[] orders2 = {
            "tea", "coffee", "coffee", "tea"
        };

        String[] result2 = mostPopular(orders2);

        System.out.println(
                "(\"" + result2[0] + "\", " + result2[1] + ")"
        );
    }
}