package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Task0Controller {

    @FXML
    private TextField fieldA;
    @FXML
    private TextField fieldB;
    @FXML
    private TextArea resultArea;
    @FXML
    private Button btnCalculate;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnBack;
    @FXML
    private Label statusLabel;

    @FXML
    private void handleCalculateButton() {
        try {
            double a = Double.parseDouble(fieldA.getText().trim());
            double b = Double.parseDouble(fieldB.getText().trim());

            StringBuilder result = new StringBuilder();
            result.append("РЕЗУЛЬТАТ ВЫЧИСЛЕНИЙ\n");
            result.append("=".repeat(40)).append("\n\n");
            result.append(String.format("Входные параметры:\n"));
            result.append(String.format("  a = %.2f\n", a));
            result.append(String.format("  b = %.2f\n", b));
            result.append(String.format("  Сумма a + b = %.2f\n\n", a + b));

            if (a + b > 100) {
                double sinValue = Math.sin(a);
                double resultValue = 2 * sinValue;

                result.append("УСЛОВИЕ ВЫПОЛНЕНО: сумма > 100\n\n");
                result.append(String.format("Вычисление: 2 * sin(%.2f)\n", a));
                result.append(String.format("  sin(%.2f) = %.6f\n", a, sinValue));
                result.append("-".repeat(40)).append("\n");
                result.append(String.format("РЕЗУЛЬТАТ: %.6f\n", resultValue));

                statusLabel.setText("Результат вычислен");
                statusLabel.setStyle("-fx-text-fill: green;");
            } else {
                result.append("УСЛОВИЕ НЕ ВЫПОЛНЕНО: сумма ≤ 100\n");
                result.append("Результат не вычисляется.");

                statusLabel.setText("Условие не выполнено");
                statusLabel.setStyle("-fx-text-fill: red;");
            }

            resultArea.setText(result.toString());

        } catch (NumberFormatException e) {
            showAlert("Ошибка ввода", "Пожалуйста, введите корректные числа!");
            statusLabel.setText("Ошибка ввода");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    private void handleClearButton() {
        fieldA.clear();
        fieldB.clear();
        resultArea.clear();
        statusLabel.setText("Готов к работе");
        statusLabel.setStyle("-fx-text-fill: black;");
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