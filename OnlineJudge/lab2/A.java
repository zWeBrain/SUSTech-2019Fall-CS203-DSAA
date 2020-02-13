package lab2;

import java.util.Scanner;

public class A {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        int length = input.nextInt();
        int[] numbers = new int[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = input.nextInt();
        }
        int t;
        int limit=(length<4)?length:4;
        for (int i = 0; i < limit; i++) {
            for (int j = i + 1; j < length; j++) {
                if (numbers[j - 1] > numbers[j]) {
                    t = numbers[j];
                    numbers[j] = numbers[j - 1];
                    numbers[j - 1] = t;
                }
            }
        }
        if (numbers[length-2]==numbers[length-3]||
                ((length>=4)&&(numbers[length-3]==numbers[length-4])))
            System.out.println("wa");
        else System.out.println(numbers[length-3]);
    }

}
