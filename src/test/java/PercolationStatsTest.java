import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PercolationStatsTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMean() throws Exception {
        PercolationStats s = new PercolationStats(2,1);
        Assert.assertTrue(s.mean()>0);
    }

    @Test
    public void testStddev() throws Exception {

    }

    @Test
    public void testConfidenceLo() throws Exception {

    }

    @Test
    public void testConfidenceHi() throws Exception {

    }

    @Test
    public void testMain() throws Exception {

    }
}
