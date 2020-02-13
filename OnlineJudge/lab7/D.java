package lab7;

import java.io.PrintWriter;
import java.util.Scanner;

public class D {
    static class BinaryHeap {
        private int currentSize;
        private long[] array;


        private BinaryHeap(int capacity) {
            currentSize = 0;
            array = new long[capacity + 1];
        }


        private void percolateDown(int hole) {
            int child;
            long tmp = array[hole];

            for (; hole * 2 <= currentSize; hole = child) {
                child = hole * 2;
                if (child != currentSize &&
                        array[child + 1] > array[child])
                    child++;
                if (array[child] > tmp)
                    array[hole] = array[child];
                else
                    break;
            }
            array[hole] = tmp;
        }

        private void insert(long next) {
            if (currentSize == array.length - 1) {
                int hole = currentSize + 1;
                int min_index = 0;
                long min = next;
                for (int i = 1; i < hole; i++) {
                    if (array[i] < min) {
                        min_index = i;
                        min = array[i];
                    }
                }
                array[min_index] = next;
            } else {
                int hole = ++currentSize;
                for (array[0] = next; next > array[hole / 2]; hole /= 2) {
                    array[hole] = array[hole / 2];
                }
                array[hole] = next;
            }
        }
       /* private void enlargeArray(int newSize) {
            int[] old = array;
            array = new int[newSize];
            System.arraycopy(old, 0, array, 0, old.length);
        }

        private void insert(int next) {
            if (currentSize == array.length - 1)
                enlargeArray(array.length*2+1);
            int hole = ++currentSize;
            for (array[0] = next; next > array[hole / 2]; hole /= 2) {
                array[hole] = array[hole / 2];
            }
            array[hole] = next;
        }*/

        private long deleteMax() {
            if (currentSize == 0) {
                System.out.println("Can't delete!");
                return -1;
            }
            long maxItem = getMax();
            array[1] = array[currentSize--];
            percolateDown(1);
            return maxItem;
        }


        private long getMax() {
            return array[1];
        }

        long getK(int K) {
            long[] collections = new long[K - 1];
            for (int i = 0; i < K - 1; i++) {
                collections[i] = deleteMax();
                //    System.out.println(getMax());
            }

            long result = getMax();
            for (int i = 0; i < K - 1; i++) {
                insert(collections[i]);
            }
            return result;
        }
    }

    private static Scanner in = new Scanner(System.in);
    private static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) {
        operate();
    }

    private static void operate() {
        int t = in.nextInt();
        int k = in.nextInt();
        int s = in.nextInt();
        int times = t / 100;
        BinaryHeap bh = new BinaryHeap(k);
        int i = 1;
        long next;
        long last_ans = s;
        while (times-- > 0) {
            int start = i;
            for (; i < start + 100; i++) {
                next = sum(i + last_ans) + i + last_ans;
                //     System.out.println(next);
                bh.insert(next);

            }
            if (k < i) last_ans = bh.getK(k);
            else last_ans = bh.getK(i-1);
            out.print(last_ans + " ");
            out.flush();
        }
        out.close();
    }

    private static long sum(long num) {
        if (num < 10)
            return num;
        return num % 10 + sum(num / 10);
    }
}
