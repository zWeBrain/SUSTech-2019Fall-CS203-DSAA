package lab3;

import java.util.Scanner;

public class D {
    private Node beginMarker = new Node(-1, -1, null, null);
    private Node endMarker = new Node(-2, -1, beginMarker, null);
    private int theSize = 0;

    private static class Node {
        Node prev;
        Node next;
        int data;
        int index;

        Node(int i, int d, Node p, Node n) {
            index = i;
            data = d;
            prev = p;
            next = n;
        }
    }

    private void addBefore(Node p, int data, int index) {
        Node newNode = new Node(index, data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }

    public Node remove(Node p, D list) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        Node temp = p.prev;
        p.next = p.prev = null;
        theSize--;
        if (temp.index == -1) return list.endMarker.prev;
        else return temp;
    }

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }

    }

    private static void operate() {
        D list = new D();
        list.beginMarker.next = list.endMarker;
        int count = input.nextInt();
        int begin = input.nextInt();
        begin = (begin - 1) % count;
        for (int i = 0; i < count; i++) {
            list.addBefore(list.endMarker, input.nextInt(), i + 1);
        }

        Node flag = list.beginMarker.next;
        while (list.theSize > 1) {
            if (begin <= list.theSize / 2) {
                for (int i = 0; i < begin; i++) {
                    if (flag.next.index == -2) {
                        flag = list.beginMarker.next;
                    } else flag = flag.next;
                }
            } else {
                for (int i = 0; i < list.theSize - begin; i++) {
                    if (flag.prev.index == -1) {
                        flag = list.endMarker.prev;
                    } else flag = flag.prev;
                }
            }

            begin = (flag.data) % (list.theSize - 1);
            flag = list.remove(flag, list);
        }
        System.out.println(list.beginMarker.next.index);
    }
}
