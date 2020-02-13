package lab7;

import java.util.Scanner;

import static lab7.BinaryTree.countDepth;
import static lab7.BinaryTree.toArray;

public class A_good {
    static class bstNode {
        int element;
        bstNode left;
        bstNode right;

        bstNode(int d) {
            element = d;
        }

    }

    static class NodeQuene {
        class Node {
            Node prev;
            Node next;
            bstNode data;

            Node(bstNode d, Node p, Node n) {
                data = d;
                prev = p;
                next = n;
            }
        }

        Node beginMarker = new Node(null, null, null);
        Node endMarker = new Node(null, beginMarker, null);
        int theSize = 0;

        void enQuene(bstNode d) {
            this.addBefore(tail(), d);
        }

        bstNode deQuene() {
            bstNode b = head().data;
            this.remove(head());
            return b;
        }

        private Node head() {
            return endMarker.prev;
        }

        private Node tail() {
            return beginMarker.next;
        }

        private void addBefore(Node p, bstNode data) {
            Node newNode = new Node(data, p.prev, p);
            newNode.prev.next = newNode;
            p.prev = newNode;
            theSize++;
        }

        private Node remove(Node p) {
            p.prev.next = p.next;
            p.next.prev = p.prev;
            Node temp = p.prev;
            p.next = p.prev = null;
            theSize--;
            return temp;
        }
    }

    private static Scanner in = new Scanner(System.in);

    private static bstNode build() {
        int len = in.nextInt();
        bstNode root = new bstNode(1);
        int[][] childs = new int[len][2];
        for (int i = 0; i < len; i++) {
            childs[i][0] = in.nextInt();
            childs[i][1] = in.nextInt();
        }
        buildNode(root, childs);
        return root;
    }

    private static void buildNode(bstNode root, int[][] childs) {
        if (root != null && childs.length > 0) {
            if (childs[root.element - 1][0] != 0) {
                root.left = new bstNode(childs[root.element - 1][0]);
                buildNode(root.left, childs);
            }
            if (childs[root.element - 1][1] != 0) {
                root.right = new bstNode(childs[root.element - 1][1]);
                buildNode(root.right, childs);
            }
        }
    }

    private static boolean isCompleted(bstNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        NodeQuene quene = new NodeQuene();
        quene.beginMarker.next = quene.endMarker;
        quene.enQuene(root);
        boolean leaf = false;
        while (quene.theSize != 0) {
            bstNode tmp = quene.deQuene();
            if ((leaf && (tmp.left != null || tmp.right != null))
                    || (tmp.left == null && tmp.right != null))
                return false;
            if (tmp.left != null) quene.enQuene(tmp.left);
            if (tmp.right != null) quene.enQuene(tmp.right);
            if ((tmp.left != null && tmp.right == null) || (tmp.left == null && tmp.right == null))
                leaf = true;
        }
        return true;
    }

    private static void travel(bstNode root) {

        NodeQuene quene = new NodeQuene();
        quene.beginMarker.next = quene.endMarker;
        quene.enQuene(root);

        while (quene.theSize != 0) {
            bstNode tmp = quene.deQuene();
            System.out.println(tmp.element);
            if (tmp.left != null) quene.enQuene(tmp.left);
            if (tmp.right != null) quene.enQuene(tmp.right);
        }
    }

    private static boolean isHeap() {

        return true;
    }

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        bstNode root = build();
        //travel(root);
        System.out.println(isCompleted(root) ? "Yes" : "No");
      //  int[] array =toArray(root,countDepth(root));
    }
}
