package lab4;

public class stack {
    private int top;
    private int MAX_SIZE;
    private int[] arr;

    public stack(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
        this.top = -1;
        this.arr = new int[MAX_SIZE];
    }

    public void push(int n) {
        if (top < MAX_SIZE - 1) {
            top++;
            arr[top] = n;
        } else
            System.out.println("???");
    }

    public void pop() {
        if (top != -1) top--;
    }

    public int top() {
        return arr[top];
    }

    public int size() {
        return top + 1;
    }
}