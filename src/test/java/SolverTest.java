import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SolverTest {

    @Test
    public void testMain() throws Exception {
        Solver.main(new String[]{getFilePath("puzzle/puzzle04.txt")});
    }

    @Test
    public void testMoves() throws Exception {
        Solver solver = new Solver(board("puzzle/puzzle04.txt"));
        assertThat(solver.moves(), is(4));
    }

    @Test
    public void testSolution() throws Exception {
        Solver solver = new Solver(board("puzzle/puzzle04.txt"));
        List<Board> solution = new ArrayList<>();
        solver.solution().forEach((item) -> solution.add(item));
        assertThat(solution.size(), is(5));
        assertThat(solution.get(0), is(board("puzzle/puzzle04.txt")));
        assertThat(solution.get(1), is(board("puzzle/puzzle04_1.txt")));
        assertThat(solution.get(2), is(board("puzzle/puzzle04_2.txt")));
        assertThat(solution.get(3), is(board("puzzle/puzzle04_3.txt")));
        assertThat(solution.get(4), is(board("puzzle/puzzle04_4.txt")));

    }

    @Test
    public void testUnsolvable() throws Exception {
        Solver solver = new Solver(board("puzzle/puzzle-unsolvable3x3.txt"));
        assertThat(solver.solution(), is(nullValue()) );
        assertThat(solver.isSolvable(), is(false) );
        assertThat(solver.moves(), is(-1) );
    }

    @Test
    public void testSolved() throws Exception {
        Solver solver = new Solver(board("puzzle/2x2.txt"));
        List<Board> solution = new ArrayList<>();
        assertThat(solver.solution(), not(nullValue()));
        solver.solution().forEach((item) -> solution.add(item));
        assertThat(solution.size(), is(1));
        assertThat(solution.get(0), is(board("puzzle/2x2.txt")));
        assertThat(solver.isSolvable(), is(true) );
        assertThat(solver.moves(), is(0) );
    }

    public static String getFilePath(String resourceName) {
        return SolverTest.class.getResource(resourceName).getFile();
    }
    public static Board board(String resourceName){
        return new Board(blocksFromFile(SolverTest.getFilePath(resourceName)));
    }


    public static int[][] blocks(String resourceName){
        return blocksFromFile(SolverTest.getFilePath(resourceName));
    }

    public static String text(String resourceName){
        return new In(SolverTest.getFilePath(resourceName)).readAll();
    }

    public static int [][] blocksFromFile(String name){
        In in = new In(name);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        return blocks;
    }
}