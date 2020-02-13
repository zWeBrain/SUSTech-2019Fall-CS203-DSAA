package lab4;

import java.io.*;
import java.util.StringTokenizer;

public class D {
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

    static class stack {
        private int top;
        private int MAX_SIZE;
        private int[] arr;

        stack(int MAX_SIZE) {
            this.MAX_SIZE = MAX_SIZE;
            this.top = -1;
            this.arr = new int[MAX_SIZE];
        }

        void push(int n) {
            if (top < MAX_SIZE - 1) {
                top++;
                arr[top] = n;
            } else
                System.out.println("???");
        }

        void pop() {
            if (top != -1) {
                out.print(top() + " ");
                top--;
            }
        }

        int top() {
            return arr[top];
        }

        public int size() {
            return top + 1;
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {


        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
            out.println();
        }
        out.close();
    }

    private static void operate() {
        int length = in.nextInt();
        int[] arr = new int[length];
        int[] min = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = in.nextInt();
        }
        min[length - 1] = arr[length - 1];
        for (int i = 1; i < length; i++) {
            min[length - i - 1] = Math.min(arr[length - i - 1], min[length - i]);
        }

        stack stack = new stack(length);
        stack.push(arr[0]);
        for (int i = 1; i < length; i++) {
            if (stack.top() == min[i - 1]) {
                stack.pop();
                while (stack.size() > 0 && stack.top() < min[i]) {
                    stack.pop();
                }
            }
            stack.push(arr[i]);
            while (i == length - 1 && stack.size() != 0) {
                stack.pop();
            }
        }
    }
}
