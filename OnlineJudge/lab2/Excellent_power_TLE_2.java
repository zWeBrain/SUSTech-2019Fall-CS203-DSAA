package lab2;

import java.util.Scanner;


public class Excellent_power_TLE_2 {
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
        long buff;
        long temp;
        long max = 0L;
        long part_max;
        if (q == 0) {
            System.out.println(sum);
            return;
        }
        if (q<1000) bsort(hps,attacks,q);
        else qSort(hps, attacks);

        if (q > count) q = count;

        for (int i = count - 1; i >= count - q; i--) {
            buff = hps[i] - attacks[i];
            if (buff < 0) buff = 0;
            sum += buff;

            part_max = up(hps[i], p) - attacks[i];
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
        switch (p) {
            case 0:
                return hp;
            case 1:
                return hp * 2;
            case 2:
                return hp * 4;
            case 3:
                return hp * 8;
            case 4:
                return hp * 16;
            case 5:
                return hp * 32;
            case 6:
                return hp * 64;
            case 7:
                return hp * 128;
            case 8:
                return hp * 256;
            case 9:
                return hp * 512;
            case 10:
                return hp * 1024;
            case 11:
                return hp * 2048;
            case 12:
                return hp * 4096;
            case 13:
                return hp * 8192;
            case 14:
                return hp * 16384;
            case 15:
                return hp * 32768;
            case 16:
                return hp * 65536;
            case 17:
                return hp * 131072;
            case 18:
                return hp * 262144;
            case 19:
                return hp * 524288;
            default:
                return hp * 1048576;
        }
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

    public static void qSort(int[] hps, int[] attacks) {
        qSort(hps, attacks, 0, hps.length - 1);
    }

    private static void qSort(int[] hps, int[] attacks, int head, int tail) {
        if (head >= tail)
            return;
        int pivot = partition(hps, attacks, head, tail);
        qSort(hps, attacks, head, pivot - 1);
        qSort(hps, attacks, pivot + 1, tail);
    }
    private static void quickSort(long[] hps, long[] attacks, int low, int high) {
        int i, j;

        if (low > high) {
            return;
        }
        i = low;
        j = high;
        int index = (int) (Math.random() * (high - low)) + low;
        long temp_h = hps[index];
        long temp_a = attacks[index];

        while (i < j) {
            while (temp_h - temp_a <= hps[j] - attacks[j] && i < j) {
                j--;
            }
            while (temp_h - temp_a >= hps[j] - attacks[j] && i < j) {
                i++;
            }
            if (i < j) {
                long t_h = hps[j];
                hps[j] = hps[i];
                hps[i] = t_h;
                long t_a = attacks[j];
                attacks[j] = attacks[i];
                attacks[i] = t_a;
            }

        }
        hps[index] = hps[i];
        hps[i] = temp_h;
        attacks[index] = attacks[i];
        attacks[i] = temp_a;
        quickSort(hps, attacks, low, j - 1);
        quickSort(hps, attacks, j + 1, high);
    }
    private static int partition(int[] hps, int[] attacks, int head, int tail) {
        int pivot_h = hps[head];
        int pivot_a = attacks[head];

        while (head < tail) {
            while (head < tail && hps[tail] - attacks[tail] >= pivot_h - pivot_a) --tail;
            hps[head] = hps[tail];
            attacks[head] = attacks[tail];

            while (head < tail && hps[head] - attacks[head] <= pivot_h - pivot_a) ++head;
            hps[tail] = hps[head];
            attacks[tail] = attacks[head];
        }
        hps[head] = pivot_h;
        attacks[head] = pivot_a;
        return head;
    }

}
