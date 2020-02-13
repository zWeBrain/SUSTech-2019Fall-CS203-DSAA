package lab5;

import java.util.Arrays;

public class next {

  /*  private static int KmpSearch(char[] s, char[] p) {
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
            return i - j;
        else
            return -1;
    }*/

    private static void GetNext(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen)
            if (k == -1 || p[j] == p[k])
                next[++j] = ++k;
            else
                k = next[k];
    }

    private static void GetNextval(char[] p, int[] next) {
        int pLen = p.length;
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < pLen ) {

            if (k == -1 || p[j] == p[k]) {
                ++j;
                ++k;
                if (p[j] != p[k])
                    next[j] = k;
                else
                    next[j] = next[k];
            } else {
                k = next[k];
            }
        }
    }

    public static void main(String[] args) {
        String s = "failed";
        int[] next = new int[s.length() + 1];
        GetNext(s.toCharArray(), next);
        int len = s.length() - next[s.length()];
        if (len == s.length()) System.out.println(len);
        else System.out.println((s.length() % len == 0) ? 0 : len - s.length() * len);
        //Arrays.stream(next).forEach(System.out::print);
    }


}
