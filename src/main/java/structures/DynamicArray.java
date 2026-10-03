package structures;

public class DynamicArray {
    private int[] data;
    private int size;

    public long steps = 0;
    public long moves = 0;
    public long comparisons = 0;

    public DynamicArray(int capacity) {
        if (capacity < 0) capacity = 10;
        this.data = new int[capacity];
        this.size = 0;
    }

    public DynamicArray() {
        this(10);
    }

    public void add(int x) {
        if (size == data.length) {
            resize();
        }
        data[size] = x;
        moves++;
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            steps++;
            moves++;
        }
        data[index] = x;
        moves++;
        size++;
    }

    public int get(int index) {
        checkIndex(index);
        steps++;
        return data[index];
    }

    public void remove(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            steps++;
            moves++;
        }
        size--;
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            steps++;
            comparisons++;
            if (data[i] == x) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private void resize() {
        int newCapacity = data.length * 2;
        int[] newData = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            steps++;
            moves++;
        }
        data = newData;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}