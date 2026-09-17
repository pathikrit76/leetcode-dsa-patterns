# 01 — Sliding Window

Sliding Window is one of the most important patterns for array and string interviews. The purpose of this section is not to memorize a template, but to learn how to **derive the window logic from the problem statement**.

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
- "smallest/minimum substring satisfying..."
- "at most k..."
- "without repeating..."
- "contains a permutation/anagram..."

Sliding Window is generally **not** the first choice when the required elements do not need to be contiguous.

Before writing code, ask:

```text
1. Is the answer contiguous?
2. Is the window size fixed or variable?
3. What information tells me whether the window is valid?
4. What changes when right enters?
5. What must be undone when left leaves?
```

---

# 2. Fixed-size Sliding Window

## Recognition

The problem explicitly gives a window length `k`, or the required object implies a fixed length.

Examples:

- maximum sum of any `k` consecutive elements
- maximum vowels in a substring of length `k`
- number of size-`k` windows satisfying a condition
- substring that is a permutation/anagram of a pattern of length `k`

## Mental model

Two adjacent windows of size `k` share `k - 1` elements.

Instead of recalculating the whole next window:

```text
nextState = currentState - outgoingElement + incomingElement
```

The maintained state does not have to be a sum. It can be a vowel count, even count, frequency array/map, etc.

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

## Anagram / permutation recognition

A permutation changes **order**, but not character counts.

If the pattern has length `m`, every permutation also has length `m`:

```text
permutation/anagram
       ↓
same frequency of characters
       ↓
substring must have length m
       ↓
Fixed Sliding Window of size m
       +
Frequency Counting
```

This is the central idea behind LC567 and LC438.

## Complexity

- Time: usually `O(n)` — each element enters and leaves the window once.
- Extra space: depends on the state; a 26-character lowercase frequency array is `O(1)`.

## Common mistakes

- Forgetting to evaluate the first window.
- Using the wrong outgoing index; it is usually `right - k`.
- Recalculating all `k` elements for every window, producing `O(n*k)`.
- Initializing a maximum to `0` when values may all be negative.
- Generating permutations when only frequency equality is needed.

## Solved

- Maximum Sum Subarray of Size K
- LeetCode 643 — Maximum Average Subarray I
- LeetCode 1343 — Count Size-K Subarrays with Average >= Threshold
- LeetCode 1456 — Maximum Vowels in a Size-K Substring
- Maximum Even Count in a Size-K Subarray
- LeetCode 567 — Permutation in String
- LeetCode 438 — Find All Anagrams in a String

### LC567 — Permutation in String

Recognition:

```text
Does s2 contain a permutation of s1?
        ↓
Every permutation has length s1.length()
        ↓
Fixed window + frequency comparison
```

Maintain the first window's frequencies. Every slide adds one incoming character and removes one outgoing character. Return immediately when the window frequencies equal the pattern frequencies.

### LC438 — Find All Anagrams in a String

Uses the same core pattern as LC567:

```text
Fixed window = p.length()
+ frequency state
```

The difference is the required output: instead of returning on the first match, record the starting index of **every** matching window.

---

# 3. Variable-size Sliding Window

The window size is not predetermined. `right` expands the window and `left` shrinks it according to a validity condition.

There are two major interview forms.

## A. Minimum valid window

Goal: find the **smallest** window satisfying a condition.

### Mental model

```text
expand right
while window is VALID:
    record the current answer
    shrink left
```

Why shrink a valid window? Because once a valid candidate exists, the only way to discover whether a **smaller valid candidate** exists ending at the same `right` is to move `left` forward.

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

Examples: LC209 and LC76.

## B. Longest valid window

Goal: find the **largest** window satisfying a constraint.

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

Examples: LC3, LC424, LC1004, and At Most Two Distinct.

## The interview distinction to remember

| Goal | Shrink while | Update answer |
|---|---|---|
| Minimum valid window | window is **valid** | before shrinking |
| Longest valid window | window is **invalid** | after validity is restored |

Do not memorize only the table. Ask why: minimum problems deliberately try to make a valid window smaller; longest problems only shrink when a constraint has been broken.

---

# 4. Choosing the smallest useful window state

Ask:

> What is the minimum information I need to decide whether this window is valid?

