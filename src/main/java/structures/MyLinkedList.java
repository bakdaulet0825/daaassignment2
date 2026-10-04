package structures;

public class MyLinkedList {
    private static class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public long steps = 0;
    public long moves = 0;
    public long comparisons = 0;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(int x) {
        Node newNode = new Node(x);
        moves++;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            moves++;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Node newNode = new Node(x);
            newNode.next = head;
            moves += 2;
            head = newNode;
            if (size == 0) tail = newNode;
            size++;
            return;
        }
        if (index == size) {
            add(x);
            return;
        }

        Node prev = getNode(index - 1);
        Node newNode = new Node(x);
        newNode.next = prev.next;
        prev.next = newNode;
        moves += 2;
        size++;
    }

    public int get(int index) {
        checkIndex(index);
        Node curr = getNode(index);
        return curr.val;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index == 0) {
            steps++;
            head = head.next;
            moves++;
            if (head == null) tail = null;
            size--;
            return;
        }

        Node prev = getNode(index - 1);
        steps++;
        prev.next = prev.next.next;
        moves++;
        if (prev.next == null) {
            tail = prev;
        }
        size--;
    }

    public boolean contains(int x) {
        Node curr = head;
        while (curr != null) {
            steps++;
            comparisons++;
            if (curr.val == x) {
                return true;
            }
            curr = curr.next;
            moves++;
        }
        return false;
    }

    public int size() {
        return size;
    }

    private Node getNode(int index) {
        Node curr = head;
        for (int i = 0; i < index; i++) {
            steps++;
            curr = curr.next;
        }
        return curr;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}