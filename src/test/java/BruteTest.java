import org.junit.Test;

public class BruteTest {

    @Test
    public void test6() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("percolations/input6.txt").getFile()});
    }

    @Test
    public void test8() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("percolations/input8.txt").getFile()});
    }


    @Test
    public void test9() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("percolations/input9.txt").getFile()});
    }


    @Test
    public void testEquidistant() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("percolations/equidistant.txt").getFile()});
    }


}