package lab3;

import java.util.Scanner;

public class E {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = input.nextInt();
        for (int i = 0; i < test; i++) {
            operate();
        }
    }

    private static void operate() {
        int n = input.nextInt();
        int m = input.nextInt();
        int max = m;
        int slow = cal(n, m);
        int fast = cal(n, slow);
        if (max < slow) max = slow;
        if (max < fast) max = fast;

        while (slow != fast) {
            slow = cal(n, slow);
            if (max < slow) max = slow;
            fast = cal(n, fast);
            if (max < fast) max = fast;
            fast = cal(n, fast);
            if (max < fast) max = fast;
        }
        System.out.println(max);
    }

    private static int cal(int n, int m) {
        String result = String.valueOf((long) m * m);
        if (result.length() > n) return Integer.parseInt(result.substring(0, n));
        else return Integer.parseInt(result);
    }
}
