# DAA Assignment 2: Custom Data Structures & Performance Benchmarking

This project implements fundamental data structures from scratch (without utilizing standard Java collections like java.util.ArrayList or java.util.LinkedList) and evaluates their empirical performance across various workloads.

## Project Structure

- src/main/java/structures/ — Custom implementations (DynamicArray, MyLinkedList, MinHeap).
- src/main/java/benchmark/ — Benchmark runner measuring execution time, steps, moves, and comparisons.
- src/test/java/structures/ — JUnit 5 unit tests verifying correctness.
- results/ — Raw CSV data and generated performance plots (plots/).

## Prerequisites

- Java 21 (OpenJDK)
- Maven
- Python 3 (with pandas and matplotlib installed for plotting)

## How to Run

1. Run Unit Tests:
   mvn test

2. Run Benchmarks (generates results/results.csv):
   mvn clean compile exec:java

3. Generate Performance Plots:
   python generate_plots.py