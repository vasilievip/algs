import java.util.Collections;

/**
 * Created by vasilievip on 22.02.15.
 */
public class Solver {
    Board initial;
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
    }
    // is the initial board solvable?
    public boolean isSolvable() {
        return false;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves()   {
        return -1;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return Collections.EMPTY_LIST;
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args){
        // create initial board from file
        Board initial = new Board(blocksFromFile(args[0]));

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
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
