package tasks;

public class Task0 {
    public static void execute() {
        System.out.println("=== ЗАДАНИЕ 4  ===");

        java.util.Scanner scanner = new java.util.Scanner(System.in);

        try {
            System.out.print("Введите число a: ");
            double a = scanner.nextDouble();

            System.out.print("Введите число b: ");
            double b = scanner.nextDouble();

            System.out.println("\nВведенные значения:");
            System.out.println("a = " + a);
            System.out.println("b = " + b);
            System.out.println("Сумма a + b = " + (a + b));
            System.out.println();

            if (a + b > 100) {
                double result = 2 * Math.sin(a);
                System.out.println("✅ Условие выполнено");
                System.out.printf("🎯 РЕЗУЛЬТАТ: 2 * sin(%.2f) = %.6f\n", a, result);
            } else {
                System.out.println("❌ Условие НЕ выполнено (сумма ≤ 100)");
                System.out.println();
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("❌ Ошибка: Введите корректные числа!");
        } finally {
            System.out.println();
        }
    }
}
