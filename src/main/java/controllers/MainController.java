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
            System.out.println("Загрузка окна: " + fxmlPath);

            String cleanPath = fxmlPath.startsWith("/") ? fxmlPath.substring(1) : fxmlPath;
            System.out.println("Очищенный путь: " + cleanPath);

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(getClass().getResource("/" + cleanPath));
            System.out.println("Путь 1 (/fxml/...): " + loader.getLocation());

            if (loader.getLocation() == null) {
                loader.setLocation(getClass().getResource(cleanPath));
                System.out.println("Путь 2 (fxml/...): " + loader.getLocation());
            }

            if (loader.getLocation() == null) {
                System.err.println("НЕ НАЙДЕН ФАЙЛ: " + fxmlPath);
                System.err.println("Текущая директория: " + System.getProperty("user.dir"));
                System.err.println("Classpath: " + getClass().getResource("/"));
                return;
            }

            Parent root = loader.load();
            Stage taskStage = new Stage();
            taskStage.setTitle(title);
            taskStage.setScene(new Scene(root));
            taskStage.initModality(Modality.WINDOW_MODAL);
            taskStage.initOwner(primaryStage);
            taskStage.showAndWait();

        } catch (IOException e) {
            System.err.println("Ошибка загрузки окна " + title + ":");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка:");
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        // Простое отображение ошибки в консоль для простоты
        System.err.println(message);
    }
}