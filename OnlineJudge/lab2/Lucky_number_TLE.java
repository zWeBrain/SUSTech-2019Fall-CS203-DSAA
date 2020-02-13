package lab2;

import java.util.Scanner;

public class Lucky_number_TLE {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int length = input.nextInt();
        int limit =input.nextInt();
        long[] arr =new long[length];
        for (int i = 0; i < length; i++) {
            arr[i]=input.nextLong();
        }
        bsort(arr,limit);
        System.out.println(arr[length-limit]);
    }

    public static void bsort(long[] arr, int limit) {
        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                }
            }
        }

    }
}
