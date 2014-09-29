//import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27/09/14.
 */
public class Fast {

    public static void main(String[] args) {
//        In in = new In(new File(args[0]));
//        In in = new In(args[0]);
//        int size = in.readInt();
//        List<Point> points = new ArrayList<Point>();
//        for (int i = 0; i < size; i++) {
//           points.add(new Point(in.readInt(), in.readInt()));
//        }

        System.out.println(new Point(491, 335).SLOPE_ORDER.compare(new Point(453, 369), new Point(341, 253)));

    }
}
