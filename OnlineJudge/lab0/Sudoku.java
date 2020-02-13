package lab0;

import java.util.*;

public class Sudoku {
    private static Scanner input = new Scanner(System.in);
    private static int[][] matrix;
    private static int need;

    public static void main(String[] args) {
        matrix = createMatrix(input);
        while (need != 0) {
            int count=need;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (matrix[i][j] == 0) {
                        int[] x = matrix[i];
                        if (isSpecial(x)) {
                            matrix[i][j] = update(x);
                            need--;
                            break;
                        }
                        int[] y = new int[9];
                        for (int k = 0; k < 9; k++) {
                            y[k] = matrix[k][j];
                        }
                        if (isSpecial(y)) {
                            matrix[i][j] = update(y);
                            need--;
                            break;
                        }
                        int[] z = getZ(getZone(i, j), matrix);
                        if (isSpecial(z)) {
                            matrix[i][j] = update(z);
                            need--;
                            break;
                        }
                        //matrix[i][j]=critical(x,y,z);
                    }
                }
            }
            if (count==need)  {
                System.out.println("The test data is incorrect!");
                return;
            }

        }
        output(matrix);
    }
    private static int critical(int[] x,int[] y,int[] z){
        int[] temp=new int[10];
        int target=0;
        int times=0;
        for (int i = 0; i <9 ; i++) {
            temp[x[i]]++;
        }
        for (int i = 0; i <9 ; i++) {
            temp[y[i]]++;
        }
        for (int i = 0; i <9 ; i++) {
            temp[z[i]]++;
        }
        for (int i = 0; i < 10; i++) {
            if (temp[i]==0) {
                target=i;
                times++;
            }
        }
        if (times==1) {
            need--;
            return target;
        }
        else return 0;
    }
    private static int[][] createMatrix(Scanner input) {
        int[][] matrix = new int[9][9];
        for (int i = 0; i < 9; ) {
            boolean well = true;
            List list = Arrays.asList(input.nextLine().split(" "));
            if (list.size() > 1) i++;
            else well = false;
            if (well) {
                for (Object o : list) {
                    if (o.equals("x")) need++;
                }
                Collections.replaceAll(list, "x", "0");
                matrix[i - 1][0] = Integer.parseInt(list.get(0).toString().trim());
                matrix[i - 1][1] = Integer.parseInt(list.get(1).toString().trim());
                matrix[i - 1][2] = Integer.parseInt(list.get(2).toString().trim());
                matrix[i - 1][3] = Integer.parseInt(list.get(4).toString().trim());
                matrix[i - 1][4] = Integer.parseInt(list.get(5).toString().trim());
                matrix[i - 1][5] = Integer.parseInt(list.get(6).toString().trim());
                matrix[i - 1][6] = Integer.parseInt(list.get(8).toString().trim());
                matrix[i - 1][7] = Integer.parseInt(list.get(9).toString().trim());
                matrix[i - 1][8] = Integer.parseInt(list.get(10).toString().trim());
            }
        }
        return matrix;
    }

    public static void output(int[][] matrix) {
        for (int i = 0; i < 9; i++) {
            System.out.printf("%d %d %d | %d %d %d | %d %d %d |",
                    matrix[i][0], matrix[i][1], matrix[i][2],
                    matrix[i][3], matrix[i][4], matrix[i][5],
                    matrix[i][6], matrix[i][7], matrix[i][8]);
            if (i!=8) System.out.println();
            if (i==2||i==5) System.out.println();
        }
    }

    public static int update(int[] arr) {
        int sum = Arrays.stream(arr).reduce(0, (accumulator, element) -> accumulator + element);
        return 45 - sum;
    }

    public static boolean isSpecial(int[] arr) {
        int times = 0;
        for (int value : arr) {
            if (value == 0) times++;
        }
        return times == 1;
    }

    private static int[] getZ(int zone, int[][] sudoku) {
        int x = (zone - 1) / 3 * 3;
        int y = (zone - 1) % 3 * 3;
        int[] temp = new int[9];
        int i = 0;
        for (int j = x; j < x + 3; j++) {
            for (int k = y; k < y + 3; k++) {
                temp[i] = sudoku[j][k];
                i++;
            }
        }
        return temp;
    }

    private static int getZone(int i, int j) {
        if (i < 3) {
            if (j < 3) return 1;
            else if (j < 6) return 2;
            else if (j < 9) return 3;
            else return 0;
        } else if (i < 6) {
            if (j < 3) return 4;
            else if (j < 6) return 5;
            else if (j < 9) return 6;
            else return 0;
        } else if (i < 9) {
            if (j < 3) return 7;
            else if (j < 6) return 8;
            else if (j < 9) return 9;
            else return 0;
        } else return 0;
    }
}
