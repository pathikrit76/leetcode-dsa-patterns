# 01 - Sliding Window

Sliding Window is useful when a problem asks about contiguous subarrays or substrings and neighboring candidate windows share most of their elements.

## Fixed-size window

Use when the window length is predetermined (`k`).

Pattern:

1. Build the first window.
2. Maintain the required state (sum, count, etc.).
3. For every new element, remove the outgoing element and add the incoming element.
4. Update the answer.

### Solved

- Maximum Sum Subarray of Size K (existing root solution)
- LeetCode 643 - Maximum Average Subarray I
- LeetCode 1343 - Number of Sub-arrays of Size K and Average >= Threshold
- LeetCode 1456 - Maximum Number of Vowels in a Substring of Given Length
- Maximum Even Count in a Subarray of Size K

## Variable-size window

### Minimum valid window

Expand right. While the window is valid, update the minimum answer and shrink left.

Example: LeetCode 209 - Minimum Size Subarray Sum.

### Longest valid window

Expand right. While the window is invalid, shrink left. Once valid again, update the maximum answer.

### Solved

- LeetCode 209 - Minimum Size Subarray Sum
- Longest Subarray with At Most Two Distinct Integers
- LeetCode 3 - Longest Substring Without Repeating Characters
- LeetCode 424 - Longest Repeating Character Replacement

## Complexity insight

A variable Sliding Window can contain a `while` loop inside a `for` loop and still be O(n): both `left` and `right` only move forward, each at most n times.
