package lab8;

import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class B {
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

    private static int[][] buildAdjacentMatrix(int[][] masters, int length, int width) {
        int[][] matrix = new int[masters.length + 2][masters.length + 2];
        int x1, y1, r1, x2, y2, r2;
        for (int i = 0; i < masters.length; i++) {
            x1 = masters[i][0];
            y1 = masters[i][1];
            r1 = masters[i][2];

            if (x1 - r1 <= 0 || y1 + r1 >= width) {
                matrix[0][i + 1] = 1;
                matrix[i + 1][0] = 1;
            }

            if (y1 - r1 <= 0 || x1 + r1 >= length) {
                matrix[masters.length + 1][i + 1] = 1;
                matrix[i + 1][masters.length + 1] = 1;
            }

            for (int j = i + 1; j < masters.length; j++) {
                x2 = masters[j][0];
                y2 = masters[j][1];
                r2 = masters[j][2];
                double dis = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
                double cDis = (r1 + r2) * (r1 + r2);
                if (dis < cDis) {
                    matrix[i + 1][j + 1] = 1;
                    matrix[j + 1][i + 1] = 1;
                }
            }

        }

        return matrix;
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                out.print(anInt + " ");
            }
            out.println();
        }
    }

    private static boolean BFS(int[][] matrix) {
        int[] visited = new int[matrix.length];
        int target = matrix.length - 1;
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue();
        queue.add(0);
        visited[0] = 1;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            for (int i = 0; i < matrix.length; i++)
                if (matrix[q][i] == 1 && visited[i] == 0) {
                    visited[i] = 1;
                    queue.add(i);
                    if (i == target) return true;
                }
        }
        return false;
    }

    private static void operate() {
        int length = in.nextInt();
        int width = in.nextInt();
        int master = in.nextInt();
        int[][] masters = new int[master][3];
        for (int i = 0; i < master; i++) {
            masters[i][0] = in.nextInt();
            masters[i][1] = in.nextInt();
            masters[i][2] = in.nextInt();
        }
        int[][] matrix = buildAdjacentMatrix(masters, length, width);
       // printMatrix(matrix);
        out.println(BFS(matrix) ? "No" : "Yes");
        out.flush();
    }
}
