package tasks;

import java.util.Random;

public class Task2 {

    public static int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
            }
        }

        return matrix;
    }

    public static int findMinInColumn(int[][] matrix, int column) {
        int minIndex = 0;
        int minValue = matrix[0][column];

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][column] < minValue) {
                minValue = matrix[i][column];
                minIndex = i;
            }
        }

        return minIndex;
    }
}