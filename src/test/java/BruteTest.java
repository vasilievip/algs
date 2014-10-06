import org.junit.Test;

import static org.junit.Assert.*;

public class BruteTest {

    @Test
    public void test6() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("input6.txt").getFile()});
    }

    @Test
    public void test8() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("input8.txt").getFile()});
    }


    @Test
    public void test9() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("input9.txt").getFile()});
    }


    @Test
    public void testEquidistant() throws Exception {
        Brute.main(new String[]{BruteTest.class.getResource("equidistant.txt").getFile()});
    }


}