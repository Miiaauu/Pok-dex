package edu.nintendo.model;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class TypeColors {

    public static void setColor(HBox box,String[] types) {
        for (String ty : types) {

            switch (ty) {

                case "Fuego":

                    Label label = new Label(" " + ty + " ");
                    label.getStyleClass().add("fire");
                    box.getChildren().add(label);

                    break;

                case "Planta":

                    Label label2 = new Label(" " + ty + " ");
                    label2.getStyleClass().add("plant");
                    box.getChildren().add(label2);

                    break;

                case "Veneno":

                    Label label3 = new Label(" " + ty + " ");
                    label3.getStyleClass().add("poison");
                    box.getChildren().add(label3);

                    break;

                case "Hielo":

                    Label label4 = new Label(" " + ty + " ");
                    label4.getStyleClass().add("hice");
                    box.getChildren().add(label4);

                    break;

                case "Psíquico":

                    Label label5 = new Label(" " + ty + " ");
                    label5.getStyleClass().add("psychic");
                    box.getChildren().add(label5);

                    break;

                case "Tierra":

                    Label label6 = new Label(" " + ty + " ");
                    label6.getStyleClass().add("ground");
                    box.getChildren().add(label6);

                    break;

                case "Roca":

                    Label label7 = new Label(" " + ty + " ");
                    label7.getStyleClass().add("rock");
                    box.getChildren().add(label7);

                    break;

                case "Agua":

                    Label label8 = new Label(" " + ty + " ");
                    label8.getStyleClass().add("whater");
                    box.getChildren().add(label8);

                    break;

                case "Volador":

                    Label label9 = new Label(" " + ty + " ");
                    label9.getStyleClass().add("flying");
                    box.getChildren().add(label9);

                    break;
            }

        }
    }

}
