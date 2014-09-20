import org.junit.Assert;
import org.junit.Test;


public class DequeTest {


    @Test
    public void testAddFirst() throws Exception {
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addFirst("a");
        d.addFirst("b");
        Assert.assertTrue(d.size() == 2);
        d.addFirst("c");
        Assert.assertTrue(d.size() == 3);
        Assert.assertEquals(d.removeFirst(), "c");
        Assert.assertTrue(d.size() == 2);
        Assert.assertEquals(d.removeFirst(), "b");
        Assert.assertTrue(d.size() == 1);
        Assert.assertEquals(d.removeFirst(), "a");
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());


    }

    @Test
    public void testAddLast() throws Exception {
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addLast("a");
        d.addLast("b");
        Assert.assertTrue(d.size() == 2);
        d.addLast("c");
        Assert.assertTrue(d.size() == 3);
        Assert.assertEquals(d.removeLast(), "c");
        Assert.assertTrue(d.size() == 2);
        Assert.assertEquals(d.removeLast(), "b");
        Assert.assertTrue(d.size() == 1);
        Assert.assertEquals(d.removeLast(), "a");
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());


    }

    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addFirst("a");
        d.addFirst("b");
        Assert.assertTrue(d.size() == 2);
        d.addFirst("c");
        Assert.assertTrue(d.size() == 3);
        Assert.assertEquals(d.removeLast(), "a");
        Assert.assertTrue(d.size() == 2);
        Assert.assertEquals(d.removeLast(), "b");
        Assert.assertTrue(d.size() == 1);
        Assert.assertEquals(d.removeLast(), "c");
        Assert.assertTrue(d.size() == 0);
        Assert.assertTrue(d.isEmpty());
    }

    @Test
    public void testIterator1() throws Exception {
        String str = "";
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addLast("a");
        d.addLast("b");
        d.addLast("c");
        Assert.assertTrue(d.size() == 3);
        for (String i : d) {
            str = str + i;
        }
        Assert.assertEquals(str, "abc");
    }

    @Test
    public void testIterator2() throws Exception {
        String str = "";
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addFirst("a");
        d.addFirst("b");
        d.addFirst("c");
        Assert.assertTrue(d.size() == 3);
        for (String i : d) {
            str = str + i;
        }
        Assert.assertEquals(str, "cba");
    }

    @Test
    public void testIterator3() throws Exception {
        String str = "";
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addFirst("a");
        d.addLast("b");
        d.addLast("c");
        Assert.assertTrue(d.size() == 3);
        for (String i : d) {
            str = str + i;
        }
        Assert.assertEquals(str, "abc");
    }

    @Test
    public void testIterator4() throws Exception {
        String str = "";
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addLast("a");
        d.addFirst("b");
        d.addFirst("c");
        Assert.assertTrue(d.size() == 3);
        for (String i : d) {
            str = str + i;
        }
        Assert.assertEquals(str, "cba");
    }


    @Test
    public void testIterator5() throws Exception {
        String str = "";
        Deque<String> d = new Deque<String>();
        Assert.assertTrue(d.isEmpty());
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.addLast("1");
        d.addFirst("1");
        d.addFirst("1");
        d.addLast("1");
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeLast();
        Assert.assertTrue(d.size() == 0);
        for (String i : d) {
            str = str + i;
        }
        Assert.assertEquals("", str);
    }
}