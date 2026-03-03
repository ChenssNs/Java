package tasks;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    private static final int N = 5; // константа N

    public static void execute() {
        System.out.println("=== ЗАДАНИЕ 1 ===");
        System.out.println("Поиск " + N + " максимальных значений в массиве");

        Scanner scanner = new Scanner(System.in);

        int arraySize = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("\nВведите размер массива (от 10 до 50): ");
                arraySize = scanner.nextInt();

                if (arraySize < 10 || arraySize > 50) {
                    System.out.println("Ошибка! Введите число от 10 до 50.");
                } else if (arraySize < N) {
                    System.out.println("Ошибка! Размер массива должен быть не меньше " + N);
                } else {
                    validInput = true;
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода! Пожалуйста, введите целое число.");
                scanner.nextLine();
            }
        }

        int[] array = new int[arraySize];
        Random random = new Random();

        System.out.println("\nИсходный массив:");
        for (int i = 0; i < arraySize; i++) {
            array[i] = random.nextInt(1000);
            System.out.printf("%4d", array[i]);
            if ((i + 1) % 10 == 0) System.out.println();
        }

        int[] maxValues = getNMaxValues(array, N);

        System.out.println("\n\n" + N + " максимальных значений:");
        for (int i = 0; i < maxValues.length; i++) {
            System.out.print(maxValues[i]);
            if (i < maxValues.length - 1) System.out.print(", ");
        }

        System.out.println("\n");
    }

    private static int[] getNMaxValues(int[] array, int n) {
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);
        int[] result = new int[Math.min(n, sortedArray.length)];

        for (int i = 0; i < result.length; i++) {
            result[i] = sortedArray[sortedArray.length - 1 - i];
        }

        return result;
    }

    private static double getAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    private static int getMinValue(int[] array) {
        int min = array[0];
        for (int value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    private static int countEvenNumbers(int[] array) {
        int count = 0;
        for (int value : array) {
            if (value % 2 == 0) count++;
        }
        return count;
    }
}