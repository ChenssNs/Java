package domain;

import org.json.JSONObject;
import org.json.JSONArray;

public class Student {
    // Поля класса
    private String idStudent;     // номер зачетной книжки
    private String lastName;      // фамилия
    private String firstName;     // имя
    private String group;         // группа
    private String department;    // кафедра
    private String discipline;    // дисциплина
    private int mark;             // оценка
    private String teacherName;   // фамилия преподавателя
    private int egeScore;         // балл ЕГЭ

    public Student(String idStudent, String lastName, String firstName,
                   String group, String department) {
        this.idStudent = idStudent;
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.department = department;
        // По умолчанию
        this.discipline = "Не указана";
        this.mark = 0;
        this.teacherName = "Не указан";
        this.egeScore = 0;
    }

    public Student(String idStudent, String lastName, String firstName,
                   String group, String department, String discipline,
                   int mark, String teacherName, int egeScore) {
        this.idStudent = idStudent;
        this.lastName = lastName;
        this.firstName = firstName;
        this.group = group;
        this.department = department;
        this.discipline = discipline;
        this.mark = mark;
        this.teacherName = teacherName;
        this.egeScore = egeScore;
    }

    // Конструктор из JSON-объекта
    public Student(JSONObject json) {
        this.idStudent = json.getString("idStudent");
        this.lastName = json.getString("lastName");
        this.firstName = json.getString("firstName");
        this.group = json.getString("group");
        this.department = json.getString("department");
        this.discipline = json.getString("discipline");
        this.mark = json.getInt("mark");
        this.teacherName = json.getString("teacherName");
        this.egeScore = json.getInt("egeScore");
    }

    // Метод для преобразования в JSON-объект
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("idStudent", idStudent);
        json.put("lastName", lastName);
        json.put("firstName", firstName);
        json.put("group", group);
        json.put("department", department);
        json.put("discipline", discipline);
        json.put("mark", mark);
        json.put("teacherName", teacherName);
        json.put("egeScore", egeScore);
        return json;
    }

    // гет
    public String getIdStudent() {
        return idStudent;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGroup() {
        return group;
    }

    public String getDepartment() {
        return department;
    }

    public String getDiscipline() {
        return discipline;
    }

    public int getMark() {
        return mark;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public int getEgeScore() {
        return egeScore;
    }

    // сет
    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public void setMark(int mark) {
        if (mark >= 0 && mark <= 10) {
            this.mark = mark;
        } else {
            System.out.println("Оценка должна быть от 0 до 10");
        }
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public void setEgeScore(int egeScore) {
        if (egeScore >= 0 && egeScore <= 100) {
            this.egeScore = egeScore;
        } else {
            System.out.println("Балл ЕГЭ должен быть от 0 до 100");
        }
    }

    @Override
    public String toString() {
        return String.format(
                "=======================================\n" +
                        "СТУДЕНТ\n" +
                        "=======================================\n" +
                        "Зачетная книжка: %s\n" +
                        "ФИО: %s %s\n" +
                        "Группа: %s\n" +
                        "Кафедра: %s\n" +
                        "Балл ЕГЭ: %d\n" +
                        "---------------------------------------\n" +
                        "УСПЕВАЕМОСТЬ\n" +
                        "---------------------------------------\n" +
                        "Дисциплина: %s\n" +
                        "Оценка: %d\n" +
                        "Преподаватель: %s\n" +
                        "=======================================\n",
                idStudent, lastName, firstName, group, department, egeScore,
                discipline, mark, teacherName
        );
    }

    public String toShortString() {
        return String.format("%s %s | Группа: %s | Оценка: %d | ЕГЭ: %d",
                lastName, firstName, group, mark, egeScore);
    }

    public static class EgeScoreComparator implements java.util.Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return Integer.compare(s2.egeScore, s1.egeScore);
        }
    }
}