package lab6;

import java.io.*;
import java.util.StringTokenizer;

public class C {
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

    private static class bTreeNode {
        bTreeNode left;
        bTreeNode right;
        int element;

        bTreeNode(int d) {
            element = d;
        }
    }

    private static void addLeft(bTreeNode p, int d) {
        p.left = new bTreeNode(d);
    }

    private static void addRight(bTreeNode p, int d) {
        p.right = new bTreeNode(d);
    }

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
            out.println();
        }
        out.close();
    }

    private static int binarySearch(int[] arr, int key) {
        /*int low = 0;
        int high = arr.length - 1;
        int mid;

        while (low < high) {
            mid = (low + high) / 2;
            if (arr[mid] > key) {
                high = mid;
            } else if (arr[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;*/
        int i = 0;
        while (i < arr.length && arr[i] != key) i++;
        return i;
    }

    private static void operate() {
        int len = in.nextInt();
        int[] preOrder = new int[len];
        int[] inOrder = new int[len];
        for (int i = 0; i < len; i++) {
            preOrder[i] = in.nextInt();
        }
        for (int i = 0; i < len; i++) {
            inOrder[i] = in.nextInt();
        }

        bTreeNode root = new bTreeNode(preOrder[0]);

        int index = binarySearch(inOrder, preOrder[0]);
        rec(1, index, index, len - 1 - index, preOrder, inOrder, root);
        doTravelRecursion(root);
    }

    private static void rec(int start, int index, int left, int right, int[] preOrder, int[] inOrder, bTreeNode node) {
        if (left != 0) {
            addLeft(node, preOrder[start]);
            int index1 = binarySearch(inOrder, preOrder[start]);
            rec(start + 1, index1, index1 - index + left, index - index1 - 1, preOrder, inOrder, node.left);

        }
        if (right != 0) {
            addRight(node, preOrder[start + left]);
            int index2 = binarySearch(inOrder, preOrder[start + left]);
            rec(start + left + 1, index2, index2 - index - 1, index + right - index2, preOrder, inOrder, node.right);
        }

    }

    private static void doTravelRecursion(bTreeNode bTreeNode) {
        if (bTreeNode == null)
            return;
        if (bTreeNode.left != null)
            doTravelRecursion(bTreeNode.left);
        if (bTreeNode.right != null)
            doTravelRecursion(bTreeNode.right);
        out.print(bTreeNode.element + " ");
    }


}
