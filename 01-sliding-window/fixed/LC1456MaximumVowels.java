/*
 * LeetCode 1456 — Maximum Number of Vowels in a Substring of Given Length
 * Pattern: Fixed-size Sliding Window
 *
 * RECOGNITION
 * - We need a contiguous substring.
 * - Its length is fixed at k.
 * - We want the maximum value of some property inside each window.
 *
 * IMPORTANT PATTERN LESSON
 * Sliding Window does NOT mean we must maintain a sum.
 * We maintain exactly the state the question needs. Here that state is
 * the number of vowels in the current window.
 *
 * WHEN THE WINDOW SLIDES
 * - If the outgoing character is a vowel, decrement vowelCount.
 * - If the incoming character is a vowel, increment vowelCount.
 * - Update the maximum.
 *
 * This reuses the previous window instead of scanning k characters again.
 *
 * COMMON MISTAKE
 * Calling a helper that scans all k characters for every starting index
 * turns the solution back into O(n*k).
 *
 * Time:  O(n)
 * Space: O(1)
 */
class Solution {
    public int maxVowels(String s, int k) {
        int vowelCount = 0;

        // Count vowels in the first window.
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) {
                vowelCount++;
            }
        }

        int maxVowelCount = vowelCount;

        for (int right = k; right < s.length(); right++) {
            char outgoing = s.charAt(right - k);
            char incoming = s.charAt(right);

            if (isVowel(outgoing)) {
                vowelCount--;
            }
            if (isVowel(incoming)) {
                vowelCount++;
            }

            maxVowelCount = Math.max(maxVowelCount, vowelCount);
        }

        return maxVowelCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
