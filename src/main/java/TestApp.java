import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestApp extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("TestApp started");
        Label label = new Label("JavaFX работает!");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Тест");
        stage.show();
        System.out.println("Window should be visible");
    }

    public static void main(String[] args) {
        System.out.println("Launching TestApp");
        launch(args);
    }
}