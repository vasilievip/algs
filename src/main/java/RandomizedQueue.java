import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int capacity = 2;
    private int head = 0;
    private int tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[capacity];
        head = 0;
        tail = 0;

    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return tail - head;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        items[tail] = item;
        tail++;
        resize();
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        resize();
        return items[head++];
    }

    private void resize() {
        if (tail >= 2 / 3 * capacity) {
            capacity = capacity * 2;
            Item[] items2 = (Item[]) new Object[capacity];
//            System.arraycopy(items, 0, items2, 0, tail);
            for (int i = 0; i < tail; i++) {
                items2[i] = items[i];
                items[i] = null;
            }
            items = items2;
        }
        if (tail <= 1 / 3 * capacity) {
            capacity = capacity / 2;
            Item[] items2 = (Item[]) new Object[capacity];
            for (int i = 0; i < tail; i++) {
                items2[i] = items[i];
                items[i] = null;
            }
//            System.arraycopy(items, 0, items2, 0, tail);
            items = items2;
        }

    }

    // return (but do not delete) a random item
    public Item sample() {
        int sample = head;
        if (isEmpty()) throw new NoSuchElementException();
        if (head < tail) {
            sample = StdRandom.uniform(head, tail);
        }
        return items[sample];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RSIterator();
    }

    // unit testing
    public static void main(String[] args) {
    }

    private class RSIterator implements Iterator<Item> {

        private int current = -1;
        private Item[] itemsI;

        private RSIterator() {
            itemsI = (Item[]) new Object[capacity];
            System.arraycopy(items, 0, itemsI, 0, tail);
            for (int i = 0; i < tail; i++) {
                Item it = itemsI[i];
                int j = 0;
                if (i > 0) {
                    j = StdRandom.uniform(0, i);
                }
                itemsI[i] = itemsI[j];
                itemsI[j] = it;
            }
        }

        @Override
        public boolean hasNext() {
            return current < tail - 1;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return itemsI[++current];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}