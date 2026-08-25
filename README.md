# LeetCode DSA Patterns — Java

A pattern-first Java repository for students preparing for FAANG-style DSA interviews.

The goal is **not to memorize solutions**. For every problem, learn to answer:

1. What clues in the question reveal the pattern?
2. What does the window/pointer state represent?
3. When does the state become valid or invalid?
4. What changes when a pointer moves?
5. Why is the optimized complexity correct?
6. What are the common implementation mistakes?

## How to use this repository

For each problem:

- Read the problem and identify the pattern before opening the solution.
- Explain the brute-force approach and its complexity.
- Derive why repeated work can be reused.
- Write the optimized solution from memory.
- Dry-run it on at least one normal case and one edge case.
- Be able to explain time and space complexity in an interview.

## Patterns

### 01. Sliding Window

Use Sliding Window when the problem works with a **contiguous subarray or substring** and neighboring candidate ranges share most of their elements.

Solved so far:

#### Fixed-size Sliding Window
- Maximum Sum Subarray of Size K
- LeetCode 643 — Maximum Average Subarray I
- LeetCode 1343 — Number of Sub-arrays of Size K and Average >= Threshold
- LeetCode 1456 — Maximum Number of Vowels in a Substring of Given Length
- Maximum Even Count in a Subarray of Size K

#### Variable-size Sliding Window
- LeetCode 209 — Minimum Size Subarray Sum
- Longest Subarray with At Most Two Distinct Integers
- LeetCode 3 — Longest Substring Without Repeating Characters
- LeetCode 424 — Longest Repeating Character Replacement

See [`01-sliding-window/README.md`](01-sliding-window/README.md) for pattern recognition rules, templates, interview reasoning, and common mistakes.

More patterns will be added as they are learned and mastered.
