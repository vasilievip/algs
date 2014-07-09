public class Percolation {

    private boolean[] opensites;
    private int size;
    private int size2;
    private WeightedQuickUnionUF uf;
    private boolean percolates;

    // create N-by-N grid, with all opensites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("?" + N);
        size = N;
        size2 = N * N;
        opensites = new boolean[size2];
        for (int i = 0; i < size2; i++) {
            opensites[i] = false;
        }
        uf = new WeightedQuickUnionUF(size2);
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
        int j = (row - 1) * size + col - 1;
        this.opensites[j] = true;

        //Top down
        if (isValidRC(row - 1, col) && isOpen(row - 1, col)) {
            int i = (row - 1 - 1) * size + col - 1;
            if (!uf.connected(i, j))
                uf.union(i, j);
        }

        if (isValidRC(row + 1, col) && isOpen(row + 1, col)) {
            int i = (row - 1 + 1) * size + col - 1;
            if (!uf.connected(i, j))
                uf.union(i, j);
        }
        //Left right
        if (isValidRC(row, col - 1) && isOpen(row, col - 1)) {
            int i = (row - 1) * size + col - 1 - 1;
            if (!uf.connected(i, j))
                uf.union(i, j);
        }
        if (isValidRC(row, col + 1) && isOpen(row, col + 1)) {
            int i = (row - 1) * size + col + 1 - 1;
            if (!uf.connected(i, j))
                uf.union(i, j);
        }

    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        checkValidRowCol(row, col);
        int j = (row - 1) * size + col - 1;
        return opensites[j];

    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        checkValidRowCol(row, col);
        if (!isOpen(row, col))
            return false;
        int j = (row - 1) * size + col - 1;
        if (j >= 0 && j < size2) {
            for (int t = 0; t < size; t++)
                if (uf.connected(t, j))
                    return true;
        }
        return false;

    }

    // does the system percolate?
    public boolean percolates() {
        for (int p = 1; p <= size; p++) {
            if (isFull(size, p)) {
                percolates = true;
                break;
            }
        }
        return percolates;
    }
}
