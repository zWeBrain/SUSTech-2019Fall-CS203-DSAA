package lab4;

import java.io.*;
import java.util.StringTokenizer;

public class E {
    static class stack {
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

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
        int test = in.nextInt();
        for (int i = 0; i < test; i++) {
            out.println("Case " + (i + 1) + ":");
            operate();
        }
        out.close();
    }

    private static void mSort(int[][] arr) {
        int[][] tmpArr = new int[arr.length][2];
        mergeSort(arr, tmpArr, 0, arr.length - 1);
    }

    private static void mergeSort(int[][] arr, int[][] tmpArr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, tmpArr, left, mid);
            mergeSort(arr, tmpArr, mid + 1, right);
            merge(arr, tmpArr, left, mid + 1, right);
        }
    }

    private static void merge(int[][] arr, int[][] tmpArr, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos][0] <= arr[rightPos][0]) tmpArr[tmpPos++] = arr[leftPos++];
            else tmpArr[tmpPos++] = arr[rightPos++];
        }
        while (leftPos <= leftEnd) tmpArr[tmpPos++] = arr[leftPos++];
        while (rightPos <= rightEnd) tmpArr[tmpPos++] = arr[rightPos++];
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tmpArr[rightEnd];
        }
    }

    private static void operate() {
        int length = in.nextInt();
        int[][] inputt = new int[length][2];
        int[] index = new int[length];
        for (int i = 0; i < length; i++) {
            int next = in.nextInt();
            inputt[i][0] = next;
            inputt[i][1] = i;
        }

        int[] input = formula(inputt);
        for (int i = 0; i < length; i++) {
            index[input[i]] = i;
        }

        int[][] result = new int[length][2];
        stack stack = new stack();
        stack.beginMarker.next = stack.endMarker;

        for (int i = 0; i < length; i++) {
            int next = input[i];
            if (stack.size() == 0) {
                stack.push(next);
            } else if (stack.top() > next) {
                stack.push(next);
            } else {
                result[index[stack.top()]][1] = 0;
                int pop = stack.top();
                stack.pop();
                while (stack.size() != 0 && stack.top() < next) {
                    result[index[stack.top()]][1] = index[pop] + 1;
                    pop = stack.top();
                    stack.pop();
                }
                stack.push(next);
            }
            if (i == length - 1) {
                result[index[stack.top()]][1] = 0;
                int pop = stack.top();
                stack.pop();
                while (stack.size() != 0 && pop < stack.top()) {
                    result[index[stack.top()]][1] = index[pop] + 1;
                    pop = stack.top();
                    stack.pop();
                }
            }
        }

        for (int i = 0; i < length; i++) {
            int next = input[length - i - 1];
            if (stack.size() == 0) {
                stack.push(next);
            } else if (stack.top() > next) {
                stack.push(next);
            } else {
                result[index[stack.top()]][0] = 0;
                int pop = stack.top();
                stack.pop();
                while (stack.size() != 0 && stack.top() < next) {
                    result[index[stack.top()]][0] = index[pop] + 1;
                    pop = stack.top();
                    stack.pop();
                }
                stack.push(next);
            }

            if (i == length - 1) {
                result[index[stack.top()]][0] = 0;
                int pop = stack.top();
                stack.pop();
                while (stack.size() != 0 && pop < stack.top()) {
                    result[index[stack.top()]][0] = index[pop] + 1;
                    pop = stack.top();
                    stack.pop();
                }
            }
        }

        for (int i = 0; i < length; i++) {
            out.println(result[i][0] + " " + (result[i][1]));
        }
    }


    private static int[] formula(int[][] input) {
        mSort(input);
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            output[input[i][1]] = i;
        }
        return output;
    }
}
