package lab8;

import java.io.*;
import java.util.StringTokenizer;

public class A {
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

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
    }

    private static int[][] buildAdjacentMatrix(int[][] pairs, int len) {
        int[][] matrix = new int[len][len];
        for (int[] pair : pairs) {
            matrix[pair[0] - 1][pair[1] - 1]++;
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                out.print(anInt + " ");
            }
            out.println();
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        int nodes = in.nextInt();
        int edges = in.nextInt();
        int[][] pairs = new int[edges][2];
        for (int i = 0; i < edges; i++) {
            pairs[i][0] = in.nextInt();
            pairs[i][1] = in.nextInt();
        }
        int[][] matrix = buildAdjacentMatrix(pairs, nodes);
        printMatrix(matrix);
        out.flush();
    }
}
