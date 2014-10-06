import java.util.Arrays;

public class Fast {

    public static void main(String[] args) {
        In in = new In(args[0]);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        int size = in.readInt();
        Point[] points = new Point[size];
        Point[] points2;
        for (int i = 0; i < size; i++) {
            points[i] = new Point(in.readInt(), in.readInt());
        }
        Arrays.sort(points);
        points2 = Arrays.copyOf(points, size);
        for (int currentPoint = 0; currentPoint < size; currentPoint++) {
            Point current = points2[currentPoint];
            Arrays.sort(points, current.SLOPE_ORDER);
            int segmentStart = 1;
            int segmentEnd = 2;
            do {
                for (int i = segmentEnd; i < size; i++) {
                    if (current.SLOPE_ORDER.
                            compare(points[segmentStart], points[i]) != 0) {
                        break;
                    }
                    segmentEnd = i;
                }
                if (segmentEnd - segmentStart >= 2) {
                    boolean isCurrentIsMinPoint = true;
                    for (int j = segmentStart; j <= segmentEnd; j++) {
                        if (current.compareTo(points[j]) >= 0) {
                            isCurrentIsMinPoint = false;
                        }
                    }
                    if (isCurrentIsMinPoint) {
                        Arrays.sort(points, segmentStart, segmentEnd+1);
                        System.out.print(current);
                        for (int j = segmentStart; j <= segmentEnd; j++) {
                            System.out.print(" -> ");
                            System.out.print(points[j]);
                        }
                        System.out.println();
                        current.drawTo(points[segmentEnd]);
                    }
                }
                segmentStart = segmentEnd;
                segmentEnd += 1;
           } while (segmentEnd < size);
        }
        for (Point p : points) {
            p.draw();
        }
    }
}
