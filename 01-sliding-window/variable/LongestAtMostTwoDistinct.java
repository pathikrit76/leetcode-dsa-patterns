import java.util.HashMap;
import java.util.Map;

class LongestAtMostTwoDistinct {
    public int longestSubarray(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < arr.length; right++) {
            frequency.put(arr[right], frequency.getOrDefault(arr[right], 0) + 1);

            while (frequency.size() > 2) {
                int outgoing = arr[left];
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
