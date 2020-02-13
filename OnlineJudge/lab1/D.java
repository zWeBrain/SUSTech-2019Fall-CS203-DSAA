package lab1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;


public class D {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Long> post = new ArrayList<>();
    private static ArrayList<Long> negt = new ArrayList<>();
    private static int count;
    private static int zeros;

    public static void main(String[] args) {
        int length = input.nextInt();
        long target = input.nextLong();
        operate(length);
        Long[] arr_negt = new Long[negt.size()];
        negt.toArray(arr_negt);
        Long[] arr_post = new Long[post.size()];
        post.toArray(arr_post);
        /*System.out.println(post);
        System.out.println(negt);*/
        if (target > 0) {
            System.out.println(num_same(arr_post, target)
                    + num_same(arr_negt, target));
        } else if (target < 0) {
            System.out.println(num_diff(arr_post, arr_negt, target));
        } else {
            if (zeros == 0) System.out.println(0);
            else if (zeros == 1) System.out.println(count);
            else System.out.println(count + 1);
        }
    }

    private static void operate(int length) {

        Long prev = 11811721L;
        for (int i = 0; i < length; i++) {
            Long num = input.nextLong();
            if (i == 0 || !num.equals(prev)) {
                if (num != 0) count++;
            }
            if (num > 0) post.add(num);
            else if (num < 0) negt.add(num);
            else zeros++;
            prev = num;
        }
        post.sort(Long::compareTo);
        negt.sort(Comparator.reverseOrder());
    }

    private static int num_same(Long[] post, long target) {
        int sum = 0;
        int flag_head = 0;
        if (post.length == 0) return 0;
        if (post[post.length - 1] * post[post.length - 1] < target) return 0;
        for (int i = 0; i < post.length; i++) {
            if (post[i] * post[i] >= target) {
                flag_head = i;
                break;
            } else if (i == 0 || !post[i].equals(post[i - 1])) {
                long mod = (target % post[i]);
                if (mod == 0) {
                    long frag = (target / post[i]);

                    sum += search(post, frag, i + 1, post.length - 1);
                }
            }
        }
        if (flag_head + 1 < post.length &&
                post[flag_head].equals(post[flag_head + 1])
                && post[flag_head] * post[flag_head + 1] == target) {
            sum++;
        }

        return sum;
    }

    private static int num_diff(Long[] post, Long[] negt, long target) {
        if (post.length == 0 || negt.length == 0) return 0;
        int sum = 0;
        for (int i = 0; i < post.length; i++) {
            long t = (target % post[i]);
            if (t == 0 && (i == 0 || !post[i].equals(post[i - 1]))) {
                sum += search(negt, target / (long) post[i], 0, negt.length - 1);
            }
        }
        return sum;
    }

    private static int search(Long[] other, long target, int head, int tail) {
        int mid;
        if (head < 0 || tail >= other.length) {
            return 0;
        }
        if (other[head] == target || other[tail] == target) {
            return 1;
        }

        while (head <= tail) {
            mid = (head + tail) / 2;
            if (other[head] == target || other[tail] == target) {

                return 1;

            }

            if (other[mid] == target) {

                return 1;

            } else if ((other[mid]<0)?(other[mid] > target):(other[mid] < target)) {
                head = mid;
            } else {
                tail = mid;
            }
            if (head == tail) return 0;
            if ((head + 1 == tail) && other[head] != target && other[tail] != target) return 0;
        }
        return 0;
    }

}
