public class Percolation {

    private boolean[] opensites;
    private int size;
    private int size2;
    private WeightedQuickUnionUF perc;
    private WeightedQuickUnionUF full;
    private int topIndex;
    private int bottomIndex;

    // create N-by-N grid, with all opensites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("?" + N);
        size = N;
        size2 = N * N;
        opensites = new boolean[size2 + 2];
        for (int i = 0; i < size2; i++) {
            opensites[i] = false;
        }
        perc = new WeightedQuickUnionUF(size2 + 2);
        full = new WeightedQuickUnionUF(size2 + 2);
        topIndex =  size2;
        bottomIndex = size2 + 1;
    }

    private boolean isValidRC(int row, int col) {
        return (row >= 1 && row <= size) && (col >= 1 && col <= size);
    }

    private void checkValidRowCol(int row, int col) {
        if (!isValidRC(row, col))
            throw new IndexOutOfBoundsException("Not valid:" + row + "," + col);
    }

    // open site (row i, column j) if it is not already
    public void open(int row, int col) {
        checkValidRowCol(row, col);
        int j = getIndex(row, col);
        this.opensites[j] = true;

        if (row == 1) {
            perc.union(topIndex, j);
            full.union(topIndex, j);
        }
        if (row == size) {
            perc.union(bottomIndex, j);
        }
        //Top down
        if (isValidRC(row - 1, col) && isOpen(row - 1, col)) {
            int i = getIndex(row - 1, col);
            if (!perc.connected(i, j)) {
                perc.union(i, j);
                full.union(i, j);
            }
        }
        if (isValidRC(row + 1, col) && isOpen(row + 1, col)) {
            int i = getIndex(row + 1, col);
            if (!perc.connected(i, j)) {
                perc.union(i, j);
                full.union(i, j);
            }
        }
        //Left right
        if (isValidRC(row, col - 1) && isOpen(row, col - 1)) {
            int i = getIndex(row, col - 1);
            if (!perc.connected(i, j)) {
                perc.union(i, j);
                full.union(i, j);
            }
        }
        if (isValidRC(row, col + 1) && isOpen(row, col + 1)) {
            int i = getIndex(row, col + 1);
            if (!perc.connected(i, j)) {
                perc.union(i, j);
                full.union(i, j);
            }
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * size + col-1;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        checkValidRowCol(row, col);
        int j = getIndex(row, col);
        return opensites[j];

    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        checkValidRowCol(row, col);
        if (!isOpen(row, col))
            return false;
        int j = getIndex(row, col);
        return full.connected(topIndex, j);
    }

    // does the system percolate?
    public boolean percolates() {
        return perc.connected(topIndex, bottomIndex);
    }
}
