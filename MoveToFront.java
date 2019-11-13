import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        char[] chars = new char[256];
        for (int i = 0; i < 256; i++) {
            chars[i] = (char) i;
        }


        while (!BinaryStdIn.isEmpty()){
            char c = BinaryStdIn.readChar();
            for (int i = 0; i < 256; i++) {
                if (chars[i] == c) {
                    BinaryStdOut.write((char)i);
                    for (int j = i; j > 0; j--)
                        chars[j] = chars[j-1];
                    chars[0] = c;
                    break;
                }
            }
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        char[] chars = new char[256];
        for (int i = 0; i < 256; i++) chars[i] = (char) i;
        while (!BinaryStdIn.isEmpty()){
            char c = BinaryStdIn.readChar();
            BinaryStdOut.write(chars[c]);
            char temp = chars[c];
            for (int i = c; i > 0; i--)
                chars[i] = chars[i-1];
            chars[0] = temp;
        }
        BinaryStdOut.flush();
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        String key = args[0];
        if ( key.equals("-")) {
            MoveToFront.encode();
        } else if (key.equals("+")) {
            MoveToFront.decode();
        }
    }

}