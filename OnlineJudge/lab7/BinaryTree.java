package lab7;

public class BinaryTree {
    static class Node {
        int element;
        Node left;
        Node right;

        Node(int d) {
            element = d;
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

    public static boolean isHeap(Node root, int len) {
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

    public static int[] toArray(Node root) {
        int depth = countDepth(root);
        return toArray(root, depth);
    }

    public int countLength(Node root) {
        return 0;
    }

    public static int countNodes(Node root) {
        return (root != null) ? countNodes(root.left) + countNodes(root.right) : 1;
    }

    public static int countDepth(Node root) {
        int rightdep, leftdep;
        if (root == null) return -1;

        leftdep = (root.left != null) ? countDepth(root.left) : -1;
        rightdep = (root.right != null) ? countDepth(root.right) : -1;

        return (rightdep > leftdep) ? rightdep + 1 : leftdep + 1;
    }


    public static void main(String[] args) {

    }
}
