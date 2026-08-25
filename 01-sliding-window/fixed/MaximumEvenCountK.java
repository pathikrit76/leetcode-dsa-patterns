/*
 * Exercise — Maximum Even Numbers in Any Subarray of Size K
 * Pattern: Fixed-size Sliding Window
 *
 * WHY THIS EXERCISE MATTERS
 * It demonstrates that the Sliding Window pattern is about maintaining
 * reusable window STATE, not specifically about sums.
 *
 * STATE
 * evenCount = number of even integers currently inside the size-k window.
 *
 * SLIDE OPERATION
 * - arr[right-k] leaves: decrement if it is even.
 * - arr[right] enters: increment if it is even.
 * - Track the maximum evenCount seen.
 *
 * INTERVIEW TAKEAWAY
 * When a fixed-window problem asks for a property of every size-k range,
 * ask: "Can I update that property using only the outgoing and incoming
 * elements?" If yes, Sliding Window is likely appropriate.
 *
 * COMMON MISTAKES
 * - Forgetting to save the result for the first window.
 * - Removing arr[right] instead of arr[right-k].
 *
 * Time:  O(n)
 * Space: O(1)
 */
class MaximumEvenCountK {
    public int maxEvenCountKSubArray(int[] arr, int k) {
        int evenCount = 0;

        // Build the first window.
        for (int i = 0; i < k; i++) {
            if (arr[i] % 2 == 0) {
                evenCount++;
            }
        }

        int maxEvenCount = evenCount;

        for (int right = k; right < arr.length; right++) {
            int outgoing = arr[right - k];
            int incoming = arr[right];

            if (outgoing % 2 == 0) {
                evenCount--;
            }
            if (incoming % 2 == 0) {
                evenCount++;
            }

            maxEvenCount = Math.max(maxEvenCount, evenCount);
        }

        return maxEvenCount;
    }
}
