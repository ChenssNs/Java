package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnTask1;
    @FXML
    private Button btnTask2;
    @FXML
    private Button btnTask3;
    @FXML
    private Button btnTask0;
    @FXML
    private Button btnExit;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void handleTask1Button() {
        openTaskWindow("resources/fxml/Task1View.fxml", "Задание 1: Массив");
    }

    @FXML
    public void handleTask2Button() {
        openTaskWindow("resources/fxml/Task2View.fxml", "Задание 2: Матрица");
    }

    @FXML
    public void handleTask3Button() {
        openTaskWindow("resources/fxml/Task3View.fxml", "Задание 3: Студенты");
    }

    @FXML
    public void handleTask0Button() {
        openTaskWindow("resources/fxml/Task0View.fxml", "Задание 0: Калькулятор");
    }

    @FXML
    public void handleExitButton() {
        primaryStage.close();
    }

    private void openTaskWindow(String fxmlPath, String title) {
        try {
            System.out.println("Загрузка окна: " + title);
            System.out.println("Ищем файл: " + fxmlPath);

            // ВАЖНО: путь должен начинаться с /
            String correctedPath = fxmlPath;
            if (!correctedPath.startsWith("/")) {
                correctedPath = "/" + correctedPath;
            }

            System.out.println("Исправленный путь: " + correctedPath);

            // Загружаем FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(correctedPath));

            if (loader.getLocation() == null) {
                System.err.println("FXML файл не найден: " + correctedPath);
                System.err.println("Проверьте, что файл существует в папке: src/main/resources/fxml/");

                // Показываем все доступные ресурсы
                System.out.println("Доступные ресурсы в /fxml/:");
                try {
                    java.net.URL fxmlFolder = getClass().getResource("/fxml");
                    System.out.println("Папка fxml: " + fxmlFolder);
                } catch (Exception e) {
                    System.err.println("Не удалось получить список ресурсов");
                }
                return;
            }

            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Подключаем CSS если есть
            try {
                scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
            } catch (Exception e) {
                System.out.println("CSS не найден, продолжаем без стилей");
            }

            Stage taskStage = new Stage();
            taskStage.setTitle(title);
            taskStage.setScene(scene);

            if (primaryStage != null) {
                taskStage.initOwner(primaryStage);
            }

            taskStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Ошибка загрузки окна " + title + ":");
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        // Простое отображение ошибки в консоль для простоты
        System.err.println(message);
    }
}