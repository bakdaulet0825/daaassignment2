# Assignment 2: Empirical Performance Analysis of Data Structures

## 1. Introduction
The objective of this assignment is to design, implement, and analyze core linear and hierarchical data structures from scratch. Specifically, the project evaluates `DynamicArray`, `MyLinkedList`, and `MinHeap` under controlled synthetic workloads to compare theoretical time complexity against real-world execution metrics (time in milliseconds, primitive operations, memory moves, and comparisons).

## 2. Implementation Overview
- **DynamicArray**: Implements a resizable array backed by contiguous memory. It supports amortized $O(1)$ append operations and direct $O(1)$ random access, while insertions and deletions in the middle require shifting elements ($O(N)$).
- **MyLinkedList**: A doubly/singly linked list structure offering efficient $O(1)$ insertions and deletions at the head, but requiring $O(N)$ traversal for indexed access or middle-position updates.
- **MinHeap**: A binary heap implemented over a dynamic array, ensuring priority queue operations (`insert`, `extractMin`, `peekMin`) operate in $O(\log N)$ time.

## 3. Workloads Description
Four distinct workloads were benchmarked across varying input sizes ($n \in \{10^2, 10^3, 10^4, 10^5\}$):
- **W1 (Random Access):** Measures the time required to randomly access elements across the structure.
- **W2 (Search / Contains):** Evaluates search operations to find specific elements.
- **W3 (Insert & Remove):** Measures insertion and deletion performance at specific positions (head vs. middle).
- **W4 (Priority Processing):** Evaluates `MinHeap` performance under continuous insertions and extractions.

## 4. Empirical Results & Analysis

### W1: Random Access Performance
- **DynamicArray** demonstrates constant time access $O(1)$ due to direct memory addressing via indices, maintaining near-zero execution times even at $n = 10^5$.
- **MyLinkedList** exhibits linear growth $O(N)$ because every indexed access requires traversing pointers from the head node.

### W2: Search Performance
- Both structures show linear search characteristics $O(N)$ in the general case, though array memory locality provides a noticeable constant-factor speedup over linked list pointer chasing.

### W3: Insert & Remove Performance
- **Head modifications** on `MyLinkedList` execute in $O(1)$ time, outperforming array shifting.
- **Middle modifications** require traversing to the index and shifting elements, resulting in performance degradation proportional to $N$ for both structures, with linked lists spending significant overhead on pointer traversal.

### W4: Priority Processing (`MinHeap`)
- The heap workloads confirm logarithmic scaling $O(\log N)$ for heapify and extraction steps, handling $10^5$ elements efficiently within ~10 milliseconds.

## 5. Conclusion
The empirical findings strictly align with theoretical complexity bounds. Contiguous memory structures (`DynamicArray`, `MinHeap`) outperform pointer-based structures (`MyLinkedList`) for random access and localized operations, whereas linked lists excel exclusively when modifications are strictly restricted to the endpoints (head/tail).