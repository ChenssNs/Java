package utils;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimationUtils {

    public static void fadeIn(Node node, double durationSeconds) {
        FadeTransition fade = new FadeTransition(Duration.seconds(durationSeconds), node);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public static void fadeOut(Node node, double durationSeconds) {
        FadeTransition fade = new FadeTransition(Duration.seconds(durationSeconds), node);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    public static void buttonPressAnimation(Node button) {
        ScaleTransition scale = new ScaleTransition(Duration.millis(100), button);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(0.95);
        scale.setToY(0.95);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);
        scale.play();
    }

    public static void slideIn(Node node, double durationSeconds, double fromX) {
        TranslateTransition translate = new TranslateTransition(Duration.seconds(durationSeconds), node);
        translate.setFromX(fromX);
        translate.setToX(0);
        translate.play();
    }

    public static void rotate(Node node, double durationSeconds, double angle) {
        TranslateTransition rotate = new TranslateTransition(Duration.seconds(durationSeconds), node);
        rotate.setByZ(angle);
        rotate.play();
    }
}