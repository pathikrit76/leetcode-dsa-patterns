class Solution {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int windowSum = 0;
        int count = 0;
        int requiredSum = threshold * k;

        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        if (windowSum >= requiredSum) {
            count++;
        }

        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            if (windowSum >= requiredSum) {
                count++;
            }
        }

        return count;
    }
}
