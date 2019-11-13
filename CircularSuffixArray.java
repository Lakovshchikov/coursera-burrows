import edu.princeton.cs.algs4.Out;

public class CircularSuffixArray {
    private int length;
    private int[] index;

    // circular suffix array of s
    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        length = s.length();
        int R = 256;
        index = new int[length];

        StringBuffer SB = new StringBuffer(s);
        SB.append(s);
        String st = SB.toString();
        SB = null;

        //Лишнее, можно через CharAt()
        char[] initCharArr = st.toCharArray();

        int[] arrFirstInd = new int[length];

        int[] arrRange = new int[length];
        int[] aux = new int[length];

        for(int i = 0; i < length; i++) {
            arrFirstInd[i] = i;
            arrRange[i] = i;
        }

        char[] chars = new char[length];

        for (int i = length; i > 0; i--) {
            int[] count = new int[R + 1];

            for(int j = 0; j < length; j++)
                chars[j] = initCharArr[arrFirstInd[arrRange[j]] + i - 1];


            for(int j = 0; j < length; j++)
                count[chars[j] + 1]++;

            for(int r = 0; r < R; r++)
                count[r+1] += count[r];

            for(int j = 0; j < length; j++)
                aux[count[chars[j]]++] = arrRange[j];

            for(int j = 0; j < length; j++)
                arrRange[j] = aux[j];
        }

        index = aux;
    }

    // length of s
    public int length() {
        return length;
    }

    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i > length-1)
            throw new IllegalArgumentException();
        return index[i];
    }

    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray CSA = new CircularSuffixArray(args[0]);
        Out out = new Out();
        int L = CSA.length();
        out.print(L);
        out.print("\n");
        for (int i = 0; i < L; i++) {
            out.print(CSA.index(i));
            out.print(" ");
        }
    }

}

