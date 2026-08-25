class Solution {
    public int maxVowels(String s, int k) {
        int vowelCount = 0;
        for (int i = 0; i < k; i++) {
            if (isVowel(s.charAt(i))) vowelCount++;
        }

        int maxVowelCount = vowelCount;
        for (int i = k; i < s.length(); i++) {
            if (isVowel(s.charAt(i - k))) vowelCount--;
            if (isVowel(s.charAt(i))) vowelCount++;
            maxVowelCount = Math.max(maxVowelCount, vowelCount);
        }

        return maxVowelCount;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
