package lab2;

import java.util.Scanner;

public class D {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int length = input.nextInt();
        long[] arr = new long[length];
        for (int i = 0; i < length; i++) {
            arr[i] = input.nextLong();
        }
        System.out.println(mSort(arr));
    }

    private static long mSort(long[] arr) {
        long[] tmpArr = new long[arr.length];
        return mergeSort(arr, tmpArr, 0, arr.length - 1);
    }

    private static long mergeSort(long[] arr, long[] tmpArr, int left, int right) {
        long count = 0L;
        if (left < right) {
            int mid = (left + right) >> 1;
            count += mergeSort(arr, tmpArr, left, mid);
            count += mergeSort(arr, tmpArr, mid + 1, right);
            count += merge(arr, tmpArr, left, mid + 1, right);
        }
        return count;
    }

    private static long merge(long[] arr, long[] tmpArr, int leftPos, int rightPos, int rightEnd) {
        long count = 0L;
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos] <= arr[rightPos]) tmpArr[tmpPos++] = arr[leftPos++];
            else {
                int length = leftEnd - leftPos + 1;
                for (int i = 0; i < length; i++) {
                    count += (arr[leftPos + i] + arr[rightPos]);
                }
                tmpArr[tmpPos++] = arr[rightPos++];
            }
        }
        while (leftPos <= leftEnd) tmpArr[tmpPos++] = arr[leftPos++];
        while (rightPos <= rightEnd) tmpArr[tmpPos++] = arr[rightPos++];
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tmpArr[rightEnd];
        }
        return count;
    }
}
