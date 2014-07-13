
public class Subset {

    public static void main(String[] args) {
        String[] strs = StdIn.readAllStrings();
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        for (String s : strs) {
            q.enqueue(s);
        }
        for (int i = 0; i < Integer.valueOf(args[0]); i++) {
            System.out.println(q.sample());
        }
        //System.out.println(Arrays.toString(strs));
        //System.out.println(Arrays.toString(args));
    }
}