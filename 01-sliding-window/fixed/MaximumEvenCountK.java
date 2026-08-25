class MaximumEvenCountK {
    public int maxEvenCountKSubArray(int[] arr, int k) {
        int evenCount = 0;
        for (int i = 0; i < k; i++) {
            if (arr[i] % 2 == 0) evenCount++;
        }

        int maxEvenCount = evenCount;
        for (int i = k; i < arr.length; i++) {
            if (arr[i - k] % 2 == 0) evenCount--;
            if (arr[i] % 2 == 0) evenCount++;
            maxEvenCount = Math.max(maxEvenCount, evenCount);
        }

        return maxEvenCount;
    }
}
