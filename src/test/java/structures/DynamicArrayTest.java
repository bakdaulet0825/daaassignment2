package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    @Test
    public void testAddAndGet() {
        DynamicArray arr = new DynamicArray();
        arr.add(10);
        arr.add(20);
        arr.add(30);

        assertEquals(3, arr.size());
        assertEquals(10, arr.get(0));
        assertEquals(20, arr.get(1));
        assertEquals(30, arr.get(2));
    }

    @Test
    public void testInsertAndRemove() {
        DynamicArray arr = new DynamicArray();
        arr.add(1);
        arr.add(3);
        arr.add(1, 2);

        assertEquals(3, arr.size());
        assertEquals(2, arr.get(1));

        arr.remove(1);
        assertEquals(2, arr.size());
        assertEquals(3, arr.get(1));
    }

    @Test
    public void testContains() {
        DynamicArray arr = new DynamicArray();
        arr.add(5);
        arr.add(15);

        assertTrue(arr.contains(15));
        assertFalse(arr.contains(100));
    }
}