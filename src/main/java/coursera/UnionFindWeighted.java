package coursera;

import java.util.Arrays;

public class UnionFindWeighted {

    /*
        Give the id[] array that results from the following sequence of 9 union
    operations on a set of 10 items using the weighted quick-union algorithm
    from lecture.

        1-8 3-4 2-0 8-2 4-5 7-3 5-6 0-4 8-9

    Recall: when joining two trees of equal size, our weighted quick
    union convention
    is to
    make the root of the second tree point to the root of the first tree.
    Also, our
    weighted
    quick union algorithm uses union by size (number of nodes),
    not union by height.

    The correct answer is: 2 3 1 3 3 3 3 3 1 3

    Here is the id[] array after each union operation:

          0 1 2 3 4 5 6 7 8 9
    1-8:  0 1 2 3 4 5 6 7 1 9
    3-4:  0 1 2 3 3 5 6 7 1 9
    2-0:  2 1 2 3 3 5 6 7 1 9
    8-2:  2 1 1 3 3 5 6 7 1 9
    4-5:  2 1 1 3 3 3 6 7 1 9
    7-3:  2 1 1 3 3 3 6 3 1 9
    5-6:  2 1 1 3 3 3 3 3 1 9
    0-4:  2 3 1 3 3 3 3 3 1 9
    8-9:  2 3 1 3 3 3 3 3 1 3
     */

    private int[] ids;
    private int[] sizes;

    public UnionFindWeighted(int N) {
        this.ids = new int[N];
        this.sizes = new int[N];
        for (int i = 0; i < N; i++) {
            ids[i] = i;
            sizes[i] = 1;
        }

    }

    public void union(String list) {
        list.replaceAll("  ", " ");
        String[] unions = list.split(" ");
        for (String un : unions) {
            union(Integer.valueOf(un.split("-")[0]),
                    Integer.valueOf(un.split("-")[1]));
        }
    }


    public void union(int p, int q) {
        int i = root(ids[p]);
        int j = root(ids[q]);
        if (i == j)
            return;

        if (sizes[i] < sizes[j]) {
            ids[i] = j;
            sizes[j] = sizes[j] + sizes[i];
        } else {
            sizes[i] = sizes[i] + sizes[j];
            ids[j] = i;
        }
    }

    private int root(int id) {
        int res = id;
        while (id != ids[id]) {
            res = ids[id];
        }
        return res;
    }

    @Override
    public String toString() {
        return "UnionFind{"
                +
                "ids="
                + Arrays.toString(ids)
                +
                '}';
    }
}
