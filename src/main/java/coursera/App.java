package coursera;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) {
       UnionFind uf = new UnionFind(10);
       uf.union("1-9 6-0 4-5 2-9 9-7 3-6");

        System.out.println(uf.toString().replaceAll(",", ""));

        UnionFindWeighted ufw = new UnionFindWeighted(10);
        ufw.union("2-6 4-9 9-3 0-1 9-8 2-1 4-5 0-4 7-0");
        System.out.println(ufw.toString().replaceAll(",", ""));


    }

}
