import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * LeetCode 438 - Find All Anagrams in a String
 *
 * PATTERN: Fixed-Size Sliding Window + Frequency Counting
 *
 * Intuition from the problem statement:
 * We are looking for substrings that are anagrams of p.
 * An anagram:
 *   1. has the same length as p
 *   2. has the same frequency of every character
 *
 * That immediately gives us a fixed window of size p.length().
 *
 * Brute force idea:
 * Check every substring of length p.length() and recount its characters.
 * This repeats work because neighboring windows share almost everything.
 *
 * Sliding-window improvement:
 * When moving one step right:
 *   - add the new character entering the window
 *   - remove the old character leaving the window
 *   - compare frequencies with p
 *
 * Recognition shortcut:
 * "Find every anagram/permutation occurrence"
 *      -> fixed window = pattern length
 *      -> frequency state
 *      -> collect matching start indices
 *
 * LC567 uses essentially the same pattern. The main difference is output:
 * LC567 returns true on the first match; LC438 records every match.
 *
 * Time: O(n * 26) = O(n), because the alphabet has constant size 26.
 * Space: O(1).
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] windowFreq = new int[26];
        int[] patternFreq = new int[26];

        // Frequency signature that every valid window must match.
        for (int i = 0; i < p.length(); i++) {
            patternFreq[p.charAt(i) - 'a']++;
        }

        for (int right = 0; right < s.length(); right++) {
            // 1. Expand: add the new right-side character.
            windowFreq[s.charAt(right) - 'a']++;

            // 2. Keep the window fixed at p.length().
            // Once it grows beyond that size, remove the outgoing character.
            if (right >= p.length()) {
                windowFreq[s.charAt(right - p.length()) - 'a']--;
            }

            // 3. Equal frequency arrays mean this fixed-size window is an anagram.
            if (Arrays.equals(windowFreq, patternFreq)) {
                result.add(right - p.length() + 1);
            }
        }

        return result;
    }
}
