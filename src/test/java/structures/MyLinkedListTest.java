package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    public void testAddAndGet() {
        MyLinkedList list = new MyLinkedList();
        list.add(100);
        list.add(200);

        assertEquals(2, list.size());
        assertEquals(100, list.get(0));
        assertEquals(200, list.get(1));
    }

    @Test
    public void testInsertAtHeadAndMiddle() {
        MyLinkedList list = new MyLinkedList();
        list.add(10);
        list.add(30);
        list.add(0, 5);
        list.add(2, 20);

        assertEquals(4, list.size());
        assertEquals(5, list.get(0));
        assertEquals(20, list.get(2));
        assertEquals(30, list.get(3));
    }

    @Test
    public void testRemove() {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(1);
        assertEquals(2, list.size());
        assertEquals(3, list.get(1));
    }
}