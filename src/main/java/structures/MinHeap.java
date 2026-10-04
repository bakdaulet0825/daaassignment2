package structures;

public class MinHeap {
    private int[] data;
    private int size;

    public long steps = 0;
    public long moves = 0;
    public long comparisons = 0;

    public MinHeap(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public MinHeap() {
        this(10);
    }

    public void insert(int x) {
        if (size == data.length) {
            resize();
        }
        data[size] = x;
        moves++;
        bubbleUp(size);
        size++;
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        steps++;
        return data[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int min = data[0];
        steps++;
        data[0] = data[size - 1];
        moves++;
        size--;
        if (size > 0) {
            bubbleDown(0);
        }
        return min;
    }

    public int size() {
        return size;
    }

    private void bubbleUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            steps += 2;
            comparisons++;
            if (data[i] < data[parent]) {
                int temp = data[i];
                data[i] = data[parent];
                data[parent] = temp;
                moves += 3;
                i = parent;
            } else {
                break;
            }
        }
    }

    private void bubbleDown(int i) {
        while (2 * i + 1 < size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = i;

            steps += 2;
            comparisons++;
            if (data[left] < data[smallest]) {
                smallest = left;
            }

            if (right < size) {
                steps++;
                comparisons++;
                if (data[right] < data[smallest]) {
                    smallest = right;
                }
            }

            if (smallest != i) {
                int temp = data[i];
                data[i] = data[smallest];
                data[smallest] = temp;
                moves += 3;
                i = smallest;
            } else {
                break;
            }
        }
    }

    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            steps++;
            moves++;
        }
        data = newData;
    }
}