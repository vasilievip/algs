/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {


    // compare points by slope
    /*
    The SLOPE_ORDER comparator should compare points by the slopes
    they make with the invoking point (x0, y0). Formally, the point (x1, y1)
    is less than the point (x2, y2) if and only if the slope (y1 − y0) / (x1 − x0)
    is less than the slope (y2 − y0) / (x2 − x0).
    Treat horizontal, vertical,
     and degenerate line segments as in the slopeTo() method.
     */
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            if (o1 == null || o2 == null)
                throw new NullPointerException("Null");

            double slopeTo1 = Point.this.slopeTo(o1);
            double slopeTo2 = Point.this.slopeTo(o2);
            if (slopeTo1 < slopeTo2) {
                return -1;
            }
            if (slopeTo1 > slopeTo2) {
                return 1;
            }
            return 0;
        }
    };       // YOUR DEFINITION HERE


    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;

    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        //StdDraw.setPenRadius(0.05);
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (that == null)
            throw new NullPointerException("Null");
        /* given by the formula (y1 − y0) / (x1 − x0).
         Treat the slope of a horizontal line segment as positive zero;
         treat the slope of a vertical line segment as positive infinity;
         treat the slope of a degenerate line segment (between a point and itself)
           as negative infinity. */
        int dy = that.y - this.y;
        int dx = that.x - this.x;

        if (dy == 0 && dx != 0) {
            return 0;
        }

        if (dy != 0 && dx == 0) {
            return Double.POSITIVE_INFINITY;
        }

        if (dy == 0 && dx == 0) {
            return Double.NEGATIVE_INFINITY;
        }

        return (double) dy/(double) dx;
    }


    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null)
            throw new NullPointerException("Null");
        /*  Formally, the invoking point (x0, y0)
         is less than the argument point (x1, y1)
        if and only if either y0 < y1 or if y0 = y1 and x0 < x1. */
        if (this == that) return 0;
        if (this.x == that.x && this.y == that.y) {
            return 0;
        }

        if ((this.y < that.y) || (this.y == that.y && this.x < that.x)) {
            return -1;
        }

        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
