package tasks;

import domain.Student;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Task3 {
    private static List<Student> students;
    private static final String DEFAULT_FILENAME = "students.json";

    public static void execute() {
        System.out.println("=== ЗАДАНИЕ 3 ===");
        System.out.println("Успеваемость студентов ВУЗА");
        System.out.println("Сортировка по баллу ЕГЭ");
        System.out.println("Работа с JSON-файлами: сохранение и загрузка данных");

        Scanner scanner = new Scanner(System.in);

        // Инициализация списка студентов
        students = new ArrayList<>();
        students.addAll(List.of(createStudentsArray()));

        // Главное меню
        int choice;
        do {
            System.out.println("\n" + "=".repeat(60));
            System.out.println("           УПРАВЛЕНИЕ СТУДЕНТАМИ           ");
            System.out.println("=".repeat(60));
            System.out.println("1. 📋 Показать всех студентов");
            System.out.println("2. ➕ Создать нового студента");
            System.out.println("3. 📊 Сортировка по баллу ЕГЭ");
            System.out.println("4. 🔍 Поиск отличников");
            System.out.println("5. 💾 Сохранить в JSON-файл");
            System.out.println("6. 📂 Загрузить из JSON-файла");
            System.out.println("7. 🗑️ Очистить список студентов");
            System.out.println("8. ℹ️ Информация о JSON-файле");
            System.out.println("0. ↩️ Вернуться в главное меню");
            System.out.println("=".repeat(60));
            System.out.print("Выберите действие: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера

            switch (choice) {
                case 1:
                    showAllStudents();
                    break;
                case 2:
                    createNewStudent(scanner);
                    break;
                case 3:
                    sortByEgeScore();
                    break;
                case 4:
                    findExcellentStudents();
                    break;
                case 5:
                    saveToJsonFile(scanner);
                    break;
                case 6:
                    loadFromJsonFile(scanner);
                    break;
                case 7:
                    clearStudents(scanner);
                    break;
                case 8:
                    showFileInfo();
                    break;
                case 0:
                    System.out.println("Возврат в главное меню...");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }

            if (choice != 0) {
                System.out.print("\nНажмите Enter для продолжения...");
                scanner.nextLine();
            }

        } while (choice != 0);
    }

    // Метод для создания массива студентов
    private static Student[] createStudentsArray() {
        Student[] studentsArray = new Student[5];

        studentsArray[0] = new Student(
                "2023001",
                "Иванов",
                "Иван",
                "ПИ-21",
                "Программная инженерия",
                "Объектно-ориентированное программирование",
                9,
                "Сидоров А.П.",
                87
        );

        studentsArray[1] = new Student(
                "2023002",
                "Петрова",
                "Мария",
                "ПИ-22",
                "Программная инженерия",
                "Базы данных",
                8,
                "Кузнецова Е.В.",
                92
        );

        studentsArray[2] = new Student(
                "2023003",
                "Сидоров",
                "Алексей",
                "ИВТ-21",
                "Информатика и вычислительная техника",
                "Веб-программирование",
                10,
                "Васильева Т.И.",
                78
        );

        studentsArray[3] = new Student(
                "2023004",
                "Алексеев",
                "Дмитрий",
                "ПИ-21",
                "Программная инженерия",
                "Математический анализ",
                7,
                "Петров В.И.",
                85
        );

        studentsArray[4] = new Student(
                "2023005",
                "Васильева",
                "Елена",
                "ИВТ-22",
                "Информатика и вычислительная техника",
                "Физика",
                9,
                "Смирнов К.Л.",
                95
        );

        return studentsArray;
    }

    // Метод для отображения всех студентов
    private static void showAllStudents() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        СПИСОК ВСЕХ СТУДЕНТОВ        ");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("📭 Список студентов пуст.");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println("\n" + students.get(i).toString());
        }

        System.out.println("\nВсего студентов: " + students.size());
    }

    // Метод для создания нового студента
    private static void createNewStudent(Scanner scanner) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       СОЗДАНИЕ НОВОГО СТУДЕНТА       ");
        System.out.println("=".repeat(60));

        System.out.print("Введите номер зачетной книжки: ");
        String id = scanner.nextLine();

        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();

        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите группу: ");
        String group = scanner.nextLine();

        System.out.print("Введите кафедру: ");
        String department = scanner.nextLine();

        System.out.print("Введите балл ЕГЭ (0-100): ");
        int egeScore = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        // Создаем студента с базовыми данными
        Student newStudent = new Student(id, lastName, firstName, group, department);
        newStudent.setEgeScore(egeScore);

        // Дополнительная информация
        System.out.print("\nХотите добавить информацию об успеваемости? (да/нет): ");
        String addInfo = scanner.nextLine().toLowerCase();

        if (addInfo.equals("да") || addInfo.equals("yes") || addInfo.equals("y")) {
            System.out.print("Введите название дисциплины: ");
            String discipline = scanner.nextLine();
            newStudent.setDiscipline(discipline);

            System.out.print("Введите оценку (0-10): ");
            int mark = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            newStudent.setMark(mark);

            System.out.print("Введите фамилию преподавателя: ");
            String teacher = scanner.nextLine();
            newStudent.setTeacherName(teacher);
        }

        // Добавляем нового студента в список
        students.add(newStudent);

        System.out.println("\n✅ Новый студент успешно добавлен!");
        System.out.println("Теперь всего студентов: " + students.size());
    }

    // Сортировка по баллу ЕГЭ
    private static void sortByEgeScore() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   СОРТИРОВКА ПО БАЛЛУ ЕГЭ   ");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("📭 Список студентов пуст. Сортировка невозможна.");
            return;
        }

        // Сортируем студентов по баллу ЕГЭ (по убыванию)
        students.sort(new Student.EgeScoreComparator());

        // Выводим отсортированный список
        System.out.println("№  Фамилия Имя         Группа  ЕГЭ  Оценка");
        System.out.println("-".repeat(60));

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.printf("%-2d %-10s %-10s %-6s %-4d %-6d\n",
                    i + 1, s.getLastName(), s.getFirstName(),
                    s.getGroup(), s.getEgeScore(), s.getMark());
        }

        // Выводим статистику
        System.out.println("\n📊 Статистика по баллам ЕГЭ:");
        System.out.println("Средний балл: " + String.format("%.1f", calculateAverageEge()));
        System.out.println("Максимальный балл: " + students.get(0).getEgeScore());
        System.out.println("Минимальный балл: " + students.get(students.size() - 1).getEgeScore());
    }

    // Метод для расчета среднего балла ЕГЭ
    private static double calculateAverageEge() {
        if (students.isEmpty()) return 0;
        int total = 0;
        for (Student student : students) {
            total += student.getEgeScore();
        }
        return (double) total / students.size();
    }

    // Поиск отличников
    private static void findExcellentStudents() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           ОТЛИЧНИКИ           ");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("📭 Список студентов пуст.");
            return;
        }

        boolean found = false;
        for (Student student : students) {
            if (student.getMark() >= 9) {
                System.out.printf("• %s %s | Группа: %s | Оценка: %d | ЕГЭ: %d\n",
                        student.getLastName(), student.getFirstName(),
                        student.getGroup(), student.getMark(), student.getEgeScore());
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ Отличников не найдено.");
        }
    }

    // Сохранение в JSON-файл
    private static void saveToJsonFile(Scanner scanner) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       СОХРАНЕНИЕ В JSON-ФАЙЛ       ");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("📭 Список студентов пуст. Нечего сохранять.");
            return;
        }

        System.out.print("Введите имя файла (по умолчанию " + DEFAULT_FILENAME + "): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
        }

        // Добавляем расширение .json, если его нет
        if (!filename.endsWith(".json")) {
            filename += ".json";
        }

        try {
            // Создаем JSON-массив из всех студентов
            JSONArray jsonArray = new JSONArray();
            for (Student student : students) {
                jsonArray.put(student.toJSON());
            }

            // Создаем корневой JSON-объект с метаданными
            JSONObject root = new JSONObject();
            root.put("students", jsonArray);
            root.put("count", students.size());
            root.put("date", new java.util.Date().toString());
            root.put("version", "1.0");

            // Записываем в файл с красивым форматированием
            try (FileWriter file = new FileWriter(filename)) {
                file.write(root.toString(4)); // 4 пробела для отступов
                System.out.println("✅ Данные успешно сохранены в файл: " + filename);
                System.out.println("   Количество сохраненных студентов: " + students.size());
                System.out.println("   Размер файла: " + new File(filename).length() + " байт");
            }

        } catch (IOException e) {
            System.out.println("❌ Ошибка при сохранении файла: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Непредвиденная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Загрузка из JSON-файла
    private static void loadFromJsonFile(Scanner scanner) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       ЗАГРУЗКА ИЗ JSON-ФАЙЛА       ");
        System.out.println("=".repeat(60));

        System.out.print("Введите имя файла (по умолчанию " + DEFAULT_FILENAME + "): ");
        String filename = scanner.nextLine().trim();
        if (filename.isEmpty()) {
            filename = DEFAULT_FILENAME;
        }

        // Добавляем расширение .json, если его нет
        if (!filename.endsWith(".json")) {
            filename += ".json";
        }

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("❌ Файл не найден: " + filename);
            return;
        }

        try {
            // Читаем JSON из файла
            JSONObject root = new JSONObject(new JSONTokener(new FileReader(file)));

            // Получаем массив студентов
            JSONArray jsonArray = root.getJSONArray("students");

            // Очищаем текущий список и загружаем новые данные
            students.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject studentJson = jsonArray.getJSONObject(i);
                Student student = new Student(studentJson);
                students.add(student);
            }

            System.out.println("✅ Данные успешно загружены из файла: " + filename);
            System.out.println("   Загружено студентов: " + students.size());
            System.out.println("   Дата сохранения: " + root.optString("date", "неизвестно"));

            // Показываем первых несколько студентов
            System.out.println("\n📋 Первые 3 загруженных студента:");
            for (int i = 0; i < Math.min(3, students.size()); i++) {
                System.out.println("   " + (i+1) + ". " + students.get(i).toShortString());
            }

        } catch (FileNotFoundException e) {
            System.out.println("❌ Файл не найден: " + filename);
        } catch (Exception e) {
            System.out.println("❌ Ошибка при загрузке файла: " + e.getMessage());
            System.out.println("   Проверьте, что файл имеет правильный JSON-формат.");
        }
    }

    // Очистка списка студентов
    private static void clearStudents(Scanner scanner) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("         ОЧИСТКА СПИСКА СТУДЕНТОВ         ");
        System.out.println("=".repeat(60));

        if (students.isEmpty()) {
            System.out.println("📭 Список студентов уже пуст.");
            return;
        }

        System.out.print("Вы уверены, что хотите удалить всех студентов? (да/нет): ");
        String confirm = scanner.nextLine().toLowerCase();

        if (confirm.equals("да") || confirm.equals("yes") || confirm.equals("y")) {
            students.clear();
            System.out.println("✅ Список студентов очищен.");
        } else {
            System.out.println("❌ Операция отменена.");
        }
    }

    // Информация о JSON-файле
    private static void showFileInfo() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       ИНФОРМАЦИЯ О JSON-ФАЙЛЕ       ");
        System.out.println("=".repeat(60));

        File file = new File(DEFAULT_FILENAME);

        if (file.exists()) {
            System.out.println("📁 Имя файла: " + DEFAULT_FILENAME);
            System.out.println("📊 Размер: " + file.length() + " байт");
            System.out.println("📅 Последнее изменение: " + new java.util.Date(file.lastModified()));

            // Пытаемся прочитать информацию из файла
            try {
                JSONObject root = new JSONObject(new JSONTokener(new FileReader(file)));
                int count = root.getInt("count");
                String date = root.getString("date");
                String version = root.getString("version");

                System.out.println("\n📦 Содержимое файла:");
                System.out.println("   Количество студентов: " + count);
                System.out.println("   Дата сохранения: " + date);
                System.out.println("   Версия формата: " + version);

            } catch (Exception e) {
                System.out.println("\n⚠️ Не удалось прочитать содержимое файла.");
            }
        } else {
            System.out.println("❌ Файл " + DEFAULT_FILENAME + " не найден.");
            System.out.println("   Создайте файл, сохранив данные (опция 5).");
        }
    }
}