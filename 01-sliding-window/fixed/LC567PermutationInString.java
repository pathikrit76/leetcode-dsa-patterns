/*
 * LeetCode 567 - Permutation in String
 *
 * PATTERN: Fixed-Size Sliding Window + Frequency Counting
 *
 * How to derive the pattern:
 * A permutation of s1 has exactly the same characters with exactly the
 * same frequencies as s1. Its order does not matter.
 *
 * If s1 has length m, every possible permutation also has length m.
 * Therefore we never need windows of different sizes -> FIXED window m.
 *
 * Instead of generating all permutations:
 * 1. Count frequencies in s1.
 * 2. Count frequencies in the first m characters of s2.
 * 3. If the arrays match, we found a permutation.
 * 4. Slide one position: add the incoming char and remove the outgoing char.
 *
 * Recognition shortcut:
 * "Does string contain an anagram/permutation of pattern?"
 *      -> fixed window of pattern.length()
 *      -> compare character frequencies
 *
 * Time: O(|s1| + |s2| * 26) = O(|s1| + |s2|), since alphabet size is fixed.
 * Space: O(1) - two arrays of size 26.
 */
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        // Build target frequency and the first fixed-size window together.
        for (int i = 0; i < s1.length(); i++) {
            freq1[s1.charAt(i) - 'a']++;
            freq2[s2.charAt(i) - 'a']++;
        }

        if (isSame(freq1, freq2)) {
            return true;
        }

        // Keep window size exactly s1.length().
        for (int right = s1.length(); right < s2.length(); right++) {
            // Character entering from the right.
            freq2[s2.charAt(right) - 'a']++;

            // Character leaving from the left.
            int outgoingIndex = right - s1.length();
            freq2[s2.charAt(outgoingIndex) - 'a']--;

            if (isSame(freq1, freq2)) {
                return true;
            }
        }

        return false;
    }

    // Same frequencies + same window length means the window is a permutation.
    private boolean isSame(int[] freq1, int[] freq2) {
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }
        return true;
    }
}
