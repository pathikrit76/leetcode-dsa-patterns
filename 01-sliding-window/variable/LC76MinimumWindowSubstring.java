import java.util.HashMap;
import java.util.Map;

class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> frequency = new HashMap<>();
        int startIndex = 0;
        int left = 0;
        int minLength = Integer.MAX_VALUE;

        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        for (int i = 0; i < t.length(); i++) {
            frequency.put(t.charAt(i), frequency.getOrDefault(t.charAt(i), 0) + 1);
        }

        int count = t.length();

        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);

            if (frequency.containsKey(rightChar)) {
                int rightCount = frequency.get(rightChar);
                if (rightCount > 0) {
                    count--;
                }
                frequency.put(rightChar, rightCount - 1);
            }

            while (count == 0) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                char leftChar = s.charAt(left);
                if (frequency.containsKey(leftChar)) {
                    int leftCount = frequency.get(leftChar);
                    if (leftCount == 0) {
                        count++;
                    }
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
