package lab6;

import java.io.*;
import java.util.*;


public class F_tle {
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
        int[] index;
        TreeNode firstChild;
        TreeNode nextSibling;
        int element;

        TreeNode(int d, int[] indexs) {
            element = d;
            index = indexs;
        }

    }

    public static TreeNode add(TreeNode p, int d, int i) {
        int[] newIndex = new int[p.index.length + 1];
        System.arraycopy(p.index, 0, newIndex, 0, p.index.length);
        newIndex[p.index.length] = i;
        if (p.firstChild == null) {
            p.firstChild = new TreeNode(d, newIndex);
            return p.firstChild;
        } else {
            TreeNode node = new TreeNode(d, newIndex);
            node.nextSibling = p.firstChild.nextSibling;
            p.firstChild.nextSibling = node;
            return p.firstChild.nextSibling;
        }
    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    private static int[] min = new int[1];
    private static int[] Count = new int[1];

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        out.close();
    }

    private static void operate() {
        Count[0] = 0;
        TreeNode root = new TreeNode(1, new int[1]);
        int nodes = in.nextInt();
        int peoples = in.nextInt();
        buildTree(root, nodes, peoples);
       /* TreeNode node1 = root.firstChild.nextSibling.nextSibling.firstChild;
        System.out.println(getShare(node1.index, node1.index));*/
    }

    private static void buildTree(TreeNode root, int nodes, int peoples) {
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

        Set<Integer> target = new HashSet<>();
        for (int i = 0; i < peoples; i++) {
            target.add(in.nextInt());
        }

        int[][] peos = new int[target.size()][];

        if (target.contains(1)) {
            peos[Count[0]++] = new int[]{ 0 };
        }

        build(root, lists, target, peos);
/*
        System.out.println(root.firstChild.firstChild.nextSibling.index[2]);
        System.out.println(peos[1][2]);*/
       /* for (int i = 0; i < peos.length; i++) {
            for (int j = 0; j < peos[i].length; j++) {
                System.out.print(peos[i][j] + " ");
            }
            System.out.println();
        }*/

        min[0] = calDistance(root.index, peos);
        travelByLevel(root, min, peos);
        out.println(min[0]);
        out.flush();
    }

    private static void build(TreeNode root, ArrayList[] lists, Set target, int[][] peos) {

        for (int i = 0; i < lists[root.element - 1].size(); i++) {
            int child = (int) lists[root.element - 1].get(i);
            lists[child - 1].remove((Integer) root.element);
            TreeNode node = add(root, child, i);
            if (target.contains(child)) {
                peos[Count[0]++] = node.index;
            }

            build(node, lists, target, peos);
        }
    }

    private static int calDistance(int[] node, int[][] peos) {
        int max = 0;
        for (int[] peo : peos) {
            int share = getShare(node, peo);
            int len = peo.length + node.length - 2 * (share);
            max = Math.max(len, max);
        }
        return max;
    }

    private static int getShare(int[] node, int[] key) {
        int low = 0;
        int high = Math.min(node.length, key.length);
        int mid;
        while (low < high) {
            mid = (low + high) >> 1;
            if (node[mid] != key[mid]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static void travelByLevel(TreeNode root, int[] min, int[][] peos) {
        TreeNode node = root.firstChild;
        int next;
        while (node != null) {
            next = calDistance(node.index, peos);
           /* System.out.println(getShare(node.index,peos[0]));
            System.out.println(getShare(node.index,peos[1]));
            System.out.println(node.element + "  " + next);*/
           //<=  ->  <
            if (next < min[0]) {
                min[0] = next;
                // System.out.println(next);
                travelByLevel(node, min, peos);
            }
            node = node.nextSibling;
        }
    }
}
/*
2
10 4
1 2
1 3
1 4
9 5
2 6
3 7
5 8
4 9
7 10
8 8 8 9
10 4
1 2
1 3
1 4
2 5
2 6
3 7
3 8
4 9
7 10
10 10 10 8
*/