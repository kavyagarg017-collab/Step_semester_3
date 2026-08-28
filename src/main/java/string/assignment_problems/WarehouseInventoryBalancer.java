public class WarehouseInventoryBalancer {
    public static void main(String[] args) {
        int[] inventory = {10, 20, 30, 40, 50};

        int total = 0;

        for (int value : inventory) {
            total += value;
        }

        int leftSum = 0;

        for (int i = 0; i < inventory.length; i++) {
            int rightSum = total - leftSum - inventory[i];

            if (leftSum == rightSum) {
                System.out.println("Balanced at index: " + i);
                System.out.println("Balance value: " + inventory[i]);
                return;
            }

            leftSum += inventory[i];
        }

        System.out.println("No balance point found");
    }
}