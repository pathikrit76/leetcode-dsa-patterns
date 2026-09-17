/*
 * Maximum Sum Subarray of Size K
 * Pattern: Fixed-size Sliding Window
 *
 * This is the foundational fixed-window problem.
 *
 * RECOGNITION
 * - "subarray" means contiguous elements.
 * - "size k" means every candidate window has a fixed length.
 * - We need the maximum value across all such windows.
 *
 * BRUTE FORCE IDEA
 * Start at every possible index and sum the next k elements.
 * Number of windows = n - k + 1.
 * Work per window = k.
 * Time = O(n*k).
 *
 * WHY SLIDING WINDOW WORKS
 * Neighboring size-k windows share k - 1 elements.
 * Example:
 *
 *     [2,1,5] -> [1,5,1]
 *
 * The old 2 leaves, the new 1 enters, and [1,5] stays unchanged.
 * Therefore:
 *
 *     nextWindowSum = currentWindowSum - outgoing + incoming
 *
 * This reduces the optimized solution to O(n) time and O(1) extra space.
 *
 * This file also keeps a prefix-sum approach for comparison. Prefix sums
 * answer each range-sum query in O(1), but require O(n) extra memory.
 */
public class MaximumSubArraySumOfSizeK {

    public static void main(String[] args) {
        System.out.println(
            maxSubarraySumOptimized(new int[] {1, 4, 2, 10, 23, 3, 1, 0, 20}, 4)
        );
    }

    /*
     * Approach 1 — Prefix Sum
     *
     * prefix[i] = sum of arr[0..i].
     * Sum of arr[left..right] can then be obtained without scanning the range.
     *
     * Time:  O(n)
     * Space: O(n)
     */
    public static int maxSubarraySum(int[] arr, int k) {
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        int maxSum = Integer.MIN_VALUE;

        for (int left = 0; left + k <= arr.length; left++) {
            int right = left + k - 1;

            int currentSum = (left == 0)
                    ? prefix[right]
                    : prefix[right] - prefix[left - 1];

            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /*
     * Approach 2 — Sliding Window (preferred for this problem)
     *
     * Interview invariant:
     * windowSum always represents the sum of the current k elements.
     *
     * Common mistakes:
     * - Forgetting to store the first window as the initial answer.
     * - Removing arr[i] instead of arr[i-k].
     * - Initializing the maximum to 0 when all values could be negative.
     *
     * Time:  O(n)
     * Space: O(1)
     */
    public static int maxSubarraySumOptimized(int[] arr, int k) {
        int windowSum = 0;

        // Build the first complete window.
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        // Slide one position at a time.
        for (int right = k; right < arr.length; right++) {
            int outgoing = arr[right - k];
            int incoming = arr[right];

            windowSum = windowSum - outgoing + incoming;
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
