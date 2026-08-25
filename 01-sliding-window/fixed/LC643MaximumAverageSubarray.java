/*
 * LeetCode 643 — Maximum Average Subarray I
 * Pattern: Fixed-size Sliding Window
 *
 * HOW TO RECOGNIZE THE PATTERN
 * - We need a contiguous subarray.
 * - Every candidate must contain exactly k elements.
 * - Consecutive windows share k - 1 elements.
 *
 * BRUTE FORCE
 * Calculate the sum of every size-k subarray from scratch.
 * There are roughly n windows and each takes O(k), giving O(n*k).
 *
 * OPTIMIZED IDEA
 * 1. Calculate the sum of the first k elements.
 * 2. When the window moves one position:
 *      subtract the outgoing element nums[right - k]
 *      add the incoming element nums[right]
 * 3. Track the maximum window sum.
 * 4. Divide by k only once at the end because every window has the same size.
 *
 * WINDOW INVARIANT
 * windowSum always represents the sum of exactly k consecutive elements
 * after the first window has been built.
 *
 * COMMON INTERVIEW MISTAKE
 * Do not initialize maxWindowSum to 0: the input can contain negative values.
 * Initialize it from the first complete window instead.
 *
 * Time:  O(n)
 * Space: O(1)
 */
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;

        // Build the first fixed-size window [0 ... k-1].
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        // The first complete window is our initial best answer.
        int maxWindowSum = windowSum;

        // right is the incoming element; right-k is the outgoing element.
        for (int right = k; right < nums.length; right++) {
            windowSum -= nums[right - k];
            windowSum += nums[right];
            maxWindowSum = Math.max(maxWindowSum, windowSum);
        }

        return (double) maxWindowSum / k;
    }
}
