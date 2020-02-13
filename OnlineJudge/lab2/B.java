package lab2;

import java.util.Scanner;

public class B {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int length = input.nextInt();
        int limit =input.nextInt();
        long[] arr =new long[length];
        for (int i = 0; i < length; i++) {
            arr[i]=input.nextLong();
        }
        System.out.println(Search(arr,0,arr.length-1,limit));
        for (int i = 0; i < length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static int Partition(long a[], int i, int j) {
        long temp = a[i];
        while (i < j) {
            while (i < j && a[j] >= temp) j--;
            if (i < j) a[i] = a[j];
            while (i < j && a[i] < temp) i++;
            if (i < j) a[j] = a[i];
        }
        a[i] = temp;
        return i;
    }

    private static long Search(long arr[], int i, int j, int k) {
        int partition = Partition(arr, i, j);
        if (k == partition - i + 1) return arr[partition];
        else if (k < partition - i + 1) {
            return Search(arr, i, partition - 1, k);
        }
        else {
            return Search(arr, partition + 1, j, k - (partition - i + 1));
        }
    }
}
