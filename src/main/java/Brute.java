import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27/09/14.
 */
public class Brute {

    public static void main(String[] args) {
        In in = new In(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int size = in.readInt();
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < size; i++) {
            points.add(new Point(in.readInt(), in.readInt()));
        }

        for (Point point1 : points) {
            for (Point point2 : points) {
                if (point1 == point2) {
                    continue;
                }
                double slope12 = point1.slopeTo(point2);

                for (Point point3 : points) {
                    if (point2 == point3
                    || point1 == point3) {
                        continue;
                    }
                    double slope23 = point1.slopeTo(point3);
                    for (Point point4 : points) {
                        if (point3 == point4
                              || point1 == point4
                              || point2 == point4
                                || slope12 != slope23) {
                            continue;
                        }

                        double slope34 = point1.slopeTo(point4);
                        if (slope23 == slope34) {
                            if (point1.compareTo(point2) < 0
                              && point2.compareTo(point3) < 0
                              && point3.compareTo(point4) < 0) {
                                System.out.print(point1);
                                System.out.print(" -> ");
                                System.out.print(point2);
                                System.out.print(" -> ");
                                System.out.print(point3);
                                System.out.print(" -> ");
                                System.out.print(point4);
                                System.out.println();
                                point1.drawTo(point4);
                            }
                        }
                    }

                }
            }

        }

        for (int i = 0; i < size; i++) {
            points.get(i).draw();
        }
    }
}
