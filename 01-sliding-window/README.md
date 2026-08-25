# 01 — Sliding Window

Sliding Window is one of the most important patterns for array and string interviews.

## 1. How to recognize Sliding Window

Look for these clues:

- The problem asks about a **contiguous** subarray or substring.
- You need a maximum, minimum, count, or best range.
- Neighboring candidate ranges overlap heavily.
- Recalculating every range from scratch would repeat work.

Typical phrases:

- "subarray of size k"
- "substring of length k"
- "longest substring/subarray satisfying..."
- "smallest subarray satisfying..."
- "at most k..."
- "without repeating..."

Sliding Window is generally **not** the first choice when the required elements do not need to be contiguous.

---

# 2. Fixed-size Sliding Window

## Recognition

The problem explicitly gives a window length `k`.

Examples:

- maximum sum of any `k` consecutive elements
- maximum vowels in a substring of length `k`
- number of size-`k` windows satisfying a condition

## Mental model

Two adjacent windows of size `k` share `k - 1` elements.

Instead of recalculating the whole next window:

```text
nextState = currentState - outgoingElement + incomingElement
```

The maintained state does not have to be a sum. It can be a vowel count, even count, frequency map, etc.

## Java template

```java
// Build first window
for (int i = 0; i < k; i++) {
    add(arr[i]);
}
updateAnswer();

// Slide
for (int right = k; right < arr.length; right++) {
    remove(arr[right - k]);
    add(arr[right]);
    updateAnswer();
}
```

## Complexity

- Time: `O(n)` — each element enters and leaves the window at most once.
- Extra space: depends on the state being maintained; often `O(1)`.

## Common mistakes

- Forgetting to evaluate the first window.
- Using the wrong outgoing index; it is usually `right - k`.
- Recalculating all `k` elements for every window, producing `O(n*k)`.
- Initializing a maximum to `0` when values may all be negative.

## Solved

- Maximum Sum Subarray of Size K
- LeetCode 643 — Maximum Average Subarray I
- LeetCode 1343 — Count Size-K Subarrays with Average >= Threshold
- LeetCode 1456 — Maximum Vowels in a Size-K Substring
- Maximum Even Count in a Size-K Subarray

---

# 3. Variable-size Sliding Window

The window size is not predetermined. `right` expands the window and `left` shrinks it according to a validity condition.

There are two major interview forms.

## A. Minimum valid window

Goal: find the **smallest** window satisfying a condition.

Example: LeetCode 209 — smallest positive-integer subarray whose sum is at least `target`.

### Mental model

```text
expand right
while window is VALID:
    record the current answer
    shrink left
```

### Template

```java
int left = 0;

for (int right = 0; right < n; right++) {
    add(right);

    while (windowIsValid()) {
        updateMinimum(right - left + 1);
        remove(left);
        left++;
    }
}
```

Why shrink a valid window? Because we are searching for an even **smaller valid** answer.

## B. Longest valid window

Goal: find the **largest** window satisfying a constraint.

Examples:

- at most two distinct integers
- no repeated characters
- at most `k` replacements

### Mental model

```text
expand right
while window is INVALID:
    shrink left
update maximum once valid
```

### Template

```java
int left = 0;

for (int right = 0; right < n; right++) {
    add(right);

    while (windowIsInvalid()) {
        remove(left);
        left++;
    }

    maxLength = Math.max(maxLength, right - left + 1);
}
```

## The interview distinction to remember

| Goal | Shrink while | Update answer |
|---|---|---|
| Minimum valid window | window is **valid** | before shrinking |
| Longest valid window | window is **invalid** | after validity is restored |

---

# 4. Why the nested `while` can still be O(n)

A common interview question is: "There is a `while` inside a `for`. Why isn't it O(n²)?"

Because the pointers never move backward:

- `right` advances at most `n` times.
- `left` advances at most `n` times across the **entire** algorithm.

Total pointer movement is bounded by roughly `2n`, so the traversal is `O(n)`. This is amortized analysis.

---

# 5. Choosing the window state

Ask: **What information is required to decide whether my current window is valid?**

Examples:

| Problem | State |
|---|---|
| Maximum sum of size K | `windowSum` |
| Maximum vowels | `vowelCount` |
| At most 2 distinct values | frequency `HashMap` |
| No repeating characters | `HashSet` or frequency map |
| Character replacement | frequency map + `maxFrequency` |

The pattern is the same; only the maintained state and validity rule change.

---

# 6. Solved variable-window problems

### LeetCode 209 — Minimum Size Subarray Sum

Recognition: contiguous subarray + minimum length + sum constraint + positive numbers.

Core rule:

```text
while windowSum >= target:
    update minimum
    shrink
```

Important assumption: with positive numbers, expanding cannot decrease the sum and shrinking cannot increase it. That monotonic behavior makes this window strategy valid.

### Longest Subarray with At Most Two Distinct Integers

Recognition: longest contiguous range + "at most 2 distinct".

State: `HashMap<value, frequency>`.

Invalid when:

```text
map.size() > 2
```

A frequency map is necessary because removing one occurrence does not necessarily remove that value from the window. Remove the map key only when its frequency reaches zero.

### LeetCode 3 — Longest Substring Without Repeating Characters

Recognition: longest substring + uniqueness constraint.

State: `HashSet<Character>`.

When the incoming character already exists, repeatedly remove characters from the left until the duplicate disappears. Then add the incoming character and update the maximum length.

### LeetCode 424 — Longest Repeating Character Replacement

Recognition: longest substring + at most `k` modifications + make all characters equal.

Key equation:

```text
replacementsNeeded = windowLength - maxFrequency
```

Invalid when:

```text
windowLength - maxFrequency > k
```

For the standard optimized solution, `maxFrequency` can represent the highest frequency observed while expanding; it does not need to be recomputed on every shrink.

---

# 7. Interview checklist

Before coding, say out loud:

1. Is the answer a contiguous range?
2. Is the window fixed or variable?
3. What state determines validity?
4. What enters when `right` moves?
5. What leaves when `left` moves?
6. Am I shrinking while valid or while invalid?
7. When exactly should I update the answer?
8. Why is the algorithm O(n)?

If you can answer all eight before coding, you understand the pattern rather than memorizing a template.
