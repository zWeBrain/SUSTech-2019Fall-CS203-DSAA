package lab7;

import java.util.Scanner;

public class A {

    static class Node {
        int element;
        Node left;
        Node right;

        Node(int d) {
            element = d;
        }

    }

    private static Scanner in = new Scanner(System.in);

    private static Node build(int len) {
        int[][] childs = new int[len + 1][2];
        int[] tags =new int[len+1];
        for (int i = 1; i < len + 1; i++) {
            childs[i][0] = in.nextInt();
            childs[i][1] = in.nextInt();
            tags[childs[i][0]]=1;
            tags[childs[i][1]]=1;
        }
        int root_index=0;
        for (int i = 1; i < len+1; i++) {
            if (tags[i]!=1) {
                root_index=i;
                break;
            }
        }
        Node root =new Node(root_index);
        buildNode(root, childs);
        return root;
    }

    private static void buildNode(Node root, int[][] childs) {
        if (root != null && childs.length > 0) {
            if (childs[root.element][0] != 0) {
                root.left = new Node(childs[root.element][0]);
                buildNode(root.left, childs);
            }
            if (childs[root.element][1] != 0) {
                root.right = new Node(childs[root.element][1]);
                buildNode(root.right, childs);
            }
        }
    }

    public static int[] toArray(Node root, int depth) {
        int MaxLen = 1;
        for (int i = 0; i <= depth; i++) {
            MaxLen *= 2;
        }
        MaxLen--;
        int[] array = new int[MaxLen];
        fillIn(array, root, 0);
        return array;
    }

    public static void fillIn(int[] array, Node root, int index) {
        array[index] = root.element;
        if (root.left != null) fillIn(array, root.left, 2 * index + 1);
        if (root.right != null) fillIn(array, root.right, 2 * index + 2);
    }

    public static int countDepth(Node root) {
        int rightdep, leftdep;

        if (root == null)
            return -1;

        leftdep = (root.left != null) ? countDepth(root.left) : -1;
        rightdep = (root.right != null) ? countDepth(root.right) : -1;

        return (rightdep > leftdep) ? rightdep + 1 : leftdep + 1;

    }

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    public static boolean isCompleted(Node root, int len) {
        int limit = (int) (Math.log(len) / Math.log(2)) + 1;
        int depth = countDepth(root);
        if (depth <= limit) {
            int[] array = toArray(root, depth);
            for (int i = 0; i < array.length; i++)
                if (array[i] == 0 && i < len) return false;
            return true;
        } else return false;
    }

    private static void operate() {
        int len = in.nextInt();
        Node root = build(len);
        System.out.println(isCompleted(root, len)?"Yes":"No");
    }
}
