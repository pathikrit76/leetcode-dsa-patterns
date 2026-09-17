# Pathikrit Sanyal — LeetCode DSA Patterns in Java

This repository documents my personal DSA preparation journey for FAANG-style coding interviews.

I am building it pattern by pattern in Java, with an emphasis on understanding **why an approach works**, how to recognize it in an interview, and how to explain the optimized solution clearly instead of memorizing code.

## Profiles

- **LeetCode:** https://leetcode.com/u/pathikritsanyal76/
- **GitHub:** https://github.com/pathikrit76

## What this repository represents

This is my working DSA notebook and solution repository. Every problem added here is part of my structured interview preparation and is organized around reusable problem-solving patterns.

For each problem, I aim to document:

1. How to recognize the pattern from the question.
2. The brute-force idea and why it is inefficient.
3. The optimized intuition.
4. The important invariant or state to maintain.
5. When pointers/windows should move.
6. Common implementation mistakes.
7. Time and space complexity.
8. The Java solution I practiced.

The comments inside the solution files are intentionally written as **reasoning notes**, not just descriptions of individual lines. The goal is to be able to revisit a problem later and reconstruct the algorithm from its intuition.

## My interview-preparation approach

I try to solve problems in this order:

- Identify the pattern before coding.
- Explain the brute-force approach.
- Find the repeated work or bottleneck.
- Derive the optimized approach.
- Define the window/pointer invariant.
- Dry-run the algorithm manually.
- Implement it in Java.
- Review edge cases and complexity.
- Revisit the problem until I can explain it without looking at the solution.

The goal is **not to memorize hundreds of LeetCode solutions**. The goal is to master the smaller set of reusable DSA patterns behind them.

## Patterns

### 01. Sliding Window

Use Sliding Window when the problem works with a **contiguous subarray or substring** and neighboring candidate ranges share most of their elements.

#### Fixed-size Sliding Window

Recognition: the required candidate window has a predetermined size, or the problem itself implies one. For anagram/permutation problems, the pattern length determines the window size.

Solved so far:

- Maximum Sum Subarray of Size K
- LeetCode 643 — Maximum Average Subarray I
- LeetCode 1343 — Number of Sub-arrays of Size K and Average >= Threshold
- LeetCode 1456 — Maximum Number of Vowels in a Substring of Given Length
- Maximum Even Count in a Subarray of Size K
- LeetCode 567 — Permutation in String
- LeetCode 438 — Find All Anagrams in a String

Key intuition:

```text
Build/maintain one window
        ↓
remove outgoing state
        +
add incoming state
        ↓
reuse previous work instead of recomputing the whole range
```

For permutations/anagrams:

```text
same characters in any order
        ↓
same character frequencies
        ↓
window size = pattern length
        ↓
Fixed Sliding Window + Frequency Counting
```

#### Variable-size Sliding Window

Recognition: the window expands and shrinks according to a validity constraint rather than staying at a predetermined size.

Solved so far:

- LeetCode 209 — Minimum Size Subarray Sum
- Longest Subarray with At Most Two Distinct Integers
- LeetCode 3 — Longest Substring Without Repeating Characters
- LeetCode 424 — Longest Repeating Character Replacement
- LeetCode 1004 — Max Consecutive Ones III
- LeetCode 76 — Minimum Window Substring

Two important templates:

```text
LONGEST VALID WINDOW
expand right
while INVALID:
    shrink left
update maximum
```

```text
MINIMUM VALID WINDOW
expand right
while VALID:
    update minimum
    shrink left
```

The key is not memorizing those loops. Before coding, determine **what makes the current window valid or invalid** and what minimal state is needed to test that condition.

See [`01-sliding-window/README.md`](01-sliding-window/README.md) for pattern-recognition rules, reusable templates, interview reasoning, solved-problem intuition, and common mistakes.

## Repository roadmap

I will continue expanding this repository as I master more interview patterns, including Two Pointers, Fast & Slow Pointers, Hashing, Prefix Sum, Merge Intervals, Cyclic Sort, Linked List Reversal, Tree BFS/DFS, Binary Search, Heaps, Graphs, Backtracking, and Dynamic Programming.

---

### Ownership

All solutions, notes, explanations, and progress tracking in this repository represent my personal DSA interview-preparation work and learning journey.
