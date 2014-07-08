public class Percolation {

    private boolean[] opensites;
    private boolean[] fullsites;
    private int size;
    private int size2;
    private WeightedQuickUnionUF uf;
    private boolean percolates;

    // create N-by-N grid, with all opensites blocked
    public Percolation(int N) {
        size2 = N * N;
        opensites = new boolean[size2];
        fullsites = new boolean[size2];
        for (int i = 0; i < size2; i++) {
            opensites[i] = false;
            fullsites[i] = false;
        }
        size = N;
        uf = new WeightedQuickUnionUF(size2);
    }


    // open site (row i, column j) if it is not already
    public void open(int row, int col) {
        int j = (row - 1) * size + col - 1;
        this.opensites[j] = true;
        //uf.union(i, j);

        //Top down
        if (isOpen(row - 1, col)) {
            int i = (row - 1 - 1) * size + col - 1;
            uf.union(i, j);
            if (fullsites[i])
                fullsites[j] = true;
        }

        if (isOpen(row + 1, col)) {
            int i = (row - 1 + 1) * size + col - 1;
            uf.union(i, j);
            if (fullsites[i])
                fullsites[j] = true;
        }
        //Left right
        if (isOpen(row, col - 1)) {
            int i = (row - 1) * size + col - 1 - 1;
            uf.union(i, j);
            if (fullsites[i])
                fullsites[j] = true;
        }
        if (isOpen(row, col + 1)) {
            int i = (row - 1) * size + col + 1 - 1;
            uf.union(i, j);
            if (fullsites[i])
                fullsites[j] = true;
        }

        for (int t = 0; t < size; t++)
            if (uf.connected(t, j))
                fullsites[j] = true;

        for (int t = 1; t <= size; t++)
            if (isFull(size, t))
                percolates = true;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        int j = (row - 1) * size + col - 1;
        if (j >= 0 && j < size2)
            return opensites[j];
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        int j = (row - 1) * size + col - 1;
        if (j >= 0 && j < size2) {
            return fullsites[j];
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolates;
    }
}
