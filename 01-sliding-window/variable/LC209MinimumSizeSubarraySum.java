/*
 * LeetCode 209 — Minimum Size Subarray Sum
 * Pattern: Variable-size Sliding Window — MINIMUM VALID WINDOW
 *
 * RECOGNITION
 * - We need a contiguous subarray.
 * - Its length is not fixed.
 * - We want the MINIMUM length whose sum is at least target.
 * - nums contains positive integers, which is crucial for this approach.
 *
 * WHY POSITIVE NUMBERS MATTER
 * Expanding right can only keep/increase the sum.
 * Removing nums[left] can only keep/decrease the sum.
 * This monotonic behavior lets us safely shrink once the window is valid.
 * With arbitrary negative numbers, this exact logic is not generally valid.
 *
 * CORE PATTERN — MINIMUM VALID WINDOW
 *
 *     expand right
 *     while window is VALID:
 *         update minimum answer
 *         shrink from left
 *
 * Why shrink while valid? Because after finding one valid window, we want
 * to discover whether an even smaller valid window exists.
 *
 * WINDOW VALIDITY
 *     windowSum >= target
 *
 * COMMON MISTAKES
 * - Updating the answer after removing nums[left]; record the valid window first.
 * - Moving left backward instead of forward.
 * - Forgetting to return 0 when no valid window exists.
 * - Assuming nested for/while automatically means O(n^2).
 *
 * WHY TIME IS O(n)
 * right moves forward at most n times and left also moves forward at most n
 * times across the entire execution. Total pointer movement is O(n).
 *
 * Time:  O(n)
 * Space: O(1)
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int windowSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            // Expand the window.
            windowSum += nums[right];

            // Current window is valid. Record it, then try to make it smaller.
            while (windowSum >= target) {
                minLength = Math.min(minLength, right - left + 1);

                windowSum -= nums[left];
                left++;
            }
        }

        // LeetCode expects 0 if no qualifying subarray exists.
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
