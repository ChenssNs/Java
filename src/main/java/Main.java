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
            // Просто загружаем FXML файл из папки resources/fxml/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/fxml/MainView.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.setPrimaryStage(primaryStage);
            primaryStage.setTitle("Сборник заданий по Java");
            primaryStage.setScene(new Scene(root, 600, 500));
            primaryStage.show();

        } catch (Exception e) {
            System.err.println("Ошибка запуска:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}