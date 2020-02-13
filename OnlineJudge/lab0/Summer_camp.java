package lab0;

import java.util.Scanner;

public class Summer_camp {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            long number = input.nextLong();
            operate(number);
        }
    }

    private static void operate(long n) {
        if (n == 1) {
            System.out.println("impossible");
            return;
        }
        long min_odd = Long.MAX_VALUE;
        long all_even = 1;
        boolean has_odd = false;
        long count = 0L;
        for (long l = 2; l <= n; l++) {
            while (n % l == 0) {
                if (l % 2 == 0) {
                    all_even *= l;
                } else {
                    if (l < min_odd) {
                        has_odd = true;
                        count++;
                        min_odd = l;
                    }
                }
                n /= l;
                if (n == 1) {
                    if (!has_odd) System.out.println("impossible");
                    else if (all_even == 1) {
                        System.out.println(2);
                    } else {
                        System.out.println(Math.min(all_even * 2, min_odd));
                    }
                }
            }
        }
    }

    public static boolean isPrime(long a) {
        if (a < 2) {
            return true;
        }

        boolean judge = true;

        for (long i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                judge = false;
                break;
            }
        }
        return judge;
    }
}
