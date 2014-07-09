public class Percolation {

    private boolean[] opensites;
    private int size;
    private int size2;
    private WeightedQuickUnionUF uf;
    private boolean percolates;

    // create N-by-N grid, with all opensites blocked
    public Percolation(int N) {
        size = N;
        size2 = N * N;
        opensites = new boolean[size2];
        for (int i = 0; i < size2; i++) {
            opensites[i] = false;
        }
        uf = new WeightedQuickUnionUF(size2);
    }


    // open site (row i, column j) if it is not already
    public void open(int row, int col) {
        if ((row < 1 || row > size) && (col < 1 && col > size)) {
            return;
        }
        int j = (row - 1) * size + col - 1;
        this.opensites[j] = true;

        //Top down
        if (isOpen(row - 1, col)) {
            int i = (row - 1 - 1) * size + col - 1;
            uf.union(i, j);
        }

        if (isOpen(row + 1, col)) {
            int i = (row - 1 + 1) * size + col - 1;
            uf.union(i, j);
        }
        //Left right
        if (isOpen(row, col - 1)) {
            int i = (row - 1) * size + col - 1 - 1;
            uf.union(i, j);
        }
        if (isOpen(row, col + 1)) {
            int i = (row - 1) * size + col + 1 - 1;
            uf.union(i, j);
        }

    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        if ((row >= 1 && row <= size) && (col >= 1 && col <= size)) {
            int j = (row - 1) * size + col - 1;
                return opensites[j];
        }
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col))
            return false;
        int j = (row - 1) * size + col - 1;
        if (j >= 0 && j < size2) {
            //return fullsites[j];
            for (int t = 0; t < size; t++)
                if (uf.connected(t, j))
                    return true;
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int p = 1; p <=size; p++) {
            if (isFull(size, p)) {
                percolates = true;

            }
        }
        return percolates;
    }
}
