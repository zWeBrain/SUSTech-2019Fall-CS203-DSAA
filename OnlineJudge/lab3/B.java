package lab3;

import java.util.Scanner;

public class B {
    private Node beginMarker = new Node(-1, null, null);
    private Node endMarker = new Node(-1, beginMarker, null);

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

    private void addBefore(Node p, int data) {
        Node newNode = new Node(data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;

    }

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }

    }

    private static void operate() {
        int length = input.nextInt();
        int times = input.nextInt();
        B list = new B();
        list.beginMarker.next = list.endMarker;
        Node[] arr = new Node[length];
        for (int i = 0; i < length; i++) {
            int tmp = input.nextInt();
            list.addBefore(list.endMarker, tmp);
            arr[tmp] = list.endMarker.prev;
        }
        for (int i = 0; i < times; i++) {
            swap(arr);
        }
        Node temp = list.beginMarker.next;
        for (int i = 0; i < length; i++) {
            System.out.print(temp.data + ((i == length - 1) ? "\n" : " "));
            temp = temp.next;
        }
        list.beginMarker = new Node(-1, null, null);
        list.endMarker = new Node(-1, list.beginMarker, null);
        list.beginMarker.next = list.endMarker;
    }

    private static void swap(Node[] arr) {
        int x1 = input.nextInt();
        int x2 = input.nextInt();
        int y1 = input.nextInt();
        int y2 = input.nextInt();

        Node temp = arr[x1];
        Node temp_ = temp.prev;
        arr[x1].prev.next = arr[y1];
        arr[x1].prev = arr[y1].prev;
        arr[y1].prev.next = temp.next.prev;
        arr[y1].prev = temp_;

        temp = arr[x2];
        temp_ = temp.next;
        arr[x2].next.prev = arr[y2];
        arr[x2].next = arr[y2].next;
        arr[y2].next.prev = temp.prev.next;
        arr[y2].next = temp_;

    }
}
