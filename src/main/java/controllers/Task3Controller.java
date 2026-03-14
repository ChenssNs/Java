package controllers;

import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;

public class Task3Controller implements Initializable {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> groupColumn;
    @FXML
    private TableColumn<Student, Integer> markColumn;
    @FXML
    private TableColumn<Student, Integer> egeColumn;

    @FXML
    private TextField newIdField;
    @FXML
    private TextField newLastNameField;
    @FXML
    private TextField newFirstNameField;
    @FXML
    private TextField newGroupField;
    @FXML
    private TextField newDepartmentField;
    @FXML
    private TextField newDisciplineField;
    @FXML
    private TextField newMarkField;
    @FXML
    private TextField newTeacherField;
    @FXML
    private TextField newEgeField;

    @FXML
    private TextArea fileStatusArea;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;  // Новая кнопка удаления
    @FXML
    private Label countLabel;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idStudent"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));
        markColumn.setCellValueFactory(new PropertyValueFactory<>("mark"));
        egeColumn.setCellValueFactory(new PropertyValueFactory<>("egeScore"));

        // Загрузка начальных данных
        loadInitialData();
        updateCountLabel();
    }

    private void loadInitialData() {
        studentData.addAll(
                new Student("2023001", "Иванов", "Иван", "ПИ-21", "Программная инженерия", "ООП", 9, "Сидоров А.П.", 87),
                new Student("2023002", "Петрова", "Мария", "ПИ-22", "Программная инженерия", "Базы данных", 8, "Кузнецова Е.В.", 92),
                new Student("2023003", "Сидоров", "Алексей", "ИВТ-21", "Информатика", "Веб-программирование", 10, "Васильева Т.И.", 78),
                new Student("2023004", "Алексеев", "Дмитрий", "ПИ-21", "Программная инженерия", "Математика", 7, "Петров В.И.", 85),
                new Student("2023005", "Васильева", "Елена", "ИВТ-22", "Информатика", "Физика", 9, "Смирнов К.Л.", 95)
        );
        studentTable.setItems(studentData);
    }

    private void updateCountLabel() {
        if (countLabel != null) {
            countLabel.setText("Всего студентов: " + studentData.size());
        }
    }

    @FXML
    private void handleRefreshButton() {
        studentTable.setItems(studentData);
        updateCountLabel();
        showInfo("Обновлено", "Таблица обновлена");
    }

    @FXML
    private void handleSortByEgeButton() {
        studentData.sort((s1, s2) -> Integer.compare(s2.getEgeScore(), s1.getEgeScore()));
        showInfo("Сортировка", "Студенты отсортированы по баллу ЕГЭ (по убыванию)");
    }

    @FXML
    private void handleAddStudentButton() {
        try {
            if (newIdField.getText().isEmpty() ||
                    newLastNameField.getText().isEmpty() ||
                    newFirstNameField.getText().isEmpty()) {
                showAlert("Ошибка", "Заполните ID, фамилию и имя!");
                return;
            }

            String id = newIdField.getText();
            String lastName = newLastNameField.getText();
            String firstName = newFirstNameField.getText();
            String group = newGroupField.getText().isEmpty() ? "Не указана" : newGroupField.getText();
            String department = newDepartmentField.getText().isEmpty() ? "Не указана" : newDepartmentField.getText();
            String discipline = newDisciplineField.getText().isEmpty() ? "Не указана" : newDisciplineField.getText();

            int mark = newMarkField.getText().isEmpty() ? 0 : Integer.parseInt(newMarkField.getText());
            String teacher = newTeacherField.getText().isEmpty() ? "Не указан" : newTeacherField.getText();
            int ege = newEgeField.getText().isEmpty() ? 0 : Integer.parseInt(newEgeField.getText());

            Student student = new Student(id, lastName, firstName, group, department,
                    discipline, mark, teacher, ege);
            studentData.add(student);

            clearNewStudentFields();
            updateCountLabel();
            showInfo("Успех", "Студент добавлен!");

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Оценка и ЕГЭ должны быть числами!");
        }
    }

    @FXML
    private void handleDeleteStudentButton() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Подтверждение удаления");
            confirm.setHeaderText("Удаление студента");
            confirm.setContentText("Вы уверены, что хотите удалить студента " +
                    selected.getLastName() + " " + selected.getFirstName() + "?");

            confirm.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    studentData.remove(selected);
                    updateCountLabel();
                    showInfo("Успех", "Студент удален");
                }
            });
        } else {
            showAlert("Ошибка", "Выберите студента для удаления");
        }
    }

    @FXML
    private void handleSaveJsonButton() {
        try {
            JSONArray jsonArray = new JSONArray();
            for (Student student : studentData) {
                jsonArray.put(student.toJSON());
            }

            JSONObject root = new JSONObject();
            root.put("students", jsonArray);
            root.put("count", studentData.size());
            root.put("date", new java.util.Date().toString());

            try (FileWriter file = new FileWriter("students.json")) {
                file.write(root.toString(4));
            }

            fileStatusArea.setText("✅ Данные сохранены в файл students.json\n" +
                    "Количество студентов: " + studentData.size() + "\n" +
                    "Путь: " + new File("students.json").getAbsolutePath());

        } catch (Exception e) {
            fileStatusArea.setText("❌ Ошибка сохранения: " + e.getMessage());
        }
    }

    @FXML
    private void handleLoadJsonButton() {
        try {
            File file = new File("students.json");
            if (!file.exists()) {
                fileStatusArea.setText("❌ Файл students.json не найден!");
                return;
            }

            JSONTokener tokener = new JSONTokener(new FileReader(file));
            JSONObject root = new JSONObject(tokener);
            JSONArray jsonArray = root.getJSONArray("students");

            studentData.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject studentJson = jsonArray.getJSONObject(i);
                studentData.add(new Student(studentJson));
            }

            updateCountLabel();
            fileStatusArea.setText("✅ Данные загружены из файла students.json\n" +
                    "Загружено студентов: " + studentData.size());

        } catch (Exception e) {
            fileStatusArea.setText("❌ Ошибка загрузки: " + e.getMessage());
        }
    }

    private void clearNewStudentFields() {
        newIdField.clear();
        newLastNameField.clear();
        newFirstNameField.clear();
        newGroupField.clear();
        newDepartmentField.clear();
        newDisciplineField.clear();
        newMarkField.clear();
        newTeacherField.clear();
        newEgeField.clear();
    }

    @FXML
    private void handleBackButton() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}