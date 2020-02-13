package lab4;

import java.util.Scanner;

public class A {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int max_size = input.nextInt();
        int[] quene = new int[max_size + 10];
        int head = 0;
        int tail = 0;
        for (int i = 0; i < max_size; i++) {
            char c = input.next().charAt(0);
            switch (c) {
                case 'E': {
                    quene[tail] = input.nextInt();
                    tail++;
                    break;
                }
                case 'D': {
                    head++;
                    break;
                }
                case 'A': {
                    System.out.println(quene[head]);
                    break;
                }
            }
        }
        for (int j = head; j < tail; j++) {
            System.out.print(quene[j]+" ");
        }
    }
}
