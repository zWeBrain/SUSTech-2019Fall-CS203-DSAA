package lab4;

import java.util.Scanner;

public class B {
    static class stack {
        private int top;
        private int MAX_SIZE;
        private char[] arr;

        stack(int MAX_SIZE) {
            this.MAX_SIZE = MAX_SIZE;
            this.top = -1;
            this.arr = new char[MAX_SIZE];
        }

        void push(char n) {
            if (top < MAX_SIZE - 1) {
                top++;
                arr[top] = n;
            }
        }

        void pop() {
            if (top > -1) top--;
        }

        char top() {
            return arr[top];
        }

        int size() {
            return top + 1;
        }
    }

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();

        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        int size = input.nextInt();
        String s = input.next();
        stack stack = new stack(size + 10);
        for (int i = 0; i < size; i++) {
            char c = s.charAt(i);
            if (stack.size() == 0){
                stack.push(c);
            }
            else if (match(stack.top(), c)) {
                stack.pop();
            } else
                stack.push(c);
        }
        if (stack.size() == 0)
            System.out.println("YES");
        else System.out.println("NO");
    }

    private static boolean match(char a, char b) {
        switch (a) {
            case '(':
                if (b == ')') return true;
                break;
            case '[':
                if (b == ']') return true;
                break;
            case '{':
                if (b == '}') return true;
                break;
        }
        return false;
    }
}
