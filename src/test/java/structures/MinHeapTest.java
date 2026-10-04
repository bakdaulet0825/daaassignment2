package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testInsertAndExtractMin() {
        MinHeap heap = new MinHeap();
        heap.insert(20);
        heap.insert(10);
        heap.insert(30);
        heap.insert(5);

        assertEquals(4, heap.size());
        assertEquals(5, heap.extractMin());
        assertEquals(10, heap.extractMin());
        assertEquals(20, heap.extractMin());
        assertEquals(30, heap.extractMin());
        assertEquals(0, heap.size());
    }

    @Test
    public void testPeekMin() {
        MinHeap heap = new MinHeap();
        heap.insert(50);
        heap.insert(15);

        assertEquals(15, heap.peekMin());
        assertEquals(2, heap.size()); // Размер не должен меняться при peek
    }
}