/*
 * LeetCode 1004 - Max Consecutive Ones III
 *
 * PATTERN: Variable-Size Sliding Window -> Longest Valid Window
 *
 * How to recognize it:
 * - We need the LONGEST contiguous subarray.
 * - We may change at most k zeros into ones.
 * - Therefore a window is valid when it contains <= k zeros.
 *
 * Intuition:
 * Expand right to make the window larger.
 * If zeroCount becomes greater than k, the window cannot be converted
 * into all 1s using only k flips, so move left until it is valid again.
 * Once valid, record the largest window seen.
 *
 * Mental template:
 *   add right
 *   while (window is invalid) remove left
 *   update maximum
 *
 * Why only zeroCount?
 * We do not need a HashMap because the only resource we spend is a
 * zero -> one flip. Tracking zeros is enough to describe validity.
 *
 * Time: O(n) - each index enters and leaves the window at most once.
 * Space: O(1)
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int zeroCount = 0;
        int maxLength = 0;
        int left = 0;

        // right expands the candidate window.
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            // More than k zeros means we would need too many flips.
            // Shrink only until the window becomes valid again.
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // At this point zeroCount <= k, so [left..right] is valid.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
