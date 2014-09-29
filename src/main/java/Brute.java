//import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27/09/14.
 */
public class Brute {

    public static void main(String[] args) {
        In in = new In(args[0]);
//String pathname = "/home/user/javaprojects/algs/src/test/resources/input8.txt";
//In in = new In(new File(pathname));
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int size = in.readInt();
        List<Point> points = new ArrayList<Point>();
        for (int i = 0; i < size; i++) {
            points.add(new Point(in.readInt(), in.readInt()));
            //points.get(i).draw();
        }

        for (Point point1 : points) {
            for (Point point2 : points) {
                if (point1.compareTo(point2) == 0) {
                    continue;
                }
                for (Point point3 : points) {
                    if (point2.compareTo(point3) == 0
                    && point1.compareTo(point3) == 0) {
                        continue;
                    }
                    for (Point point4 : points) {
                        if (point3.compareTo(point4) == 0
                                || point1.compareTo(point4) == 0
                                || point2.compareTo(point4) == 0) {
                            continue;
                        }
                        double slope12 = point1.slopeTo(point2);
                        double slope23 = point1.slopeTo(point3);
                        double slope34 = point1.slopeTo(point4);
//                        System.out.println(slope12);
//                        System.out.println(slope23);
//                        System.out.println(slope34);
//                        System.out.println("----------");
                        if (slope12 == slope23
                              &&  slope23 == slope34) {
                            if (point1.compareTo(point2) == -1
                              && point2.compareTo(point3) == -1
                              && point3.compareTo(point4) == -1) {
                                System.out.print(point1);
                                System.out.print(" -> ");
                                System.out.print(point2);
                                System.out.print(" -> ");
                                System.out.print(point3);
                                System.out.print(" -> ");
                                System.out.print(point4);
                                System.out.println();
                                //point1.drawTo(point2);
                                //point1.drawTo(point3);
                                point1.draw();
                                point2.draw();
                                point3.draw();
                                point4.draw();
                                point1.drawTo(point4);
                            }
                        }
                    }

                }
            }

        }
    }
}
