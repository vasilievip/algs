import org.junit.Test;

import static org.junit.Assert.*;

public class SolverTest {

    @Test
    public void testMain() throws Exception {
        Solver.main(new String[]{getFilePath("puzzle/puzzle04.txt")});
    }

    public static String getFilePath(String resourceName) {
        return SolverTest.class.getResource(resourceName).getFile();
    }

    public static int[][] blocksFromResource(String resourceName){
        return Solver.blocksFromFile(SolverTest.getFilePath(resourceName));
    }

    public static String textFromResource(String resourceName){
        return new In(SolverTest.getFilePath(resourceName)).readAll();
    }
}