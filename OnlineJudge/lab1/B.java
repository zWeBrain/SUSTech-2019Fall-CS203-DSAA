package lab1;

import java.util.Scanner;

public class B {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int days = input.nextInt();
        int kinds = input.nextInt();
        long[] money = new long[days];
        long[] price = new long[kinds];
        read(days, money);
        read(kinds, price);
        for (int i = 0; i < days; i++) {
            long today = money[i];
            long result = search(price, today, 0, price.length - 1);
            if (result == -2L) System.out.println("Meow");
            else System.out.println(result);
        }
    }

    private static void read(int count, long[] arr) {
        for (int i = 0; i < count; i++) {
            arr[i] = input.nextLong();
        }
    }

    private static long search(long[] arrs, long num, int head, int tail) {
        if (num == arrs[head]||num==arrs[tail]) return -2L;
        if (num < arrs[head]) return num;
        if (num > arrs[tail]) return num - arrs[tail];

        int mid = (head + tail) / 2;
        if (head==tail||mid==head) {
            if(arrs[head]==num||arrs[mid]==num||arrs[tail]==num) return -2L;
            else if (arrs[tail]>num) return num - arrs[head];
            else return num-arrs[tail];
        }


        if (arrs[mid] < num) {
            return search(arrs, num, mid, tail);
        } else if (arrs[mid] > num) {
            return search(arrs, num, head, mid);
        } else {
            return -2L;
        }
    }
}