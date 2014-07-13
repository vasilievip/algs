import org.junit.Assert;
import org.junit.Test;


public class RandomizedQueueTest {

    @Test
    public void testAddFirst() throws Exception {
        RandomizedQueue<String> d = new RandomizedQueue<String>();
        Assert.assertTrue(d.isEmpty());
        d.enqueue("a");
        d.enqueue("b");
        Assert.assertTrue(d.size() == 2);
        d.enqueue("c");
        Assert.assertTrue(d.size() == 3);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 2);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 1);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());
    }

    @Test
    public void testIterator() throws Exception {
        RandomizedQueue<String> d = new RandomizedQueue<String>();
        Assert.assertTrue(d.isEmpty());
        d.enqueue("a");
        d.enqueue("b");
        Assert.assertTrue(d.size() == 2);
        d.enqueue("c");
        Assert.assertTrue(d.size() == 3);

        for(String str:d){
            System.out.println(str);
        }

        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 2);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 1);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());
    }

}