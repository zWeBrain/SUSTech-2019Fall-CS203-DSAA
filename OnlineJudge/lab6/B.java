package lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B {
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

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {
        int nodes = in.nextInt();
        ArrayList[] lists = new ArrayList[nodes];

        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < nodes - 1; i++) {

            int left = in.nextInt();
            int right = in.nextInt();
            lists[left - 1].add(right);
            lists[right - 1].add(left);

        }
        /*for (int i = 0; i < nodes; i++) {
            System.out.println(lists[i]);
        }*/
        int[] result =new int[nodes];
        find(1, lists, 0,result);
        out.print(result[0]);
        for (int i = 1; i < result.length; i++) {
            out.print(" " + result[i]);
        }
        out.println();
        out.flush();
    }

    private static void find(int node, ArrayList[] lists, int depth,int[] result) {
        if (lists[node - 1].isEmpty()) {
            result[node-1]=depth;
        } else {
            for (int i = 0; i < lists[node - 1].size(); i++) {
                int next = (int) lists[node - 1].get(i);
                lists[next - 1].remove((Integer) node);

                find(next, lists, depth + 1,result);
            }
            result[node-1]=depth;
        }
    }
}
/*
4
10
1 2
1 3
4 2
2 5
2 6
7 5
8 3
3 9
10 9
3
1 2
2 3
1
2
2 1
 */