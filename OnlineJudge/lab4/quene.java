package lab4;

import java.util.Scanner;

public class quene {
    private Node beginMarker = new Node(-1, null, null);
    private Node endMarker = new Node(-1, beginMarker, null);
    private int theSize = 0;

    private static class Node {
        Node prev;
        Node next;
        int data;

        Node(int d, Node p, Node n) {
            data = d;
            prev = p;
            next = n;
        }
    }

    private void enQuene(int d) {
        this.addBefore(tail(), d);
    }

    private void deQuene() {
        this.remove(head());
    }

    private Node head() {
        return endMarker.prev;
    }

    private Node tail() {
        return beginMarker.next;
    }

    private void addBefore(Node p, int data) {
        Node newNode = new Node(data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }

    private void printQuene() {
        Node node = beginMarker.next;
        for (int i = 0; i < theSize; i++) {
            System.out.print(node.data + "  ");
            node = node.next;
        }
        System.out.println();
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
