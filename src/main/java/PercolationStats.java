import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class PercolationStats {

    private double mean;
    private double stddev;
    private double confLo;
    private double confHi;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        double[] x = new double[T];
        int procs = Runtime.getRuntime().availableProcessors();
        ExecutorService es = Executors.newFixedThreadPool(procs);

        List<Future<Double>> results = new ArrayList();
        for (int i = 0; i < T; i++) {
            results.add((Future<Double>) es.submit((Runnable) new Task(i,N)));

//            Percolation p = new Percolation(N);
//            double openSites = 0;
//            while (!p.percolates()) {
//                int row = StdRandom.uniform(N)+1;
//                int col = StdRandom.uniform(N)+1;
//                if (!p.isOpen(row, col)) {
//                    p.open(row, col);
//                    openSites++;
//                }
////               PercolationVisualizer.draw(p, N);
////               StdDraw.show(100);
//            }
////            System.out.println(openSites);
////            PercolationVisualizer.draw(p, N);
////            StdDraw.show(5000);
//            x[i] = openSites /(double) (N * N) ;
        }

        for (int i = 0; i < T; i++) {
            try {
                x[i] = results.get(i).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Arrays.toString(x));
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

    private class Task implements Callable<Double>, Runnable {
        int i,N;
        double res;
        public Task(int i, int N) {
            this.i = i;
            this.N = N;

        }

        @Override
        public Double call() throws Exception {
            return res;
        }

        @Override
        public void run() {
            Percolation p = new Percolation(N);
            double openSites = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(N)+1;
                int col = StdRandom.uniform(N)+1;
                if (!p.isOpen(row, col)) {
                    p.open(row, col);
                    openSites++;
                }
//               PercolationVisualizer.draw(p, N);
//               StdDraw.show(100);
            }
//            System.out.println(openSites);
//            PercolationVisualizer.draw(p, N);
//            StdDraw.show(5000);

            res = openSites /(double) (N * N) ;
        }
    }
}
