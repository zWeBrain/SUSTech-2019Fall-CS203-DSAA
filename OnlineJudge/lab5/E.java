package lab5;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class E {
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

    private static boolean Rabin_Karp_search(String T, String P, int m, int d, int q) {
        HashMap<Long, Integer> map = new HashMap<>();
        int n = T.length();
        int i, k, j;
        long p = 0;
        long t = 0;
        long h = 1;

        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        for (i = 0; i < m; i++) {
            p = (d * p + P.charAt(i)) % q;
            t = (d * t + T.charAt(i)) % q;
        }

        map.put(t, 0);

        for (i = 0; i <= n - m; i++) {
            if (p == t) {
                for (j = 0; j < m; j++)
                    if (T.charAt(i + j) != P.charAt(j))
                        break;
                if (j == m)
                    return true;
            }
            if (i < n - m) {
                t = (d * (t - T.charAt(i) * h) + T.charAt(i + m)) % q;
                if (t < 0)
                    t += q;
                map.put(t, i + 1);
            }
        }
        //    System.out.println(map.toString());
        for (k = 0; k < P.length() - m; k++) {
            p = (d * (p - P.charAt(k) * h) + P.charAt(k + m)) % q;
            if (p < 0) {
                p += q;
            }

            //  System.out.println(p);
            if (map.containsKey(p)) {
                //  System.out.println("now m: " + m);
                for (j = 0; j < m; j++) {
                    int index = map.get(p);
                    //System.out.println(index);
                    //    System.out.println(T.charAt(index + j));
                    if (T.charAt(index + j) != P.charAt(j + k + 1))
                        break;
                }
                if (j == m) {
                    //    System.out.println("good");
                    return true;
                }
            }
        }

        return false;
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        //long starttime = System.currentTimeMillis();
        operate();
       // out.println(System.currentTimeMillis() - starttime);
        out.close();
    }

    private static void operate() {
        int big = Integer.MAX_VALUE;
        int d = 131;
        String s1 = in.next();
        String s2 = in.next();
        String temp = (s1.length() < s2.length()) ? s1 : s2;
        s1 = (s1.length() >= s2.length()) ? s1 : s2;
        s2 = temp;
        int m = s2.length();
       /* String T = "Karpstringsearchalgorithm:Rabin-Karp";
        String P = "n–2Karp1pstri443";*/
        int mid;
        int low = 0;
        int high = m;
        while (low < high) {
            mid = (low + high) >> 1;
            // System.out.println("mid:  " + mid);
            if (Rabin_Karp_search(s1, s2, mid, d, big)) {
                low = mid;
            } else high = mid;
            //  System.out.println("low:  " + low);
            //  System.out.println("high:  " + high);
            if (low + 1 == high && !Rabin_Karp_search(s1, s2, high, d, big)) {
                high = low;
            }
            if (low + 1 == high && Rabin_Karp_search(s1, s2, high, d, big)) {
                low = high;
            }
        }
        out.println(low);
        //out.printf("最长公共连续子串的长度为：%d\n", low);
    }
}
