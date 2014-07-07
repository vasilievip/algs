package coursera;

import java.util.Arrays;

public class UnionFind {

    /*
        Give the id[] array that results from the following sequence of 6 union
        operations on a set of 10 items using the quick-find algorithm.

            5-9 6-2 5-1 0-4 1-6 0-8

        Recall: our quick-find convention for the union operation p-q is to change id[p]
        (and perhaps some other entries) but not id[q].

        The correct answer is: 8 2 2 3 8 2 2 7 8 2

        Here is the id[] array after each union operation:

              0 1 2 3 4 5 6 7 8 9
        5-9:  0 1 2 3 4 9 6 7 8 9
        6-2:  0 1 2 3 4 9 2 7 8 9
        5-1:  0 1 2 3 4 1 2 7 8 1
        0-4:  4 1 2 3 4 1 2 7 8 1
        1-6:  4 2 2 3 4 2 2 7 8 2
        0-8:  8 2 2 3 8 2 2 7 8 2

     */

    int[] ids;

    public UnionFind(int N) {
        this.ids = new int[N];
        for(int i = 0; i<N; i++) {
            ids[i] = i;
        }

    }

    public void union(String list){
        list.replaceAll("  ", " ");
        String [] unions = list.split(" ");
        for(String un:unions){
             union(Integer.valueOf(un.split("-")[0]), Integer.valueOf(un.split("-")[1]));
        }
    }

    public void union(int p, int q){
       int pid = ids[p];
       int qid = ids[q];
       for (int i = 0; i<ids.length; i++)
           if(ids[i] == pid)
               ids[i]=qid;
    }

    @Override
    public String toString() {
        return "UnionFind{" +
                "ids=" + Arrays.toString(ids) +
                '}';
    }
}
