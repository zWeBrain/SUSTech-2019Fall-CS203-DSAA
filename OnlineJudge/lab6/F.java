package lab6;

import java.io.*;
import java.util.*;

public class F {

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

    private static class TreeNode {
        int depth;
        TreeNode father;
        TreeNode firstChild;
        TreeNode nextSibling;
        int element;

        TreeNode(int e, int d) {
            element = e;
            depth = d;
        }

        TreeNode(TreeNode f, int e, int d) {
            father = f;
            element = e;
            depth = d;
        }

    }

    public static TreeNode add(TreeNode p, int d) {
        if (p.firstChild == null) {
            p.firstChild = new TreeNode(p, d, p.depth + 1);
            return p.firstChild;
        } else {
            TreeNode node = new TreeNode(p, d, p.depth + 1);
            node.nextSibling = p.firstChild.nextSibling;
            p.firstChild.nextSibling = node;
            return p.firstChild.nextSibling;
        }
    }

   /* public static TreeNode add(TreeNode p, TreeNode K) {
        if (p.firstChild == null) {
            p.firstChild = K;
        } else {
            K.nextSibling = p.firstChild.nextSibling;
            p.firstChild.nextSibling = K;
        }
        return K;
    }*/

    private static void build(TreeNode root, ArrayList[] lists, Set sum) {

        for (int i = 0; i < lists[root.element - 1].size(); i++) {
            int child = (int) lists[root.element - 1].get(i);
            lists[child - 1].remove((Integer) root.element);
            TreeNode node = add(root, child);
            if (sum.contains(node.element) && node.depth > deep.depth) deep = node;
            build(node, lists, sum);
        }

    }

    private static void travelByLevel(TreeNode root) {
        System.out.print(root.element + "(" + root.depth + ") ");
        TreeNode node = root.firstChild;
        while (node != null) {
            travelByLevel(node);
            node = node.nextSibling;
        }
    }


    private static void rebuild(TreeNode fresh, TreeNode root, int father, int[] count, int N, Set sum) {
        if (root.father != null && root.father.element != father) {
            TreeNode node = add(fresh, root.father.element);
            rebuild(node, root.father, fresh.element, count, N, sum);
            count[0]++;
            int tmp = node.depth;
            if (sum.contains(node.element) && deepest[0] < tmp) deepest[0] = tmp;
            if (count[0] == N)
                return;
        }
        TreeNode node = root.firstChild;
        while (node != null) {
            if (node.element != father) {
                TreeNode node1 = add(fresh, node.element);
                rebuild(node1, node, fresh.element, count, N, sum);
                count[0]++;
                int tmp = node1.depth;
                if (sum.contains(node1.element) && deepest[0] < tmp) deepest[0] = tmp;
                if (count[0] == N)
                    return;
            }
            node = node.nextSibling;
        }

    }

    private static Set buildTree(TreeNode root, int nodes, int peoples) {
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
        Set<Integer> sum = new HashSet();
        for (int i = 0; i < peoples; i++) {
            sum.add(in.nextInt());
        }
        build(root, lists, sum);
        return sum;
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static int[] count = new int[1];
    private static int[] deepest = new int[1];
    private static TreeNode deep = new TreeNode(null, 0, -1);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
            count[0] = 0;
            deepest[0] = 0;
            deep = new TreeNode(null, 0, -1);
        }
        out.close();
    }


    private static void operate() {
        TreeNode root = new TreeNode(1, 0);
        int nodes = in.nextInt();
        int peoples = in.nextInt();
        Set sum = buildTree(root, nodes, peoples);
        //travelByLevel(root);
        //System.out.println(sum);
        TreeNode fresh = new TreeNode(deep.element, 0);
        rebuild(fresh, deep, 0, count, nodes, sum);
        //travelByLevel(fresh);
        //out.println(deepest[0]);
        out.println((deepest[0] + 1) / 2);
        out.flush();
    }
}
