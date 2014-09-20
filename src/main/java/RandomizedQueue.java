import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int capacity = 2;
    private int tail = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[capacity];
        tail = -1;

    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return tail + 1;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        tail++;
        items[tail] = item;
        resize();
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int random = 0;
        if (size() > 1) {
            random = StdRandom.uniform(0, size());
        }
        Item tailItem = items[tail];
        Item randItem = items[random];
        items[tail] = null;
        items[random] = tailItem;
        tail--;
        items[tail+1] = null;
        resize();
        return randItem;
    }

    private void resize() {
        if (size() > 0.8 * capacity) {
            capacity = capacity * 2;
            Item[] items2 = (Item[]) new Object[capacity];
            System.arraycopy(items, 0, items2, 0, size());
            items = items2;
        }
        if (size() < 0.2 * capacity) {
            capacity = capacity / 2;
            Item[] items2 = (Item[]) new Object[capacity];
            System.arraycopy(items, 0, items2, 0, size());
            items = items2;
        }

    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size() == 1) {
            return items[0];
        }
        return items[StdRandom.uniform(0, size())];
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
            itemsI = (Item[]) new Object[size()];
            System.arraycopy(items, 0, itemsI, 0, size());
            StdRandom.shuffle(itemsI);
        }

        @Override
        public boolean hasNext() {
            return current < size() - 1;
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