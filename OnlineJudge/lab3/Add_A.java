package lab3;

import java.util.Scanner;


public class Add_A {
    private static Scanner input = new Scanner(System.in);
    private int theSize;
    private Node beginMarker = new Node(0, -1, null, null);
    private Node endMarker = new Node(0, -1, beginMarker, null);

    private static class Node {
        Node prev;
        Node next;
        int coef;
        int expo;

        Node(int c, int e, Node p, Node n) {
            coef = c;
            expo = e;
            prev = p;
            next = n;
        }
    }

    public int size() {
        return theSize;
    }

    public void addBefore(Node p, int t, int e) {
        Node newNode = new Node(t, e, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
    }

    public int remove(Node p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        p.next = p.prev = null;
        theSize--;
        return p.coef;
    }

    public static Add_A add(Add_A list1, Add_A list2) {
        Add_A list = new Add_A();
        list.beginMarker.next = list.endMarker;

        Node temp1 = list1.beginMarker.next;
        Node temp2 = list2.beginMarker.next;
        int idx1 = list1.size();
        int idx2 = list2.size();
        boolean isOK = true;
        while (isOK) {
            if (idx1 == 0) {
                list.addBefore(list.endMarker, temp2.coef, temp2.expo);
                temp2 = temp2.next;
                idx2--;
            } else if (idx2 == 0 || temp1.expo < temp2.expo) {
                list.addBefore(list.endMarker, temp1.coef, temp1.expo);
                temp1 = temp1.next;
                idx1--;
            } else if (temp1.expo > temp2.expo) {
                list.addBefore(list.endMarker, temp2.coef, temp2.expo);
                temp2 = temp2.next;
                idx2--;
            } else {
                list.addBefore(list.endMarker, temp1.coef + temp2.coef, temp2.expo);
                temp1 = temp1.next;
                temp2 = temp2.next;
                idx1--;
                idx2--;
            }
            if (idx1 == 0 && idx2 == 0) {
                isOK = false;
            }
        }
        return list;
    }

    public static void main(String[] args) {

        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
        System.out.println();
    }

    private static void operate() {
        Add_A list1 = new Add_A();
        Add_A list2 = new Add_A();
        list1.beginMarker.next = list1.endMarker;
        list2.beginMarker.next = list2.endMarker;
        int first = input.nextInt();
        initial(list1, first);
        int second = input.nextInt();
        initial(list2, second);
        Add_A list = add(list1, list2);
        test(list);
    }

    private static void test(Add_A list) {
        int count = input.nextInt();
        Node tmp = list.beginMarker.next;
        for (int i = 0; i < count; i++) {
            int target = input.nextInt();
            boolean finding = true;
            while (finding) {
                if (tmp.expo == -1) {
                    System.out.print("0 ");
                    finding = false;
                } else if (tmp.expo < target) {
                    tmp = tmp.next;
                } else if (tmp.expo == target) {
                    System.out.print(tmp.coef + " ");
                    finding = false;
                } else {
                    System.out.print("0 ");
                    finding = false;
                }
            }


        }
    }

    private static void initial(Add_A list, int count) {
        for (int i = 0; i < count; i++) {
            list.addBefore(list.endMarker, input.nextInt(), input.nextInt());
        }
    }

}

