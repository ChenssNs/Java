import tasks.Task1;
import tasks.Task2;
import tasks.Task3;
import tasks.Task0;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=======================================");
        System.out.println("     ПРОЕКТ: СБОРНИК ЗАДАНИЙ ПО JAVA    ");
        System.out.println("=======================================");

        do {
            displayMenu();
            System.out.print("\nВыберите задание (0-3) или 10 для выхода: ");

            // Проверка ввода
            while (!scanner.hasNextInt()) {
                System.out.print("Пожалуйста, введите число от 0 до 3: ");
                scanner.next();
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("\n".repeat(50));
                    Task0.execute();
                    break;
                case 1:
                    System.out.println("\n".repeat(50)); // Очистка экрана (условная)
                    Task1.execute();
                    break;
                case 2:
                    System.out.println("\n".repeat(50));
                    Task2.execute();
                    break;
                case 3:
                    System.out.println("\n".repeat(50));
                    Task3.execute();
                    break;
                case 10:
                    System.out.println("\nСпасибо за использование программы!");
                    System.out.println("Выход из программы...");
                    break;
                default:
                    System.out.println("Неверный выбор! Введите число от 0 до 3.\n");
            }

            if (choice != 0 && choice >= 1 && choice <= 3) {
                System.out.print("\nНажмите Enter для возврата в главное меню...");
                scanner.nextLine();
                scanner.nextLine(); //await
            }

        } while (choice != 0);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                ГЛАВНОЕ МЕНЮ                ");
        System.out.println("=".repeat(50));
        System.out.println("0. T.1 🌌 Синус - двойной синус первого числа");
        System.out.println();
        System.out.println("1. T.2 🎯 Массивы - поиск максимальных значений");
        System.out.println();
        System.out.println("2. T.3 📊 Матрицы - работа с двумерными массивами");
        System.out.println();
        System.out.println("3. T.4 🎓 Класс Student - управление студентами");
        System.out.println("=".repeat(50));
    }
}