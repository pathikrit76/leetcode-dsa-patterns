import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode 424 — Longest Repeating Character Replacement
 * Pattern: Variable-size Sliding Window — Longest Valid Window
 *
 * RECOGNITION
 * - We need the longest contiguous substring.
 * - We may modify at most k characters.
 * - We want every character in the chosen window to become the same.
 *
 * KEY DERIVATION
 * Suppose the current window length is L and its most frequent character
 * appears maxFrequency times. Keep those maxFrequency characters unchanged.
 * Every other character must be replaced.
 *
 *     replacementsNeeded = L - maxFrequency
 *
 * Therefore the window is valid when:
 *
 *     windowLength - maxFrequency <= k
 *
 * and invalid when:
 *
 *     windowLength - maxFrequency > k
 *
 * STATE
 * - frequency map: character counts in the current window
 * - maxFrequency: highest useful frequency observed while expanding
 *
 * WHY maxFrequency IS NOT DECREASED ON EVERY SHRINK
 * The standard optimized solution keeps a possibly stale maximum. We only
 * need it to decide whether a window length can beat the best length found.
 * Avoiding a full recomputation keeps each iteration simple and linear.
 *
 * CORE PATTERN
 *     expand right and update frequency/maxFrequency
 *     while replacementsNeeded > k:
 *         shrink left
 *     update maximum length
 *
 * COMMON MISTAKES
 * - Decrementing s[right] when shrinking; the outgoing character is s[left].
 * - Storing windowLength once and forgetting that left changes inside while.
 *   Prefer computing right - left + 1 directly.
 * - Updating maxLength before restoring validity.
 *
 * Time:  O(n) amortized.
 * Space: O(1) for the problem's uppercase English alphabet (at most 26 keys).
 */
class Solution {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> frequency = new HashMap<>();
        int left = 0;
        int maxFrequency = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char incoming = s.charAt(right);

            frequency.put(incoming, frequency.getOrDefault(incoming, 0) + 1);
            maxFrequency = Math.max(maxFrequency, frequency.get(incoming));

            // Too many non-majority characters would require more than k replacements.
            while ((right - left + 1) - maxFrequency > k) {
                char outgoing = s.charAt(left);
                frequency.put(outgoing, frequency.get(outgoing) - 1);

                if (frequency.get(outgoing) == 0) {
                    frequency.remove(outgoing);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
