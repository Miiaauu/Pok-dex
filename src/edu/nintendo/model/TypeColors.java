package edu.nintendo.model;

import com.jfoenix.controls.JFXButton;
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
                    label4.getStyleClass().add("ice");
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
                    label8.getStyleClass().add("water");
                    box.getChildren().add(label8);

                    break;

                case "Volador":

                    Label label9 = new Label(" " + ty + " ");
                    label9.getStyleClass().add("flying");
                    box.getChildren().add(label9);

                    break;

                case "Bicho":

                    Label label10 = new Label(" " + ty + " ");
                    label10.getStyleClass().add("insect");
                    box.getChildren().add(label10);

                    break;

                case "Normal":

                    Label label11 = new Label(" " + ty + " ");
                    label11.getStyleClass().add("normal");
                    box.getChildren().add(label11);

                    break;

                case "Hada":

                    Label label12 = new Label(" " + ty + " ");
                    label12.getStyleClass().add("fairy");
                    box.getChildren().add(label12);

                    break;

                case "Eléctrico":

                    Label label13 = new Label(" " + ty + " ");
                    label13.getStyleClass().add("electric");
                    box.getChildren().add(label13);

                    break;

                case "Lucha":

                    Label label14 = new Label(" " + ty + " ");
                    label14.getStyleClass().add("fight");
                    box.getChildren().add(label14);

                    break;

                case "Acero":

                    Label label15 = new Label(" " + ty + " ");
                    label15.getStyleClass().add("steel");
                    box.getChildren().add(label15);

                    break;

                case "Fantasma":

                    Label label16 = new Label(" " + ty + " ");
                    label16.getStyleClass().add("ghost");
                    box.getChildren().add(label16);

                    break;

                    default:
                        Label out = new Label(" " + ty + " ");
                        out.getStyleClass().add("default");
                        box.getChildren().add(out);
            }

        }
    }

    public static void setColor(JFXButton types) {

            switch (types.getText()) {
                case "Fuego": types.getStyleClass().add("fire"); break;
                case "Planta": types.getStyleClass().add("plant"); break;
                case "Veneno": types.getStyleClass().add("poison"); break;
                case "Hielo": types.getStyleClass().add("ice"); break;
                case "Psíquico": types.getStyleClass().add("psychic"); break;
                case "Tierra": types.getStyleClass().add("ground"); break;
                case "Roca": types.getStyleClass().add("rock"); break;
                case "Agua": types.getStyleClass().add("water"); break;
                case "Volador": types.getStyleClass().add("flying"); break;
                case "Bicho": types.getStyleClass().add("insect"); break;
                case "Normal": types.getStyleClass().add("normal"); break;
                case "Hada": types.getStyleClass().add("fairy"); break;
                case "Eléctrico": types.getStyleClass().add("electric"); break;
                case "Lucha": types.getStyleClass().add("fight"); break;
                case "Acero": types.getStyleClass().add("steel"); break;
                case "Fantasma": types.getStyleClass().add("ghost"); break;
                default: types.getStyleClass().add("default");
            }
    }

}
