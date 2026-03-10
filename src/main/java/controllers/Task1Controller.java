package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tasks.Task1;

import java.net.URL;
import java.util.ResourceBundle;

public class Task1Controller implements Initializable {

    @FXML
    private Slider sizeSlider;
    @FXML
    private TextField sizeField;
    @FXML
    private Slider nSlider;
    @FXML
    private TextField nField;
    @FXML
    private Button btnGenerate;
    @FXML
    private Button btnFindMax;
    @FXML
    private Button btnBack;
    @FXML
    private TextArea originalArrayArea;
    @FXML
    private TextArea resultArea;
    @FXML
    private Label statsLabel;

    private int[] currentArray;
    private int arraySize = 20;
    private int n = 5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка слайдеров
        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            arraySize = newVal.intValue();
            sizeField.setText(String.valueOf(arraySize));
        });

        nSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            n = newVal.intValue();
            nField.setText(String.valueOf(n));
        });
    }

    @FXML
    private void handleGenerateButton() {
        // Используем логику из Task1 для генерации массива
        currentArray = Task1.generateArray(arraySize);
        displayArray(currentArray, originalArrayArea);
        resultArea.clear();
        statsLabel.setText("Массив сгенерирован. Размер: " + arraySize);
    }

    @FXML
    private void handleFindMaxButton() {
        if (currentArray == null) {
            showAlert("Ошибка", "Сначала сгенерируйте массив!");
            return;
        }

        int[] maxValues = Task1.getNMaxValues(currentArray, n);

        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" максимальных значений:\n");
        for (int i = 0; i < maxValues.length; i++) {
            sb.append(maxValues[i]);
            if (i < maxValues.length - 1) sb.append(", ");
        }
        resultArea.setText(sb.toString());

        // Статистика
        double avg = Task1.getAverage(currentArray);
        int min = Task1.getMinValue(currentArray);
        int evenCount = Task1.countEvenNumbers(currentArray);

        statsLabel.setText(String.format("Среднее: %.2f | Мин: %d | Четных: %d", avg, min, evenCount));
    }

    @FXML
    private void handleBackButton() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }

    private void displayArray(int[] array, TextArea area) {
        StringBuilder sb = new StringBuilder("Исходный массив:\n");
        for (int i = 0; i < array.length; i++) {
            sb.append(String.format("%4d", array[i]));
            if ((i + 1) % 10 == 0) sb.append("\n");
        }
        area.setText(sb.toString());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}