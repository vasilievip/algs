import org.junit.Test;


import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void testConstructor() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/puzzle04.txt");
        Board board = new Board(blocks);
        assertThat(board.dimension(), is(3));
    }

    @Test
    public void testEquals() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/puzzle04.txt");
        Board board1 = new Board(blocks);
        Board board2 = new Board(blocks);
        assertThat(board1, is(board2));
    }

    @Test
    public void testToString() throws Exception {
        String text = SolverTest.text("puzzle/puzzle04.txt");
        int [][] blocks = SolverTest.blocks("puzzle/puzzle04.txt");
        Board board = new Board(blocks);
        assertThat(text, is(board.toString()));
    }

    @Test
    public void testIsGoal() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/puzzle04.txt");
        Board board = new Board(blocks);
        assertThat(board.isGoal(), is(false));

        int [][] blocks2 = SolverTest.blocks("puzzle/goal.txt");
        Board board2 = new Board(blocks2);
        assertThat(board2.isGoal(), is(true));
    }

    @Test
    public void testHamming() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/distance.txt");
        Board board = new Board(blocks);
        assertThat(board.hamming(), is(5));
    }

    @Test
    public void testTwin() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/twintest.txt");
        Board board = new Board(blocks);
        assertFalse(board.twin().equals(board));
    }

    /*
        @Test
        public void testGetTargetIndexes() throws Exception {
            int [][] blocks = SolverTest.blocks("puzzle/distance.txt");
            Board board = new Board(blocks);

            assertThat(board.getTargetIndexes(3)[0], is(1));
            assertThat(board.getTargetIndexes(3)[1], is(3));

            assertThat(board.getTargetIndexes(4)[0], is(2));
            assertThat(board.getTargetIndexes(4)[1], is(1));

            for (int i = 1; i <= blocks.length; i++) {
                for (int j = 1; j <= blocks.length; j++) {
                    int number = j + (i - 1) * blocks.length;
                    assertThat(board.getTargetIndexes(number)[0], is(i));
                    assertThat(board.getTargetIndexes(number)[1], is(j));
                }
            }
        }
    */
    @Test
    public void testManhattan() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/distance.txt");
        Board board = new Board(blocks);
        assertThat(board.manhattan(), is(10));
    }

    @Test
    public void testNeighboring() throws Exception {
        int [][] blocks = SolverTest.blocks("puzzle/neighboring.txt");
        Board board = new Board(blocks);
        int [][] blocks2 = SolverTest.blocks("puzzle/neighboring.txt");
        Board board2 = new Board(blocks2);
        assertTrue(board.equals(board2));
        List<Board> boardSet = new LinkedList<Board>();
        board.neighbors().forEach((join) -> boardSet.add(join));

        assertTrue(boardSet.contains(new Board(SolverTest.blocks("puzzle/neighboring_down.txt"))));
        assertTrue(boardSet.contains(new Board(SolverTest.blocks("puzzle/neighboring_top.txt"))));
        assertTrue(boardSet.contains(new Board(SolverTest.blocks("puzzle/neighboring_left.txt"))));
        assertTrue(boardSet.contains(new Board(SolverTest.blocks("puzzle/neighboring_right.txt"))));

    }

    @Test
    public void testToM3() throws Exception {
        int [] array = new int [9];
        for(int i=0;i<array.length; i++)
            array[i] = i;
        System.out.println(new Board(toM3(array, 3)));
    }

    public static int[][] toM3(int[] array, int a) {
        int[][] matrix = new int[(array.length + a - 1) / a][];
        int rowStart = 0;
        for (int i = 0; i < array.length; i++) {
            int row = i/a;
            if (matrix[ row ] == null) {
                matrix[ row ] = new int[ Math.min( a, array.length-rowStart) ];
                rowStart += a;
            }
            matrix[ row ][i % a] = array[i];
        }
        return matrix;
    }
}