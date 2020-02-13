package lab1;

import java.util.Scanner;

public class F {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            long num=input.nextLong();
            long sum=0;
            while (num/5!=0){
               sum+=num/5;
               num/=5;
            }
            System.out.println(sum);
        }
    }
}
