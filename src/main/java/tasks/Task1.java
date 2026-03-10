package tasks;

import java.util.Arrays;
import java.util.Random;

public class Task1 {

    public static int[] generateArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    public static int[] getNMaxValues(int[] array, int n) {
        int[] sortedArray = array.clone();
        Arrays.sort(sortedArray);

        int[] result = new int[Math.min(n, sortedArray.length)];
        for (int i = 0; i < result.length; i++) {
            result[i] = sortedArray[sortedArray.length - 1 - i];
        }
        return result;
    }

    public static double getAverage(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return (double) sum / array.length;
    }

    public static int getMinValue(int[] array) {
        int min = array[0];
        for (int value : array) {
            if (value < min) min = value;
        }
        return min;
    }

    public static int countEvenNumbers(int[] array) {
        int count = 0;
        for (int value : array) {
            if (value % 2 == 0) count++;
        }
        return count;
    }
}