package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tasks.Task2;

import java.net.URL;
import java.util.ResourceBundle;

public class Task2Controller implements Initializable {

    @FXML
    private Slider rowsSlider;
    @FXML
    private TextField rowsField;
    @FXML
    private Slider colsSlider;
    @FXML
    private TextField colsField;
    @FXML
    private Button btnGenerate;
    @FXML
    private Button btnFindMin;
    @FXML
    private Button btnSearchColumn;
    @FXML
    private Button btnBack;
    @FXML
    private TextArea matrixArea;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField searchColumnField;
    @FXML
    private Label infoLabel;

    private int[][] currentMatrix;
    private int rows = 4;
    private int cols = 4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка слайдеров
        rowsSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            rows = newVal.intValue();
            rowsField.setText(String.valueOf(rows));
            infoLabel.setText("Размер матрицы: " + rows + "x" + cols);
        });

        colsSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            cols = newVal.intValue();
            colsField.setText(String.valueOf(cols));
            infoLabel.setText("Размер матрицы: " + rows + "x" + cols);
        });

        infoLabel.setText("Размер матрицы: " + rows + "x" + cols);
    }

    @FXML
    private void handleGenerateButton() {
        currentMatrix = Task2.generateMatrix(rows, cols);
        displayMatrix(currentMatrix);
        resultArea.clear();
        infoLabel.setText("Матрица " + rows + "x" + cols + " сгенерирована");
    }

    @FXML
    private void handleFindMinButton() {
        if (currentMatrix == null) {
            showAlert("Ошибка", "Сначала сгенерируйте матрицу!");
            return;
        }

        StringBuilder sb = new StringBuilder("МИНИМАЛЬНЫЕ ЗНАЧЕНИЯ ПО СТОЛБЦАМ:\n");
        sb.append("-".repeat(40)).append("\n");

        for (int col = 0; col < cols; col++) {
            int minIndex = Task2.findMinInColumn(currentMatrix, col);
            sb.append(String.format("Столбец %d: строка %d (значение: %d)\n",
                    col, minIndex, currentMatrix[minIndex][col]));
        }

        sb.append("\nПоиск завершен");
        resultArea.setText(sb.toString());
    }

    @FXML
    private void handleSearchColumnButton() {
        if (currentMatrix == null) {
            showAlert("Ошибка", "Сначала сгенерируйте матрицу!");
            return;
        }

        try {
            int column = Integer.parseInt(searchColumnField.getText());
            if (column < 0 || column >= cols) {
                showAlert("Ошибка", "Столбец должен быть от 0 до " + (cols - 1));
                return;
            }

            int minIndex = Task2.findMinInColumn(currentMatrix, column);
            String currentText = resultArea.getText();
            resultArea.setText(currentText + String.format(
                    "\n\n🔍 Поиск: столбец %d, минимум в строке %d (значение: %d)",
                    column, minIndex, currentMatrix[minIndex][column]));

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите корректный номер столбца!");
        }
    }

    private void displayMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder("📋 МАТРИЦА " + rows + "x" + cols + ":\n");
        sb.append("-".repeat(40)).append("\n");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(String.format("%4d", matrix[i][j]));
            }
            sb.append("\n");
        }
        matrixArea.setText(sb.toString());
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
}