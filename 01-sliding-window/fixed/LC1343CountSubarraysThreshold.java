/*
 * LeetCode 1343 — Number of Sub-arrays of Size K and Average >= Threshold
 * Pattern: Fixed-size Sliding Window
 *
 * RECOGNITION
 * - We are checking contiguous subarrays.
 * - Every candidate has exactly k elements.
 * - We need to count how many windows satisfy a condition.
 *
 * KEY INTERVIEW TRICK
 * Instead of computing an average for every window:
 *
 *     windowSum / k >= threshold
 *
 * compare sums directly:
 *
 *     windowSum >= threshold * k
 *
 * This avoids repeated division and keeps the condition simple.
 *
 * ALGORITHM
 * 1. Build the first size-k sum.
 * 2. Count it if it satisfies the required sum.
 * 3. Slide one position at a time:
 *      remove arr[right-k]
 *      add arr[right]
 * 4. Count every valid window.
 *
 * COMMON MISTAKES
 * - Forgetting to check the first window.
 * - Recomputing the average for every window unnecessarily.
 * - Using the wrong outgoing index.
 *
 * Time:  O(n)
 * Space: O(1)
 */
class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int windowSum = 0;
        int count = 0;
        int requiredSum = threshold * k;

        // First fixed window.
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        if (windowSum >= requiredSum) {
            count++;
        }

        // Slide: remove outgoing value, add incoming value.
        for (int right = k; right < arr.length; right++) {
            windowSum -= arr[right - k];
            windowSum += arr[right];

            if (windowSum >= requiredSum) {
                count++;
            }
        }

        return count;
    }
}
