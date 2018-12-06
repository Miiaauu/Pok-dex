package edu.nintendo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import edu.nintendo.model.TypeColor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Stack;

public class IndexC implements EventHandler {
    @FXML
    private StackPane root;
    @FXML
    private ListView<HBox> list;

    private JFXDialog weaknessesDialog;
    private JFXDialogLayout layout;

    private JSONArray data;
    private JSONParser parser;

    //Icono pokeball
    private String pokeballIcon = "M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4C7.92,4 4.55,7.05 4.06,11H8.13C8.57,9.27 10.14,8 12,8C13.86,8 15.43,9.27 15.87,11H19.94C19.45,7.05 16.08,4 12,4M12,20C16.08,20 19.45,16.95 19.94,13H15.87C15.43,14.73 13.86,16 12,16C10.14,16 8.57,14.73 8.13,13H4.06C4.55,16.95 7.92,20 12,20M12,10A2,2 0 0,0 10,12A2,2 0 0,0 12,14A2,2 0 0,0 14,12A2,2 0 0,0 12,10Z";

    public void init(JSONArray data) {
        this.data = data;

        parser = new JSONParser();
        weaknessesDialog = new JFXDialog();
        layout = new JFXDialogLayout();

        //Grid, columnas y filas
        int i = 0;
        int j = 0;

        ArrayList<Node> element = new ArrayList<>();

        for(Object dat: data){
            try {
                JSONObject d = (JSONObject) parser.parse(dat.toString());

                Label labelNumber = new Label(d.get("number").toString());
                labelNumber.getStyleClass().add("number");

                ImageView view = new ImageView(new Image(d.get("img").toString(), 100, 100, true, true));

                Label label = new Label(d.get("name").toString());
                label.getStyleClass().add("name");

                HBox type = new HBox();
                type.setAlignment(Pos.CENTER);
                type.setSpacing(5);

                String[] types = (String[]) d.get("type").toString().replace("[", "").replace("]", "").replace("\"", "").split(",");
                TypeColor.setColor(type, types);

                SVGPath icon = new SVGPath();
                icon.setContent(pokeballIcon);
                icon.getStyleClass().add("icon");

                JFXButton actionButton = new JFXButton("", icon);
                actionButton.setId(d.get("weaknesses").toString());
                actionButton.setOnAction(this);

                VBox vbox = new VBox(labelNumber, view, label, type, actionButton);
                vbox.getStyleClass().add("card");
                vbox.setAlignment(Pos.CENTER);
                vbox.setSpacing(10);
                StackPane finalBox = new StackPane(vbox);
                finalBox.setMargin(vbox, new Insets(10, 10, 10, 10));

                element.add(finalBox);

                i++;

                //Mostrar de 4 en 4 los pokémon
                if(i % 4 == 0){
                    HBox boxFila = new HBox();
                    boxFila.setAlignment(Pos.CENTER);
                    element.forEach(o -> {
                        boxFila.getChildren().add(o);
                    });

                    list.getItems().add(boxFila);
                    element.clear();

                } else {
                    HBox boxFila = new HBox();
                    boxFila.setAlignment(Pos.CENTER);
                    element.forEach(o -> {
                        boxFila.getChildren().add(o);
                    });
                    if(boxFila.getChildren().size() != 0) {
                        list.getItems().add(boxFila);
                    }
                }
                //============================

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void handle(Event event) {
        if(event.getSource().getClass().getName().equals("com.jfoenix.controls.JFXButton")){
            JFXButton actionButton = (JFXButton) event.getSource();
            String[] data = actionButton.getId().replace("[", "").replace("]", "").replace("\"", "").split(",");

            layout.setHeading(new Label("Debilidades"));

            HBox box = new HBox();
            box.setSpacing(10);
            box.setAlignment(Pos.CENTER);

            TypeColor.setColor(box, data);

            layout.setBody(box.getChildren().size() == 0 ? new Label("No se encuentran debilidades") : box);
            weaknessesDialog.setContent(layout);
            weaknessesDialog.show(root);
        }
    }
}
