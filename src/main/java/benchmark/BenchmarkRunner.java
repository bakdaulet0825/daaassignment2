package benchmark;

import structures.DynamicArray;
import structures.MyLinkedList;
import structures.MinHeap;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        File resultsDir = new File("results");
        if (!resultsDir.exists()) {
            resultsDir.mkdir();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("results/results.csv"))) {
            writer.println("workload,variant,structure,n,time_ms,steps,moves,comparisons");

            for (int n : sizes) {
                runW1(n, writer);
                runW2(n, writer);
                runW3(n, writer);
                runW4(n, writer);
            }
            System.out.println("Benchmark finished successfully. Results saved to results/results.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void runW1(int n, PrintWriter writer) {
        MetricsResult resArray = benchmarkRun(5, () -> {
            DynamicArray da = new DynamicArray();
            Random rnd = new Random(42);
            for (int i = 0; i < n; i++) da.add(rnd.nextInt());
            da.steps = 0; da.moves = 0; da.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                da.get(rnd.nextInt(n));
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, da.steps, da.moves, da.comparisons);
        });
        writer.printf("W1,none,DynamicArray,%d,%.3f,%d,%d,%d%n", n, resArray.time, resArray.steps, resArray.moves, resArray.comparisons);

        MetricsResult resList = benchmarkRun(5, () -> {
            MyLinkedList ml = new MyLinkedList();
            Random rnd = new Random(42);
            for (int i = 0; i < n; i++) ml.add(rnd.nextInt());
            ml.steps = 0; ml.moves = 0; ml.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 10000; i++) {
                ml.get(rnd.nextInt(n));
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, ml.steps, ml.moves, ml.comparisons);
        });
        writer.printf("W1,none,MyLinkedList,%d,%.3f,%d,%d,%d%n", n, resList.time, resList.steps, resList.moves, resList.comparisons);
    }

    private static void runW2(int n, PrintWriter writer) {
        MetricsResult resArray = benchmarkRun(5, () -> {
            DynamicArray da = new DynamicArray();
            Random rnd = new Random(42);
            ArrayList<Integer> values = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int val = rnd.nextInt();
                da.add(val);
                values.add(val);
            }
            da.steps = 0; da.moves = 0; da.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                int query = (i % 2 == 0) ? values.get(rnd.nextInt(n)) : rnd.nextInt();
                da.contains(query);
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, da.steps, da.moves, da.comparisons);
        });
        writer.printf("W2,none,DynamicArray,%d,%.3f,%d,%d,%d%n", n, resArray.time, resArray.steps, resArray.moves, resArray.comparisons);

        MetricsResult resList = benchmarkRun(5, () -> {
            MyLinkedList ml = new MyLinkedList();
            Random rnd = new Random(42);
            ArrayList<Integer> values = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int val = rnd.nextInt();
                ml.add(val);
                values.add(val);
            }
            ml.steps = 0; ml.moves = 0; ml.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                int query = (i % 2 == 0) ? values.get(rnd.nextInt(n)) : rnd.nextInt();
                ml.contains(query);
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, ml.steps, ml.moves, ml.comparisons);
        });
        writer.printf("W2,none,MyLinkedList,%d,%.3f,%d,%d,%d%n", n, resList.time, resList.steps, resList.moves, resList.comparisons);
    }

    private static void runW3(int n, PrintWriter writer) {
        runW3Variant(n, writer, "head", 0);
        runW3Variant(n, writer, "middle", n / 2);
    }

    private static void runW3Variant(int n, PrintWriter writer, String variant, int index) {
        MetricsResult resArray = benchmarkRun(5, () -> {
            DynamicArray da = new DynamicArray();
            Random rnd = new Random(42);
            for (int i = 0; i < n; i++) da.add(rnd.nextInt());
            da.steps = 0; da.moves = 0; da.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                int targetIdx = Math.min(index, da.size());
                da.add(targetIdx, rnd.nextInt());
                da.remove(targetIdx);
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, da.steps, da.moves, da.comparisons);
        });
        writer.printf("W3,%s,DynamicArray,%d,%.3f,%d,%d,%d%n", variant, n, resArray.time, resArray.steps, resArray.moves, resArray.comparisons);

        MetricsResult resList = benchmarkRun(5, () -> {
            MyLinkedList ml = new MyLinkedList();
            Random rnd = new Random(42);
            for (int i = 0; i < n; i++) ml.add(rnd.nextInt());
            ml.steps = 0; ml.moves = 0; ml.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < 1000; i++) {
                int targetIdx = Math.min(index, ml.size());
                ml.add(targetIdx, rnd.nextInt());
                ml.remove(targetIdx);
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, ml.steps, ml.moves, ml.comparisons);
        });
        writer.printf("W3,%s,MyLinkedList,%d,%.3f,%d,%d,%d%n", variant, n, resList.time, resList.steps, resList.moves, resList.comparisons);
    }

    private static void runW4(int n, PrintWriter writer) {
        MetricsResult resHeap = benchmarkRun(5, () -> {
            MinHeap heap = new MinHeap();
            Random rnd = new Random(42);
            for (int i = 0; i < n; i++) heap.insert(rnd.nextInt());
            heap.steps = 0; heap.moves = 0; heap.comparisons = 0;
            long start = System.nanoTime();
            for (int i = 0; i < n; i++) {
                heap.extractMin();
            }
            long time = System.nanoTime() - start;
            return new MetricsData(time / 1_000_000.0, heap.steps, heap.moves, heap.comparisons);
        });
        writer.printf("W4,none,MinHeap,%d,%.3f,%d,%d,%d%n", n, resHeap.time, resHeap.steps, resHeap.moves, resHeap.comparisons);
    }

    private static MetricsResult benchmarkRun(int runs, java.util.function.Supplier<MetricsData> task) {
        ArrayList<Double> times = new ArrayList<>();
        long steps = 0, moves = 0, comparisons = 0;

        for (int i = 0; i < runs; i++) {
            MetricsData data = task.get();
            if (i == 0) continue;
            times.add(data.time);
            steps = data.steps;
            moves = data.moves;
            comparisons = data.comparisons;
        }

        Collections.sort(times);
        double medianTime = times.get(times.size() / 2);
        return new MetricsResult(medianTime, steps, moves, comparisons);
    }

    private static class MetricsData {
        double time;
        long steps, moves, comparisons;
        MetricsData(double time, long steps, long moves, long comparisons) {
            this.time = time;
            this.steps = steps;
            this.moves = moves;
            this.comparisons = comparisons;
        }
    }

    private static class MetricsResult {
        double time;
        long steps, moves, comparisons;
        MetricsResult(double time, long steps, long moves, long comparisons) {
            this.time = time;
            this.steps = steps;
            this.moves = moves;
            this.comparisons = comparisons;
        }
    }
}