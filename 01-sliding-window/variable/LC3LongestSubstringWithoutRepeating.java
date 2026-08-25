import java.util.HashSet;
import java.util.Set;

/*
 * LeetCode 3 — Longest Substring Without Repeating Characters
 * Pattern: Variable-size Sliding Window — Longest Valid Window
 *
 * RECOGNITION
 * - "substring" means a contiguous range of the string.
 * - We need the longest range satisfying a constraint.
 * - The constraint is uniqueness: no character may repeat.
 *
 * STATE
 * A HashSet contains exactly the characters in the current valid window.
 *
 * INVALID CONDITION
 * The incoming character s[right] is already in the set.
 *
 * WHAT TO DO ON A DUPLICATE
 * Repeatedly remove s[left] and move left forward until the incoming
 * character is no longer present. Then add it and update the answer.
 *
 * Example idea for "abca":
 * window "abc" is valid. The next 'a' is a duplicate, so remove from the
 * left until the old 'a' is gone. The new valid window becomes "bca".
 *
 * LONGEST VALID WINDOW RULE
 *     while INVALID -> shrink
 *     once VALID     -> update maximum
 *
 * COMMON MISTAKES
 * - Using an if instead of while: multiple removals may be necessary.
 * - Adding the duplicate before restoring validity with this Set approach.
 * - Updating the answer before the duplicate has been removed.
 *
 * Time:  O(n) amortized. Every character enters and leaves the set at most once.
 * Space: O(min(n, character-set size)).
 *
 * Interview follow-up:
 * A HashMap<Character, lastIndex> can move left directly past the previous
 * occurrence instead of removing characters one by one.
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char incoming = s.charAt(right);

            // Shrink until adding incoming will keep every character unique.
            while (window.contains(incoming)) {
                window.remove(s.charAt(left));
                left++;
            }

            window.add(incoming);

            // Window is valid here.
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
