package lab2;

import java.util.Scanner;

class soldier {
    private long hp;
    private long attack;

    long getdiv() {
        return Math.max(hp - attack, 0);
    }

    long up_div(int p) {
        long hpp = hp;
        for (int i = 0; i < p; i++) {
            hpp *= 2;
        }
        return hpp - attack;
    }

    soldier(long hp, long attack) {
        this.hp = hp;
        this.attack = attack;
    }
}

public class Excellent_power_TLE {
    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        int count = input.nextInt();
        int p = input.nextInt();
        int q = input.nextInt();
        soldier[] soldiers = new soldier[count];
        long sum = 0L;

        for (int i = 0; i < count; i++) {
            long hp = input.nextLong();
            long att = input.nextLong();
            sum += att;
            soldiers[i] = new soldier(hp, att);
        }

        bsort(soldiers, q);
        long buff;
        long temp;
        long max = 0L;
        if (q>count) q=count;
        for (int i = 1; i <= q; i++) {
            buff = soldiers[count - i].getdiv();
            sum += buff;
            temp = soldiers[count - i].up_div(p) - buff;
            if (max < temp)
                max = temp;
        }

        for (int i = 0; i < count - q; i++) {
            temp = soldiers[i].up_div(p) - soldiers[count - q].getdiv();
            if (max < temp)
                max = temp;
        }
        System.out.println(sum + max);
    }

    private static void bsort(soldier[] arr, int q) {
        for (int i = 0; i < q; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getdiv() > arr[j + 1].getdiv()) {
                    soldier temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
