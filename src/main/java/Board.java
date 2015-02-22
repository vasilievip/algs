import java.util.Arrays;
import java.util.Collections;

/**
 * Created by vasilievip on 22.02.15.
 */
public class Board {
    private int[][] blocks;
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks)   {
        this.blocks = blocks;
    }
    // board dimension N
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        int result = 0;
        for (int i = 1; i <= blocks.length; i++) {
            for (int j = 1; j <= blocks.length; j++) {
                if ((i == j) && (i == blocks.length)) break;
                if (blocks[i - 1][j - 1] != j + (i - 1) * blocks.length) {
                    result++;
                }
            }
        }
        return result;
    }

    // sum of Manhattan distances between blocks and goal
/*
 8  1  3        1  2  3     1  2  3  4  5  6  7  8    1  2  3  4  5  6  7  8
 4     2        4  5  6     ----------------------    ----------------------
 7  6  5        7  8        1  1  0  0  1  1  0  1    1  2  0  0  2  2  0  3

 initial          goal         Hamming = 5 + 0          Manhattan = 10 + 0
*/
    public int manhattan() {
        int result = 0;
        for (int i = 1; i <= blocks.length; i++) {
            for (int j = 1; j <= blocks.length; j++) {
                if ((i == j) && (i == blocks.length)) break;
                if (blocks[i - 1][j - 1] != j + (i - 1) * blocks.length &&
                        blocks[i - 1][j - 1] != 0) {
                    int target [] = getTargetIndexes(blocks[i - 1][j - 1]);
                    int toBeJ =  target[0];
                    int toBeI =  target[1];
                    result+= Math.abs(toBeI - i + toBeJ - j);
                }
            }
        }
        return result;
    }

    protected int [] getTargetIndexes(int number){
        return new int [] {number - number % blocks.length +1, number / blocks.length + 1};
    }

    // is this board the goal board?
    public boolean isGoal() {
        int result = 0;
        for (int i = 1; i <= blocks.length; i++) {
            for (int j = 1; j <= blocks.length; j++) {
                if ((i == j) && (i == blocks.length)) break;
                if (blocks[i - 1][j - 1] != j + (i - 1) * blocks.length) {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin(){
        return this;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board board = (Board) y;
        if (blocks.length != blocks.length) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < blocks.length; i++) {
            result = result && Arrays.equals(blocks[i], board.blocks[i]);
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "" + blocks.length;
        for (int i = 0; i < blocks.length; i++) {
            result += System.getProperty("line.separator");
            for (int j = 0; j < blocks.length; j++)
                result += String.format(" %d ", blocks[i][j]);
        }
        return result;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {return Collections.EMPTY_LIST;}

    // unit tests (not graded)
    public static void main(String[] args) {}
}