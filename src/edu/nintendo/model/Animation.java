package edu.nintendo.model;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Animation {

    private static HamburgerBackArrowBasicTransition transition;
    public static boolean hamburgerUse = false;

    public static void open(Node node) {
        node.setVisible(true);
        FadeTransition ft2 = new FadeTransition();
        ft2.setDuration(Duration.millis(1000));
        ft2.setNode(node);
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);
        ft2.play();
    }

    public static void close(Node node) {
        FadeTransition ft2 = new FadeTransition();
        ft2.setDuration(Duration.millis(1000));
        ft2.setNode(node);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        ft2.play();

        ft2.setOnFinished( evt -> {
            node.setVisible(false);
        });
    }

    public static void hamburgerTransition(JFXHamburger hamburger) {

        if (!hamburgerUse) {
            transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburgerUse = true;
        }

        transition.setRate(transition.getRate() * -1);
        transition.play();
    }
}
