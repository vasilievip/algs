import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testOpen() throws Exception {
        Percolation p = new Percolation(2);
        p.open(1,1);
        Assert.assertTrue(p.isOpen(1,1));
        Assert.assertFalse(p.isOpen(1,2));
        Assert.assertFalse(p.isOpen(1,0));
        Assert.assertFalse(p.isOpen(1,3));
        Assert.assertFalse(p.isOpen(2,2));
        p.open(1,2);
        Assert.assertTrue(p.isOpen(1,2));
        p.open(2,2);
        Assert.assertTrue(p.isOpen(2,2));
    }

    @Test
    public void testIsFull() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,1);
        p.open(4,4);
        Assert.assertTrue(p.isFull(1,1));
        Assert.assertFalse(p.isFull(4,4));
        p.open(2,2);
        Assert.assertFalse(p.isFull(4,4));
        p.open(2,1);
        Assert.assertFalse(p.isOpen(1,2));
        PercolationVisualizer.draw(p, 4);
        StdDraw.show(10000);
        Assert.assertTrue(p.isFull(2,2));
    }

    @Test
    public void testPercolates2() throws Exception {
        Percolation p = new Percolation(2);
        p.open(1,1);
        p.open(1,2);
        Assert.assertFalse(p.percolates());
        p.open(2,2);
        Assert.assertTrue(p.percolates());
    }

    @Test
    public void testPercolates4() throws Exception {
        Percolation p = new Percolation(4);
        p.open(1,1);
        Assert.assertFalse(p.percolates());
        Assert.assertFalse(p.isOpen(1,3));
        Assert.assertFalse(p.isOpen(1,4));
        p.open(1,2);
        p.open(2,2);
        Assert.assertFalse(p.percolates());

        //PercolationVisualizer.draw(p, 4);
       // StdDraw.show(10000);
    }
}
