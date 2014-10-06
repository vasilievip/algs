import org.junit.Test;

public class FastTest {

    @Test
    public void test6() throws Exception {
        Fast.main(new String[]{FastTest.class.getResource("input6.txt").getFile()});
    }
    @Test
    public void testEquidistant() throws Exception {
        Fast.main(new String[]{FastTest.class.getResource("equidistant.txt").getFile()});
    }

    @Test
    public void test8() throws Exception {
        Fast.main(new String[]{FastTest.class.getResource("input8.txt").getFile()});
    }

    @Test
    public void test9() throws Exception {
        Fast.main(new String[]{FastTest.class.getResource("input9.txt").getFile()});
    }

}