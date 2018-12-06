package edu.nintendo.model;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TypeColor {

    public static void setColor(HBox box, String[] types) {

        for(String ty: types) {
            switch (ty) {
                case "Fuego":
                    Label labelType = new Label(" " + ty + " ");
                    box.getChildren().add(labelType);
                    labelType.getStyleClass().add("fire");
                    break;
                case "Planta":
                    Label labelType2 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType2);
                    labelType2.getStyleClass().add("grass");
                    break;
                case "Veneno":
                    Label labelType3 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType3);
                    labelType3.getStyleClass().add("poison");
                    break;
                case "Volador":
                    Label labelType4 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType4);
                    labelType4.getStyleClass().add("flying");
                    break;
                case "Hielo":
                    Label labelType5 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType5);
                    labelType5.getStyleClass().add("ice");
                    break;
                case "Psíquico":
                    Label labelType6 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType6);
                    labelType6.getStyleClass().add("psychic");
                    break;
                case "Tierra":
                    Label labelType7 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType7);
                    labelType7.getStyleClass().add("ground");
                    break;
                case "Roca":
                    Label labelType8 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType8);
                    labelType8.getStyleClass().add("rock");
                    break;
                case "Agua":
                    Label labelType9 = new Label(" " + ty + " ");
                    box.getChildren().add(labelType9);
                    labelType9.getStyleClass().add("water");
                    break;
            }
        }
    }
}
