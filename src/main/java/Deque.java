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
        Node node = new Node(item, head, null);
        head = node;
        if (tail == null)
            tail = head;
        size++;
    }

    public void addLast(Item item)           // insert the item at the end
    {
        if (item == null) throw new NullPointerException();
        Node node = new Node(item, null, tail);
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
        if (tail == head)
            tail = head.getNext();
        head = head.getNext();
        //head.setPrev(null);
        //head.getNext().setPrev(head);
        size--;
        return oldHead.item;
    }

    public Item removeLast()                 // delete and return the item at the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        Node oldTail = tail;
        if (tail == head)
            head = tail.getPrev();
        tail = tail.getPrev();
        //tail.setPrev(tail.getPrev());
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

        private Node(Item item, Node next, Node prev) {
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

        private Node current = new Node(null, head, null);

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            current = current.getNext();
            return current.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}