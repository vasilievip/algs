public class PercolationStats {
    private double mean;
    private double stddev;
    private double confLo;
    private double confHi;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException("");
        double[] x = new double[T];
        for (int i = 0; i < T; i++) {
            Stopwatch w = new Stopwatch();
            Percolation p = new Percolation(N);
            double openSites = 0;
            while (!p.percolates()) {
                for (int t = 0; t < 10000; t++) {
                    int row = StdRandom.uniform(N) + 1;
                    int col = StdRandom.uniform(N) + 1;
                    if (!p.isOpen(row, col)) {
                        p.open(row, col);
                        openSites++;
                        break;
                    }
                }
            }
            x[i] = openSites / (double) (N * N);
            System.out.println(Thread.currentThread().getId()+":"+w.elapsedTime());
        }
        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
        confLo = mean - 1.96 * stddev / Math.sqrt(T);
        confHi = mean + 1.96 * stddev / Math.sqrt(T);
    }


    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return confLo;
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return confHi;
    }

    // test client, described below
    public static void main(String[] args) {
        // PercolationVisualizer
        int N = Integer.valueOf(args[0]);
        int T = Integer.valueOf(args[1]);
        PercolationStats st = new PercolationStats(N, T);

        // write to stdout
        StdOut.print("mean                    = ");
        StdOut.printf("%.17f\n", st.mean());

        StdOut.print("stddev                  = ");
        StdOut.printf("%.17f\n", st.stddev());

        StdOut.print("95% confidence interval = ");
        StdOut.printf("%.17f %.17f\n", st.confidenceLo(), st.confidenceHi());

    }

}
