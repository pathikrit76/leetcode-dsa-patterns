import java.util.HashMap;
import java.util.Map;

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
