package lab3;

import java.util.Scanner;

class Node {
    Pair item;
    Node next;

    Node(Pair element, Node next) {
        this.item = element;
        this.next = next;
    }

    Node() {
    }
}

class Pair {
    int L;
    int R;

    Pair(int L, int R) {
        this.L = L;
        this.R = R;
    }

}

public class A {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        int first = input.nextInt();
        Node node_first = new Node();
        initial(node_first, first);
        int second = input.nextInt();
        Node node_second = new Node();
        initial(node_second, second);
        print(node_first);
        print(node_first);
        System.out.println();
        print(node_second);
        System.out.println();
        Node node_result = new Node();
        addTwo(node_result, node_first, node_second);
        print(node_result);
    }

    private static void initial(Node node, int count) {

        if (count == 0) return;
        int L = input.nextInt();
        int R = input.nextInt();
        Pair pair = new Pair(L, R);
        node.next = new Node(pair, null);
        initial(node.next, count - 1);

    }

    private static void print(Node node) {
        if (node.item != null)
            System.out.println(node.item.L + " " + node.item.R);
        if (node.next != null) print(node.next);
    }

    private static void addTwo(Node node, Node first, Node second) {
        if (first.next != null || second.next != null) {
            if (first.item == null && second.item == null) {
                System.out.println("null");
                addTwo(node, first.next, second.next);

            } else if (second.item == null || first.item.R < second.item.R) {
                Node node_new = new Node(new Pair(first.item.L, first.item.R), null);
                node.next = node_new;
                addTwo(node_new, first.next, second);
            } else if (first.item == null || first.item.R > second.item.R) {
                Node node_new = new Node(new Pair(second.item.L, second.item.R), null);
                node.next = node_new;
                addTwo(node_new, first, second.next);
            } else {
                int L = second.item.L + first.item.L;
                Pair pair = new Pair(L, first.item.R);
                Node node_new = new Node(pair, null);
                node.next = node_new;
                addTwo(node_new, first.next, second.next);
            }

        }
    }
}
