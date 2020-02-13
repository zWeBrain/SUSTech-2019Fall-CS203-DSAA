package lab7;

import java.util.Scanner;

public class B {
    static class Node {
        int element;
        Node left;
        Node right;

        Node(int d) {
            element = d;
        }

    }

    private static Scanner in = new Scanner(System.in);


    private static boolean isAHeap(int len) {
        Node[] NODES = new Node[len];
        for (int i = 0; i < len; i++) {
            NODES[i] = new Node(in.nextInt());
        }
        Node root = NODES[in.nextInt() - 1];
        for (int i = 0; i < len - 1; i++) {
            Node father = (i == 0) ? root : NODES[in.nextInt() - 1];
            Node child = NODES[in.nextInt() - 1];
            if (father.left == null) father.left = child;
            else if (father.right == null) father.right = child;
            else return false;
        }
        return isHeap1(root,len)||isHeap2(root,len);
    }

    public static boolean isHeap1(Node root, int len) {
        int limit = (int) (Math.log(len) / Math.log(2)) + 1;
        int depth = countDepth(root);
        if (depth <= limit) {
            int[] array = toArray(root, depth);
            //for (int value : array) System.out.print(value + " ");
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0 && i < len)
                    return false;
                if (array[i] != 0 && (2 * i + 2 < array.length) && (
                        (array[i] > array[2 * i + 1] && array[2 * i + 1] != 0) ||
                                (array[i] > array[2 * i + 2] && array[2 * i + 2] != 0)))
                    return false;
            }
            return true;
        } else return false;
    }
    public static boolean isHeap2(Node root, int len) {
        int limit = (int) (Math.log(len) / Math.log(2)) + 1;
        int depth = countDepth(root);
        if (depth <= limit) {
            int[] array = toArray(root, depth);
            //for (int value : array) System.out.print(value + " ");
            for (int i = 0; i < array.length; i++) {
                if (array[i] == 0 && i < len)
                    return false;
                if (array[i] != 0 && (2 * i + 2 < array.length) && (
                        (array[i] < array[2 * i + 1] && array[2 * i + 1] != 0) ||
                                (array[i] < array[2 * i + 2] && array[2 * i + 2] != 0)))
                    return false;
            }
            return true;
        } else return false;
    }

    public static int[] toArray(Node root, int depth) {
        int MaxLen = 1;
        for (int i = 0; i <= depth; i++) MaxLen *= 2;
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
        if (root == null) return -1;

        leftdep = (root.left != null) ? countDepth(root.left) : -1;
        rightdep = (root.right != null) ? countDepth(root.right) : -1;

        return (rightdep > leftdep) ? rightdep + 1 : leftdep + 1;

    }
    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            System.out.print("Case #"+(i+1)+": ");
            operate();
        }
    }

    private static void operate() {
        int len = in.nextInt();
        System.out.println(isAHeap(len)?"YES":"NO");
    }
}
