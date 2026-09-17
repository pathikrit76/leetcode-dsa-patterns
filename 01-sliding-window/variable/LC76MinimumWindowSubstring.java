import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode 76 - Minimum Window Substring
 *
 * PATTERN: Variable-Size Sliding Window -> Minimum Valid Window
 *          + Frequency Map
 *
 * How to recognize it:
 * - We need a CONTIGUOUS substring.
 * - The substring must contain all characters required by t, including duplicates.
 * - We want the MINIMUM such substring.
 *
 * This suggests:
 *   expand right until the window becomes VALID
 *   while it remains valid, shrink left and record the minimum
 *
 * This is the key contrast with "longest valid window" problems:
 * - Longest valid:  shrink while INVALID, then update maximum.
 * - Minimum valid:  shrink while VALID, updating minimum before each removal.
 *
 * Frequency-map meaning used by this solution:
 *   value > 0 -> still missing that many copies
 *   value = 0 -> have exactly enough copies
 *   value < 0 -> window contains extra copies
 *
 * count = total number of required character OCCURRENCES still missing.
 * It is t.length(), not the number of distinct characters.
 * This matters for t such as "AABC": we need four occurrences, not three types.
 *
 * EXPAND intuition:
 * If a relevant character has frequency > 0 before we take it, it satisfies
 * one missing requirement, so count--. We then decrement its map value ALWAYS.
 * Extra copies therefore naturally become negative.
 *
 * SHRINK intuition:
 * Suppose the left character currently has map value 0. Removing it changes
 * 0 -> 1, so we lose a required copy and count must increase.
 * If it is -1, removing it changes -1 -> 0: we only removed an extra copy,
 * so the window is still valid.
 *
 * Mental template:
 *   for each right:
 *       add right to window
 *       while window is VALID:
 *           update minimum answer
 *           remove left
 *           left++
 *
 * Time: O(|s| + |t|). Both pointers move only forward.
 * Space: O(number of distinct characters in t).
 */
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> frequency = new HashMap<>();
        int startIndex = 0;
        int left = 0;
        int minLength = Integer.MAX_VALUE;

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Build how many copies of each character are required.
        for (int i = 0; i < t.length(); i++) {
            frequency.put(t.charAt(i), frequency.getOrDefault(t.charAt(i), 0) + 1);
        }

        // Number of required character occurrences that are still missing.
        int count = t.length();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            // EXPAND the window.
            if (frequency.containsKey(rightChar)) {
                int rightCount = frequency.get(rightChar);

                // Only a positive value means this occurrence was still needed.
                if (rightCount > 0) {
                    count--;
                }

                // Always decrement so extra copies are represented by negatives.
                frequency.put(rightChar, rightCount - 1);
            }

            // count == 0 means the current window contains every required occurrence.
            // Because we want MINIMUM, keep shrinking while the window is valid.
            while (count == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);

                // SHRINK the window by returning leftChar to the requirement map.
                if (frequency.containsKey(leftChar)) {
                    int leftCount = frequency.get(leftChar);

                    // 0 -> 1 means we are removing a character we had exactly enough of.
                    // The next window will therefore be missing one required occurrence.
                    if (leftCount == 0) {
                        count++;
                    }

                    // -1 -> 0 removes only an extra copy and keeps the window valid.
                    frequency.put(leftChar, leftCount + 1);
                }

                left++;
            }
        }

        return minLength == Integer.MAX_VALUE
                ? ""
                : s.substring(startIndex, startIndex + minLength);
    }
}
