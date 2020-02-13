package lab3;

import java.util.Scanner;

public class C {
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

    public int remove(Node p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.next = p.prev = null;
        return p.data;
    }

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = Integer.parseInt(input.nextLine().trim());
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        Coverted linkedlist = new Coverted();
        linkedlist.beginMarker.next = linkedlist.endMarker;
        Node flag = linkedlist.endMarker;
        int operand = Integer.parseInt(input.nextLine().trim());
        String command = input.nextLine().trim();
        for (int i = 0; i < operand; i++) {
            char current = command.charAt(i);
            switch (current) {
                case 'r':
                    if (i != operand - 1) {
                        i++;
                        int next = Integer.parseInt(String.valueOf(command.charAt(i)));
                        if (flag == linkedlist.endMarker) {
                            linkedlist.addBefore(flag, next);
                            flag = flag.prev;
                        } else flag.data = next;
                    }
                    break;
                case 'I':
                    flag = linkedlist.beginMarker.next;
                    break;
                case 'H':
                    if (flag != linkedlist.beginMarker.next)
                        flag = flag.prev;
                    break;
                case 'L':
                    if (flag != linkedlist.endMarker)
                        flag = flag.next;
                    break;
                case 'x':
                    if (flag != linkedlist.endMarker) {
                        Node temp1 = flag.next;
                        Node temp2 = flag.prev;
                        linkedlist.remove(flag);
                        flag = temp1;
                        flag.prev = temp2;
                    }
                    break;
                default:
                    linkedlist.addBefore(flag, Integer.parseInt(String.valueOf(current)));
                    break;
            }

        }
        Node temp = linkedlist.beginMarker.next;
        while (temp != linkedlist.endMarker) {
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println();
        linkedlist.beginMarker = new Node(-1, null, null);
        linkedlist.endMarker = new Node(-1, linkedlist.beginMarker, null);
        linkedlist.beginMarker.next = linkedlist.endMarker;
    }
}
