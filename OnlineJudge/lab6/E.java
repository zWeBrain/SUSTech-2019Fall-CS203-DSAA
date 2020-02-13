package lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static int RED = 0;
    private static int BLUE = 0;
    private static int[] sum = new int[1];

    private static class TreeNode {
        TreeNode firstChild;
        TreeNode nextSibling;
        int element;
        int color;
        int red;
        int blue;

        /*TreeNode(int e, int c, int r, int b) {
            element = e;
            color = c;
            red = r;
            blue = b;
            if (color == 1) {
                red++;
            } else if (color == 2) {
                blue++;
            }
        }*/

        TreeNode(int e) {
            element = e;
            red = 0;
            blue = 0;
        }
    }

    /*public static TreeNode add(TreeNode p, int e, int c) {
        if (p.firstChild == null) {
            p.firstChild = new TreeNode(e, c, p.red, p.blue);
            return p.firstChild;
        } else {
            TreeNode node = new TreeNode(e, c, p.red, p.blue);
            node.nextSibling = p.firstChild.nextSibling;
            p.firstChild.nextSibling = node;
            return p.firstChild.nextSibling;
        }
    }*/

    public static TreeNode add(TreeNode p, int e) {
        if (p.firstChild == null) {
            p.firstChild = new TreeNode(e);
            return p.firstChild;
        } else {
            TreeNode node = new TreeNode(e);
            node.nextSibling = p.firstChild.nextSibling;
            p.firstChild.nextSibling = node;
            return p.firstChild.nextSibling;
        }
    }

    private static void travelByLevel(TreeNode root, int blue, int red) {
        if (root.element != 1 && ((root.blue == blue && root.red == 0) || (root.red == red && root.blue == 0))) {
            sum[0]++;
           // System.out.println("  " + root.element + " " + root.red + " " + root.blue);
        }

        TreeNode node = root.firstChild;
        while (node != null) {
            travelByLevel(node, blue, red);
            node = node.nextSibling;
        }
    }

    private static TreeNode buildTree(int nodes) {
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
        int[] colors = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            colors[i] = in.nextInt();
            if (colors[i] == 1) RED++;
            else if (colors[i] == 2) BLUE++;
        }
        TreeNode root = new TreeNode(1);
        build(root, lists, colors);
        return root;
    }

    private static void build(TreeNode root, ArrayList[] lists, int[] colors) {

        for (int i = 0; i < lists[root.element - 1].size(); i++) {
            int child = (int) lists[root.element - 1].get(i);
            lists[child - 1].remove((Integer) root.element);
            //TreeNode node = add(root, child, colors[child - 1]);
            TreeNode node = add(root, child);
            build(node, lists, colors);
            node.color = colors[child - 1];
            if (node.color == 1) {
                node.red++;
            } else if (node.color == 2) {
                node.blue++;
            }
            root.red += node.red;
            root.blue += node.blue;
        }
    }

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {

        int nodes = in.nextInt();
        TreeNode root = buildTree(nodes);
       /* System.out.println(RED);
        System.out.println(BLUE);*/
        travelByLevel(root, BLUE, RED);
        System.out.println(sum[0]);
        RED = 0;
        BLUE = 0;
        sum[0] = 0;
    }
}
