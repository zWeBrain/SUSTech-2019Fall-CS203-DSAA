package lab5;

public class KMP {

    private static int KmpSearch(char[] s, char[] p) {
        int[] next = new int[p.length + 1];
        GetNext(p, next);
        int i = 0;
        int j = 0;
        int sLen = s.length;
        int pLen = p.length;
        while (i < sLen && j < pLen) {
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pLen)
            //return true;
            return i - j;
        else
            return -1;
    }


    private static void GetNext(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen - 1)
            if (k == -1 || p[j] == p[k])
                next[++j] = ++k;
            else
                k = next[k];
    }

    private static void GetNext2(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen - 1) {
            if (k == -1 || p[j] == p[k]) {
                ++j;
                ++k;
                if (p[j] != p[k]) next[j] = k;
                else next[j] = next[k];
            } else k = next[k];
        }
    }

    public static void main(String[] args) {
        String p = "1241231";
        String s = "1242315423151";
        int[] ints = new int[p.length()];
        GetNext2(p.toCharArray(), ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
        GetNext(p.toCharArray(), ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println(KmpSearch(s.toCharArray(), p.toCharArray()));
    }

}
