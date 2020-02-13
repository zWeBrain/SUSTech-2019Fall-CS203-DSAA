package lab1;

import java.util.Scanner;

public class C {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n = input.nextInt();
        long m = input.nextInt();
        if (n>=4) System.out.println(0);
        else {
            switch (n){
                case 0:
                case 1:
                    if (m==1) System.out.println(0);
                    else System.out.println(1);
                    break;
                case 2:
                    if (m<=2) System.out.println(0);
                    else System.out.println(2);
                    break;
                case 3:
                    operate(m);
                    break;
            }
        }
    }
    private static void operate(long m){
        long sum = 1;
        for (int i = 1; i <= 720; i++) {
            sum = (sum % m) * (i%m) % m;
        }
        System.out.println(sum);
    }
}
