public class Percolation {

    private boolean[] sites;
    private int size;
    private int size2;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        size2 = N * N;
        sites = new boolean[size2];
        for (int i = 0; i < size2; i++)
            sites[i] = false;
        size = N;
        uf = new WeightedQuickUnionUF(size2);
    }


    // open site (row i, column j) if it is not already
    public void open(int row, int col) {
        int j = (row-1) * size + col - 1;
        this.sites[j] = true;
        //uf.union(i, j);
        //Left right
        if (isOpen(row - 1, col)) {
            int i = (row-1 -1)*size + col - 1;
            uf.union(i, j);
        }

        if (isOpen(row + 1, col)) {
            int i = (row-1+1)*size + col - 1;
            uf.union(i, j);
        }
        //Top down
        if (isOpen(row, col - 1)) {
            int i = (row-1)*size + col -1 - 1;
            uf.union(i, j);
        }

        if (isOpen(row, col + 1)) {
            int i = (row-1)*size + col +1 - 1;
            uf.union(i, j);
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int row, int col) {
        int i = row - 1;
        int j = i * size + col - 1;
        if (j >= 0 && j < size2)
            return sites[j];
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col))
            return false;
        int i = row - 1;
        int j = i * size + col - 1;
        for (int t = 0; t < size; t++)
            if (uf.connected(t, j))
                return true;
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int t = 1; t <= size; t++)
          if (isFull(size, t))
                return true;
        return false;
    }
}
