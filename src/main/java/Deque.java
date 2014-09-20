import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int size = 0;

    public Deque()  // construct an empty deque
    {
        head = null;
        tail = null;
    }

    public boolean isEmpty()// is the deque empty?
    {
        return size == 0;
    }

    public int size()// return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item)// insert the item at the front
    {
        if (item == null) throw new NullPointerException();
        Node node = new Node(item, null, head);
        if (head != null)
            head.setPrev(node);
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }

    public void addLast(Item item)           // insert the item at the end
    {
        if (item == null) throw new NullPointerException();
        Node node = new Node(item, tail, null);
        if (tail != null)
            tail.setNext(node);
        tail = node;
        if (head == null)
            head = tail;

        size++;
    }

    public Item removeFirst()// delete and return the item at the front
    {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldHead = head;
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        size--;
        return oldHead.item;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldTail = tail;
        if (tail == head) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        size--;
        return oldTail.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args)   // unit testing
    {

    }

    private class Node {

        private Item item;
        private Node next;
        private Node prev;

        private Node(Item item, Node prev, Node next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item i) {
            this.item = i;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            this.next = n;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node p) {
            this.prev = p;
        }
    }


    private class DequeIterator implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item res = current.item;
            current = current.getNext();
            return res;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}