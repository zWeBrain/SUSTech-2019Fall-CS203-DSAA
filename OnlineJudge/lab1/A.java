package lab1;

import java.util.Scanner;

public class A {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            long n=input.nextLong();
            long sum=(n*(n+1)*(2*n+1)/6+n*(n+1)/2)/2;
            System.out.println(sum);
        }
    }
}
