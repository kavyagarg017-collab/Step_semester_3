import java.util.HashSet;

public class PairWithTargetSum {

    // Brute Force Approach
    static boolean bruteForce(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }

    // HashSet Approach
    static boolean usingHashSet(int[] nums, int target) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            int complement = target - num;

            if (set.contains(complement)) {
                return true;
            }

            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;

        int[] nums2 = {3, 4, 6};
        int target2 = 20;

        System.out.println("Brute Force: " +
                bruteForce(nums1, target1));

        System.out.println("HashSet: " +
                usingHashSet(nums1, target1));

        System.out.println("Brute Force: " +
                bruteForce(nums2, target2));

        System.out.println("HashSet: " +
                usingHashSet(nums2, target2));
    }
}