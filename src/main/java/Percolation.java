import java.util.Arrays;

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
    public void open(int i, int j) {
        int index = (i-1) * size + j - 1;
        this.sites[index] = true;
        uf.union(i, j);
        //Left right
        if (isOpen(i - 1, j))
            uf.union(index - 1, j-1);
        if (isOpen(i + 1, j))
            uf.union((i-1) * size, j-1);
        //Top down
        if (isOpen(i, j - 1))
            uf.union((i-1) * size, j - 2);
        if (isOpen(i, j + 1))
            uf.union((i-1) * size, j);
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i >= 1 && j >= 1 && (i * size + j-1) < size2)
            return sites[(i-1) * size + j-1];
        return false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        for (int t = 0; t < size; t++)
            if (uf.connected(t, (i-1) * size + j-1))
                return true;
        return false;
    }

    // does the system percolate?
    public boolean percolates() {

        return false;
    }

    @Override
    public String toString() {
        return "Percolation{"
                + "sites=" + Arrays.toString(sites)
                + ", size=" + size
                + ", uf=" + uf
                + '}';
    }
}
