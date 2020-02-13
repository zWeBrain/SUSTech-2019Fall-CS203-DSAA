package lab4;

public class queueArr {
    private int[] a;
    private int front;
    private int rear;

    public queueArr(int size) {
        a = new int[size];
        front = 0;
        rear = 0;
    }

    public boolean enqueue(int e) {
        if ((rear + 1) % a.length == front) {
            return false;
        }
        a[rear] = e;
        rear = (rear + 1) % a.length;
        return true;
    }

    public int dequeue() {
        int e = a[front];
        front = (front + 1) % a.length;
        return e;
    }

}
