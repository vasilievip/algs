import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vasilievip on 22.02.15.
 */
public class Board {
    private int[][] blocks;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = new int [blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                this.blocks[i][j] = blocks[i][j];
            }
        }
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
                if (getAt(i, j) == 0) continue;
                if (getAt(i, j) != j + (i - 1) * blocks.length) {
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
                if (getAt(i, j) != 0) {
                    int [] target = getTargetIndexes(getAt(i, j));
                    int toBeI = target[0];
                    int toBeJ = target[1];
                    result += (Math.abs(toBeI - i) + Math.abs(toBeJ - j));
                }
            }
        }
        return result;
    }

    private int getAt(int i, int j) {
        return blocks[i - 1][j - 1];
    }

    private int [] getEmptyBlock() {
        for (int i = 1; i <= blocks.length; i++) {
            for (int j = 1; j <= blocks.length; j++) {
                if (getAt(i, j) == 0) {
                    return new int [] { i , j };
                }
            }
        }
        throw new IllegalStateException("No empty block found!");
    }

    private int[] getTargetIndexes(int number) {
        return new int[]{(number - 1) / blocks.length + 1,
                (number - 1) % blocks.length + 1};
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 1; i <= blocks.length; i++) {
            for (int j = 1; j <= blocks.length; j++) {
                if ((i == j) && (i == blocks.length)) break;
                if (getAt(i, j) != j + (i - 1) * blocks.length) {
                    return false;
                }
            }
        }
        return true;
    }

    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int j1 = 1;
        int j2 = 2;
        int[][] newBlocks = getCopy();
        for (int row = 1; row <= blocks.length; row++) {
            if (getAt(row, j1) != 0 && getAt(row, j2) != 0) {
                exch(row, j1, row, j2, newBlocks);
                break;
            }
        }
        return new Board(newBlocks);
    }

    private void exch(int i, int j, int newi, int newj, int[][] array) {
        int tmp = array[i - 1][j - 1];
        array[i - 1][j - 1] = array[newi - 1][newj - 1];
        array[newi - 1][newj - 1] = tmp;
    }

    private int[][] getCopy() {
        int[][] newBlocks = new int[blocks.length][blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks.length; j++) {
                newBlocks[i][j] = blocks[i][j];
            }
        }
        return newBlocks;
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
        for (int i = 1; i <= blocks.length; i++) {
            result += System.getProperty("line.separator");
            for (int j = 1; j <= blocks.length; j++)
                result += String.format(" %d ", getAt(i, j));
        }
        return result;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int [] emptyIJ = getEmptyBlock();
        int emptyI = emptyIJ[0];
        int emptyJ = emptyIJ[1];

        Board top = moveTop(emptyI, emptyJ);
        Board left = moveLeft(emptyI, emptyJ);
        Board right = moveRight(emptyI, emptyJ);
        Board down = moveDown(emptyI, emptyJ);
        List<Board> result = new ArrayList<Board>();
        if (top != null) {
            result.add(top);
        }
        if (left != null) {
            result.add(left);
        }
        if (right != null) {
            result.add(right);
        }
        if (down != null) {
            result.add(down);
        }
        return result;
    }

    private Board moveDown(int emptyI, int emptyJ) {
        if (emptyI == blocks.length) {
            return null;
        }
        int [][] newBlocks = getCopy();
        exch(emptyI , emptyJ, emptyI + 1, emptyJ, newBlocks);
        return new Board(newBlocks);
    }

    private Board moveRight(int emptyI, int emptyJ) {
        if (emptyJ == blocks.length) {
            return null;
        }
        int [][] newBlocks = getCopy();
        exch(emptyI, emptyJ, emptyI, emptyJ + 1, newBlocks);
        return new Board(newBlocks);
    }

    private Board moveLeft(int emptyI, int emptyJ) {
        if (emptyJ == 1) {
            return null;
        }
        int [][] newBlocks = getCopy();
        exch(emptyI, emptyJ, emptyI, emptyJ - 1, newBlocks);
        return new Board(newBlocks);
    }

    private Board moveTop(int emptyI, int emptyJ) {
        if (emptyI == 1) {
            return null;
        }
        int [][] newBlocks = getCopy();
        exch(emptyI, emptyJ, emptyI - 1, emptyJ, newBlocks);
        return new Board(newBlocks);
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}
