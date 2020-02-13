package lab0;

import java.util.Scanner;

public class Magical_Number {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            long number = input.nextLong();
            operate(number);
        }
    }

    public static void operate(long number) {

        // 9 9 90 90 900 900 9000 9000 90000 90000
        // 10 10 100 100 1000 1000
        // 3220: 1**1 2**2 3003 3113 3223x
        // 32221: 1***1 2***2 30*03 31*13 32123 32*23x
        int length = String.valueOf(number).length();
        double sum = ((length - 1) % 2 == 0) ?
                10 * Math.pow(10, length / 2 - 1) * 2 - 1 :
                10 * Math.pow(10, length / 2 - 1) * 2 - 1 - Math.pow(10, length / 2 - 1) * 9;
        sum += count(number, length, length, 0, true);
        if (isPalindromic(number)) sum++;
        System.out.println((long) sum);
    }

    private static boolean isPalindromic(long n) {
        String temp = String.valueOf(n);
        StringBuilder stringBuffer = new StringBuilder(temp.substring(0, (temp.length() + 1) / 2));
        String r = stringBuffer.reverse().toString();
        r = r.substring(r.length() * 2 - temp.length());
        return n - Long.parseLong(stringBuffer.reverse().append(r).toString()) >= 0;
    }

    public static long count(long number, int length, int flag, long sum, boolean first) {
        int first_num = (int) (number / Math.pow(10, length - 1));
        number -= first_num * Math.pow(10, length - 1);
        sum += (first)
                ? (first_num - 1) * count_2(flag - 2)
                : first_num * count_2(flag - 2);
        length -= 1;
        flag -= 2;
        if (flag > 0)
            return count(number, length, flag, sum, false);
        else return sum;
    }

    public static long count_2(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        double sum = Math.pow(10, (n + 1) / 2);
        return (long) sum;
    }

}