| Problem | State | Validity idea |
|---|---|---|
| Maximum sum of size K | `windowSum` | fixed size |
| Maximum vowels | `vowelCount` | fixed size |
| Permutation / anagram | frequency array | frequencies match |
| At most 2 distinct | frequency `HashMap` | `map.size() <= 2` |
| No repeating characters | `HashSet` / frequency map | no duplicate |
| Character replacement | frequency + `maxFrequency` | `windowLength - maxFrequency <= k` |
| Max Consecutive Ones III | `zeroCount` | `zeroCount <= k` |
| Minimum Window Substring | requirement map + missing count | `count == 0` |

A common mistake is using a more complicated state than necessary. LC1004, for example, only needs to know how many zeros are in the window; a general frequency map is unnecessary.

---

# 5. Solved variable-window problems and intuition

### LeetCode 209 — Minimum Size Subarray Sum

Recognition: contiguous subarray + minimum length + sum constraint + positive numbers.

```text
while windowSum >= target:
    update minimum
    shrink
```

Positive numbers give the monotonic behavior needed for this strategy: expanding cannot decrease the sum, and shrinking cannot increase it.

### Longest Subarray with At Most Two Distinct Integers

Recognition: longest contiguous range + "at most 2 distinct".

State: `HashMap<value, frequency>`.

```text
INVALID when map.size() > 2
```

A frequency map is necessary because removing one occurrence does not necessarily remove that value from the window. Delete the key only when its frequency reaches zero.

### LeetCode 3 — Longest Substring Without Repeating Characters

Recognition: longest substring + uniqueness constraint.

When the incoming character already exists, repeatedly remove characters from the left until that duplicate disappears. Once valid again, update the maximum length.

### LeetCode 424 — Longest Repeating Character Replacement

Recognition: longest substring + at most `k` modifications + make all characters equal.

```text
characters that must change
= windowLength - maxFrequency
```

Therefore:

```text
INVALID when windowLength - maxFrequency > k
```

### LeetCode 1004 — Max Consecutive Ones III

Translate the wording first:

```text
flip at most k zeros
        ↓
window may contain at most k zeros
```

So the entire validity condition is simply:

```text
zeroCount <= k
```

This becomes the standard longest-valid-window template:

```text
expand right
while zeroCount > k:
    shrink left
update maximum
```

### LeetCode 76 — Minimum Window Substring

Recognition:

```text
minimum substring
+ must contain all required characters including duplicates
        ↓
Variable Sliding Window
+ Frequency Map
+ shrink while VALID
```

One useful way to model the frequency map is:

```text
frequency > 0  -> still missing copies
frequency = 0  -> exactly enough copies
frequency < 0  -> extra copies in the current window
```

Let `count` represent the total number of required character occurrences still missing. Start with:

```text
count = t.length()
```

When expanding:

```text
if incoming frequency > 0:
    count--
frequency-- ALWAYS
```

The unconditional decrement is important because extra copies must become negative.

When `count == 0`, the window is valid. Because we want the minimum, shrink it:

```text
while count == 0:
    update minimum
    remove left
```

When removing a relevant left character:

```text
frequency = -1 before removal
-1 -> 0
removed an EXTRA copy
count unchanged

frequency = 0 before removal
0 -> 1
removed a REQUIRED copy
count++
window becomes invalid
```

That `-1 / 0 / +1` interpretation is the main invariant behind this implementation.

---

# 6. Why the nested `while` can still be O(n)

A common interview question is: "There is a `while` inside a `for`. Why isn't it O(n²)?"

Because the pointers never move backward:

- `right` advances at most `n` times.
- `left` advances at most `n` times across the **entire** algorithm.

Total pointer movement is bounded by roughly `2n`, so the traversal is `O(n)`. This is amortized analysis.

---

# 7. Pattern comparison cheat sheet

```text
FIXED WINDOW
"size k" / "length k"
        -> maintain exactly k elements

ANAGRAM / PERMUTATION
"same chars in any order"
        -> fixed window = pattern length
        -> frequency counting

LONGEST VALID WINDOW
"longest ... at most / without ..."
        -> expand
        -> shrink while INVALID
        -> update maximum

MINIMUM VALID WINDOW
"smallest/minimum ... satisfying ..."
        -> expand until valid
        -> shrink while VALID
        -> update minimum before removing left
```

---

# 8. Interview checklist

Before coding, say out loud:

1. Is the answer a contiguous range?
2. Is the window fixed or variable?
3. If fixed, what determines its size?
4. What state determines validity?
5. Can I use a simpler state instead of a HashMap?
6. What enters when `right` moves?
7. What leaves when `left` moves?
8. Am I shrinking while valid or while invalid?
9. When exactly should I update the answer?
10. Why is the algorithm O(n)?

If you can answer these before coding, you understand the pattern rather than memorizing a template.
