package lab4;

public  class stackk {
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

    private void push(int d) {
        this.addBefore(tail(), d);
    }

    private void pop() {
        this.remove(tail());
    }
    public int top() {
        return tail().data;
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

    private Node remove(Node p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        Node temp = p.prev;
        p.next = p.prev = null;
        theSize--;
        return temp;
    }
    public int size() {
        return theSize;
    }
}