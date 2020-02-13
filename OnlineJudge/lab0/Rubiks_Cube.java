package lab0;

import java.util.Scanner;


public class Rubiks_Cube {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		int test = input.nextInt();
		for (int i = 0; i < test; i++) {
			System.out.println(createMatrix(input)[1][1]);
		}
	}

	private static char[][] createMatrix(Scanner input) {
		char[][] matrix = new char[3][3];
		for (int row = 0; row < matrix.length; row++) {
			for (int column = 0; column < matrix[row].length; column++) {
				matrix[row][column] = input.next().charAt(0);
			}
		}
		return matrix;
	}
}

