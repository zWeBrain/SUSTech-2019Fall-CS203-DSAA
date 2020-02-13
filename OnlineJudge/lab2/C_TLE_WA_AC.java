package lab2;

import java.util.Scanner;

public class C_TLE_WA_AC {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int length = input.nextInt();
        int target = input.nextInt();
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = input.nextLong();
        }
        qsort(arr);
        System.out.println(search(arr, 0, arr.length - 1, target));
    }

    private static long search(long[] arr, int head, int tail, long target) {
        long sum = 0;
        while (head + 1 < tail) {
            long ta = target - arr[head] - arr[tail];
            if (ta >= arr[head + 1] && ta <= arr[tail - 1]) {
                sum += bs(arr, ta, head + 1, tail - 1);
            }
            head++;
            if (head + 1 >= tail) {
                head = 0;
                tail--;
            }
        }
        return sum;

    }

    private static int bs(long[] arr, long target, int head, int tail) {
        //     System.out.println("   " + target);
        int end = tail;
        while (head < tail) {
            int mid = (head + tail) >> 1;
            if (arr[mid] < target)
                head = mid + 1;
            else
                tail = mid;
        }
        if (arr[head] == target) {
            int flag = head;
            // if (head + 2 == arr.length || arr[head + 1] > target) return 1;
            int step = end - head;
            while (arr[head + step] > target) {
                step >>= 1;
            }
            head += step;
            while (step > 0) {
                while (head + step <= end && arr[head + step] == target) {
                    head += step;
                }
                step >>= 1;
            }
            //  System.out.println(head - flag + 1);
            return head - flag + 1;
        }
       /* if (arr[head] == target) {
            int sum = 0;
            while (sum + head <= end && arr[head + sum] == target) {
                sum++;
            }
            return sum;
        } */else return 0;
    }

    private static void qsort(long[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(long[] arr, int head, int tail) {
        if (head >= tail)
            return;
        int pivot = partition(arr, head, tail);
        qsort(arr, head, pivot - 1);
        qsort(arr, pivot + 1, tail);
    }

    private static int partition(long[] arr, int head, int tail) {
        long pivot = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= pivot) --tail;
            arr[head] = arr[tail];
            while (head < tail && arr[head] <= pivot) ++head;
            arr[tail] = arr[head];
        }
        arr[head] = pivot;
        return head;
    }
}
