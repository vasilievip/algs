import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


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


//    @Test
//    public void test500() throws Exception {
//        RandomizedQueue<String> d = new RandomizedQueue<String>();
//        Assert.assertTrue(d.isEmpty());
//        d.enqueue("b");
//        d.enqueue("b");
//
//        Assert.assertTrue(d.size() == 2);
//        d.enqueue("c");
//        Assert.assertTrue(d.size() == 3);
//        Assert.assertTrue("abc".contains(d.dequeue()));
//        Assert.assertTrue(d.size() == 2);
//        Assert.assertTrue("abc".contains(d.dequeue()));
//        Assert.assertTrue(d.size() == 1);
//        Assert.assertTrue("abc".contains(d.dequeue()));
//        Assert.assertTrue(d.size() == 0);
//        Assert.assertTrue(d.isEmpty());
//    }

    @Test
    public void testIterator() throws Exception {
        RandomizedQueue<String> d = new RandomizedQueue<String>();
        Assert.assertTrue(d.isEmpty());
        d.enqueue("a");
        d.enqueue("b");
        Assert.assertTrue(d.size() == 2);
        d.enqueue("c");
        Assert.assertTrue(d.size() == 3);

        StringBuffer all = new StringBuffer();
        for(String str:d){
            all.append(str);
        }
        Assert.assertEquals("abc", sort(all.toString()));

        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 2);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 1);
        Assert.assertTrue("abc".contains(d.dequeue()));
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());
    }

    private String sort(String str){
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}