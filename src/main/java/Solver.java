import java.util.LinkedList;
import java.util.List;

/**
 * Created by vasilievip on 22.02.15.
 */
public class Solver {
    private final Board initial;
    private final MinPQ<SearchNode> gameTree1;
    private final MinPQ<SearchNode> gameTree2;
    private List<Board> result = new LinkedList<>();
    private int moves = -1;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        this.gameTree1 = new MinPQ<SearchNode>();
        this.gameTree2 = new MinPQ<SearchNode>();
        this.gameTree1.insert(new SearchNode(initial, null, 0));
        this.gameTree2.insert(new SearchNode(initial.twin(), null, 0));
        calcResult();
    }
    // is the initial board solvable?
    public boolean isSolvable() {
        return moves >= 0;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }
    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (moves >= 0) {
            return result;
        }
        return null;
    }

    private void calcResult() {
        boolean stop = false;
        do {
            SearchNode start1 = gameTree1.delMin();
            SearchNode start2 = gameTree2.delMin();
            insertNoDuplicates(start1.getThisBoard().neighbors(), gameTree1, start1);
            insertNoDuplicates(start2.getThisBoard().neighbors(), gameTree2, start2);
            result.add(start1.getThisBoard());
            if (start1.getThisBoard().isGoal()) {
                moves = start1.getMoves();
                return;
            }
            if (start2.getThisBoard().isGoal()) {
                stop = true;
                moves = -1;
            }
        } while (!stop);
        result.clear();
    }

    private void insertNoDuplicates(Iterable<Board> iterable,
                                   MinPQ<SearchNode> gameTree,
                                   SearchNode current) {
        for (Board board : iterable) {
            if (current.getPrevious() == null
                    || !board.equals(current.getPrevious())) {
                gameTree.insert(
                        new SearchNode(
                                board,
                                current.getThisBoard(),
                                current.getMoves() + 1)
                );
            }
        }
    }
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
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

    private static int [][] blocksFromFile(String name) {
        In in = new In(name);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        return blocks;
    }

    private static class SearchNode implements Comparable<SearchNode> {
        private final Board thisBoard;
        private final Board previous;
        private final int moves;

        private SearchNode(Board thisBoard, Board previous, int moves) {
            this.thisBoard = thisBoard;
            this.previous = previous;
            this.moves = moves;
        }

        public Board getThisBoard() {
            return thisBoard;
        }

        public Board getPrevious() {
            return previous;
        }

        public int getMoves() {
            return moves;
        }

        public int getPriority() {
            return thisBoard.manhattan() + moves;
        }

        @Override
        public int compareTo(SearchNode other) {
            return this.getPriority() - other.getPriority();
        }

        @Override
        public String toString() {
            return "SearchNode{"
                    +
                    "thisBoard="
                    + thisBoard
                    +
                    ", previous="
                    + previous
                    +
                    ", moves="
                    + moves
                    +
                    '}';
        }
    }
}
