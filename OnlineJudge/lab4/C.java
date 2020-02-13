package lab4;

import java.util.Scanner;

public class C {
    private Node beginMarker = new Node(-1, -1, null, null);
    private Node endMarker = new Node(-1, -1, beginMarker, null);
    private int theSize = 0;

    private static class Node {
        Node prev;
        Node next;
        int data;
        int index;

        Node(int d, int i, Node p, Node n) {
            index = i;
            data = d;
            prev = p;
            next = n;
        }
    }

    private void enQuene(int d, int i) {
        this.addBefore(tail(), d, i);
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

    private void addBefore(Node p, int data, int index) {
        Node newNode = new Node(data, index, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }

    private void printQuene() {
        Node node = tail();
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


    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        C quene = new C();
        quene.beginMarker.next = quene.endMarker;
        int m = input.nextInt();
        quene.enQuene(input.nextInt(), 1);
        int next = input.nextInt();
        int index = 1;
        int sum=0;
        while (next != -1) {
            index++;
            if (quene.tail().data < next) {
                quene.remove(quene.tail());
                while (quene.theSize != 0 && quene.tail().data < next) {
                    quene.remove(quene.tail());
                }
                quene.enQuene(next, index);
            } else {
                quene.enQuene(next, index);
            }
            if (index >= m) {
               // System.out.println(quene.head().data);
                sum ^= quene.head().data;
            }
            if (quene.head().index + m - 1 == index)
                quene.deQuene();
            next = input.nextInt();

        }
        System.out.println(sum);
    }
}
