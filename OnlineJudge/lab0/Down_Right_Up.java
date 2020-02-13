package lab0;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Down_Right_Up {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int test = Integer.parseInt(input.nextLine());
        for (int i = 0; i < test; i++) {
            String s = input.nextLine().trim();
            if (s.charAt(0) != '(')
                plan_A(s);
            else plan_B(s);

        }
    }

    private static long pow(int a, int b) {
        long sum = 1;
        for (int i = 0; i < b; i++) {
            sum *= a;
        }
        return sum;
    }

    private static void plan_A(String s) {
        long number = Long.parseLong(s);
        long down = 0L, right = 0L;
        String number_4 = Long.toString(number - 1, 4);
        int count = number_4.length() - 1;
        for (int i = 0; i < number_4.length(); i++) {
            switch (number_4.charAt(i)) {
                case '0':
                    break;
                case '1':
                    down += pow(2, count);
                    break;
                case '2':
                    down += pow(2, count);
                    right += pow(2, count);
                    break;
                case '3':
                    right += pow(2, count);
                    break;
            }
            count--;
        }
        System.out.println("(" + (down + 1) + "," + (right + 1) + ")");
    }

    private static void plan_B(String s) {
        long down = 0L, right = 0L;
        Pattern p = Pattern.compile("((?<down>[0-9]*),(?<right>[0-9]*))");
        Matcher m = p.matcher(s);
        if (m.find()) {
            down = Long.parseLong(m.group("down"));
            right = Long.parseLong(m.group("right"));
        }
        String down_2 = Long.toString(down - 1, 2);
        String right_2 = Long.toString(right - 1, 2);
        // System.out.println(down_2+" "+right_2);
        boolean d_bigger_r = down > right;
        int length = Math.max(right_2.length(), down_2.length());
        int little = Math.min(right_2.length(), down_2.length());
        int flag_1 = 0, flag_2 = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (flag_1 < length - little) {
                if (d_bigger_r) {
                    if (down_2.charAt(flag_1) == '1') stringBuffer.append(1);
                    else stringBuffer.append(0);
                } else {
                    if (right_2.charAt(flag_1) == '1') stringBuffer.append(3);
                    else stringBuffer.append(0);
                }
                flag_1++;
            } else {
                if (d_bigger_r) {
                    if (down_2.charAt(flag_1) == '1' && right_2.charAt(flag_2) == '1') stringBuffer.append(2);
                    else if (down_2.charAt(flag_1) == '0' && right_2.charAt(flag_2) == '0') stringBuffer.append(0);
                    else if (down_2.charAt(flag_1) == '1' && right_2.charAt(flag_2) == '0') stringBuffer.append(1);
                    else if (down_2.charAt(flag_1) == '0' && right_2.charAt(flag_2) == '1') stringBuffer.append(3);
                } else {
                    if (down_2.charAt(flag_2) == '1' && right_2.charAt(flag_1) == '1') stringBuffer.append(2);
                    else if (down_2.charAt(flag_2) == '0' && right_2.charAt(flag_1) == '0') stringBuffer.append(0);
                    else if (down_2.charAt(flag_2) == '1' && right_2.charAt(flag_1) == '0') stringBuffer.append(1);
                    else if (down_2.charAt(flag_2) == '0' && right_2.charAt(flag_1) == '1') stringBuffer.append(3);
                }
                flag_1++;
                flag_2++;
            }
        }
        String all = stringBuffer.toString().trim();
        // System.out.println(all);
        long sum = 1L;
        for (int i = 0; i < all.length(); i++) {
            sum += pow(4, all.length() - i - 1) * Integer.parseInt("" + stringBuffer.charAt(i));
        }
        System.out.println(Long.valueOf(sum));
    }
}
