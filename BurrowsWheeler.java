import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;

public class BurrowsWheeler {
    // apply Burrows-Wheeler transform,
    // reading from standard input and writing to standard output

    public static void transform() {
        String s = BinaryStdIn.readString();
        StringBuffer SB = new StringBuffer(s);
        CircularSuffixArray CSA = new CircularSuffixArray(s);
        char[] chars = new char[s.length()];

        SB.append(s);
        s = SB.toString();
        SB = null;

        int first = -1;

        for (int i = 0; i < CSA.length(); i++){
            int ind = CSA.index(i);
            if (ind == 0) {
                first = i;
                chars[i] = s.charAt(s.length()-1);
            } else {
                chars[i] = s.charAt(ind - 1);
            }
        }

        BinaryStdOut.write(first);

        for (int i=0; i<chars.length; i++) {
            BinaryStdOut.write(chars[i]);
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();

        return;
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        ST<Character, Queue<Integer>> NT = new ST<>();
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] chars = s.toCharArray();
        CircularSuffixArray CSA = new CircularSuffixArray(s);
        int[] next = new int[s.length()];
        char[] firstColumn = new char[s.length()];
        int[] count = new int[257];

        for(int j = 0; j < CSA.length(); j++)
            count[chars[j] + 1]++;

        for(int r = 0; r < 256; r++)
            count[r+1] += count[r];

        for(int j = 0; j < CSA.length(); j++) {
            firstColumn[count[chars[j]]] = chars[j];
            if (NT.get(chars[j]) != null) {
                NT.get(chars[j]).enqueue(j);
            } else {
                NT.put(chars[j], new Queue<>());
                NT.get(chars[j]).enqueue(j);
            }
            count[chars[j]]++;
        }

        for (int j = 0; j < CSA.length(); j++){
            next[j] = NT.get(firstColumn[j]).dequeue();
        }

        for (int i = 0; i < CSA.length(); i++){
            chars[i] = firstColumn[first];
            first=next[first];
        }

        for (int i=0; i<chars.length; i++) {
            BinaryStdOut.write(chars[i]);
        }

        BinaryStdOut.flush();
        BinaryStdOut.close();
        return;
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        String key = args[0];
        if ( key.equals("-")) {
            BurrowsWheeler.transform();
        } else if (key.equals("+")) {
            BurrowsWheeler.inverseTransform();
        }
    }
}
