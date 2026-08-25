import java.util.HashMap;
import java.util.Map;

/*
 * Longest Subarray with At Most Two Distinct Integers
 * Pattern: Variable Sliding Window - Longest Valid Window
 *
 * Recognition:
 * - contiguous subarray
 * - longest possible range
 * - at most two distinct values
 *
 * State:
 * frequency maps each value to its count inside the current window.
 * A frequency map is better than a Set here because one occurrence may
 * leave while another occurrence of the same value remains in the window.
 *
 * Valid:   frequency.size() <= 2
 * Invalid: frequency.size() > 2
 *
 * Longest-valid-window rule:
 * 1. Expand right.
 * 2. While invalid, remove elements from the left.
 * 3. When valid again, update the maximum length.
 *
 * Important: remove a key from the map only when its frequency reaches 0.
 *
 * Common mistakes:
 * - Updating maxLength while the window is invalid.
 * - Removing a key even though its remaining frequency is greater than 0.
 * - Decrementing the right element when shrinking instead of the left element.
 *
 * Time: O(n) amortized because left and right only move forward.
 * Space: O(n) worst case for the general map technique; the valid window in
 * this specific problem contains at most two keys.
 */
class LongestAtMostTwoDistinct {
    public int longestSubarray(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < arr.length; right++) {
            // Expand the window with the incoming value.
            frequency.put(arr[right], frequency.getOrDefault(arr[right], 0) + 1);

            // Restore validity whenever a third distinct value appears.
            while (frequency.size() > 2) {
                int outgoing = arr[left];
                frequency.put(outgoing, frequency.get(outgoing) - 1);

                if (frequency.get(outgoing) == 0) {
                    frequency.remove(outgoing);
                }
                left++;
            }

            // The current window is valid here.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
