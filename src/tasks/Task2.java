package tasks;

import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static void execute() {
        System.out.println("=== ЗАДАНИЕ 2 ===");
        System.out.println("Поиск минимальных значений в столбцах матрицы");

        Scanner scanner = new Scanner(System.in);

        int rows = 0;
        int cols = 0;

        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("\nВведите количество строк матрицы (от 2 до 10): ");
                rows = scanner.nextInt();
                if (rows < 2 || rows > 10) {
                    System.out.println("Ошибка! Введите число от 2 до 10.");
                    continue;
                }

                System.out.print("Введите количество столбцов матрицы (от 2 до 10): ");
                cols = scanner.nextInt();
                if (cols < 2 || cols > 10) {
                    System.out.println("Ошибка! Введите число от 2 до 10.");
                    continue;
                }

                validInput = true;
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Пожалуйста, введите целое число.");
                scanner.nextLine();
            }
        }

        // Создание и заполнение матрицы
        int[][] matrix = new int[rows][cols];
        Random random = new Random();

        System.out.println("\nСлучайная матрица " + rows + "x" + cols + ":");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(100);
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        //search
        System.out.println("\nНомера строк с минимальными значениями по столбцам:");
        for (int col = 0; col < cols; col++) {
            int minIndex = findMinInColumn(matrix, col);
            System.out.println("Столбец " + col + ": минимальное значение в строке " + minIndex +
                    " (значение: " + matrix[minIndex][col] + ")");
        }

        // arg search
        System.out.print("\nХотите найти минимальное значение в конкретном столбце? (да/нет): ");
        scanner.nextLine();
        String answer = scanner.nextLine().toLowerCase();

        if (answer.equals("да") || answer.equals("yes") || answer.equals("y")) {
            System.out.print("Введите номер столбца (от 0 до " + (cols-1) + "): ");
            int columnToSearch = scanner.nextInt();

            if (columnToSearch >= 0 && columnToSearch < cols) {
                int minIndex = findMinInColumn(matrix, columnToSearch);
                System.out.println("В столбце " + columnToSearch + " минимальное значение " +
                        matrix[minIndex][columnToSearch] + " находится в строке " + minIndex);
            } else {
                System.out.println("Неверный номер столбца!");
            }
        }

        System.out.println();
    }

    private static int findMinInColumn(int[][] matrix, int column) {
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