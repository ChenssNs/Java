import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controllers.MainController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            System.out.println("Загрузка MainView.fxml...");

            // Загружаем FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/MainView.fxml"));
            Parent root = loader.load();

            // Получаем контроллер и передаем ему primaryStage
            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);

            // Создаем сцену
            Scene scene = new Scene(root, 600, 500);

            // Подключаем CSS - ПРОВЕРЯЕМ, ЧТО ФАЙЛ НАЙДЕН
            String cssPath = "resources/css/style.css";
            System.out.println("Загрузка CSS по пути: " + cssPath);

            java.net.URL cssUrl = getClass().getResource(cssPath);
            if (cssUrl == null) {
                System.err.println("CSS файл не найден по пути: " + cssPath);
                System.err.println("Проверьте, что файл лежит в: src/main/resources/css/style.css");
            } else {
                System.out.println("CSS файл найден: " + cssUrl);
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            primaryStage.setTitle("Сборник заданий по Java");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            System.out.println("Главное окно загружено");

        } catch (Exception e) {
            System.err.println("Ошибка запуска:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}