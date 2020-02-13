package lab5;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

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
        return j;
    }

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

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        char[] c = new char[26];
        for (int i = 0; i < 26; i++) {
            c[i] = in.next().trim().charAt(0);
        }
        char[] txt = in.next().toCharArray();
        char[] str = new char[txt.length];
        for (int i = 0; i < str.length; i++) {
            str[i] = c[txt[i] - 'a'];
        }
        char[] match = String.valueOf(str).substring((txt.length + 1) / 2, txt.length).toCharArray();
        out.println(txt.length -KmpSearch(match, txt));
        out.close();

    }
}
