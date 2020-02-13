package lab2;

import java.util.Scanner;


public class E {
    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        int count = input.nextInt();
        int p = input.nextInt();
        int q = input.nextInt();
        int[] attacks = new int[count];
        int[] hps = new int[count];
        long sum = 0L;

        for (int i = 0; i < count; i++) {
            int hp = input.nextInt();
            int att = input.nextInt();
            sum += att;
            hps[i] = hp;
            attacks[i] = att;
        }

        long max = 0L;

        if (q == 0) {
            System.out.println(sum);
            return;
        } else if (q >= count) {
            q = count;
            //qSort(hps, attacks);
        } else if (q < 10) bsort(hps, attacks, q);
        else Search(hps, attacks, 0, count - 1, count - q);

        int buff;
        long part_max;
        long temp;
        for (int i = count - 1; i >= count - q; i--) {


            buff = hps[i] - attacks[i];
            if (buff < 0) buff = 0;
            sum += buff;

            part_max = up((long) hps[i], p) - attacks[i];
            if (part_max < 0) part_max = 0;
            temp = part_max - buff;

            if (max < temp)
                max = temp;
        }

        for (int i = 0; i < count - q; i++) {

            buff = hps[count - q] - attacks[count - q];
            if (buff < 0) buff = 0;

            part_max = up(hps[i], p) - attacks[i];
            if (part_max < 0) part_max = 0;
            temp = part_max - buff;

            if (max < temp)
                max = temp;
        }

        System.out.println(sum + max);

    }

    private static long up(long hp, int p) {
        long sum = hp;
        for (int i = 0; i < p; i++) {
            sum <<= 1;
        }
        return sum;
    }

    private static void bsort(int[] hps, int[] attacks, int q) {
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < hps.length - 1 - i; j++) {
                if (hps[j] - attacks[j] > hps[j + 1] - attacks[j + 1]) {

                    hps[j] ^= hps[j + 1];
                    hps[j + 1] ^= hps[j];
                    hps[j] ^= hps[j + 1];
                    attacks[j] ^= attacks[j + 1];
                    attacks[j + 1] ^= attacks[j];
                    attacks[j] ^= attacks[j + 1];

                }
            }
        }
    }


    private static int partition(int[] hps, int[] attacks, int head, int tail) {
        int index = (int) (Math.random() * (tail - head)) + head;
        int pivot_h = hps[index];
        int pivot_a = attacks[index];
        int L = 0;
        int R = tail - head;
        int[] arr_a = new int[tail - head + 1];
        int[] arr_h = new int[tail - head + 1];

        for (int i = head; i <= tail; i++) {
            if (i == index) {
                continue;
            }
            if (hps[i] - attacks[i] < pivot_h - pivot_a) {
                arr_a[L] = attacks[i];
                arr_h[L] = hps[i];
                L++;
            } else {
                arr_a[R] = attacks[i];
                arr_h[R] = hps[i];
                R--;
            }
        }
        arr_a[L] = pivot_a;
        arr_h[L] = pivot_h;
        if (tail + 1 - head >= 0) {
            System.arraycopy(arr_a, 0, attacks, head, tail + 1 - head);
            System.arraycopy(arr_h, 0, hps, head, tail + 1 - head);
        }
        return L + head;
    }

    private static void qSort(int[] hps, int[] attacks) {
        qSort(hps, attacks, 0, hps.length - 1);
    }

    private static void qSort(int[] hps, int[] attacks, int head, int tail) {
        if (head >= tail)
            return;
        int pivot = partition(hps, attacks, head, tail);
        qSort(hps, attacks, head, pivot - 1);
        qSort(hps, attacks, pivot + 1, tail);
    }

    private static long Search(int[] hps, int[] attacks, int i, int j, int k) {
        int partition = partition(hps, attacks, i, j);

        if (k == partition - i + 1) return hps[partition] - attacks[partition];
        else if (k < partition - i + 1) {
            return Search(hps, attacks, i, partition - 1, k);
        } else {
            return Search(hps, attacks, partition + 1, j, k - (partition - i + 1));
        }

    }
}
