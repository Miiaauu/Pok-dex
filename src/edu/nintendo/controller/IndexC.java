package edu.nintendo.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import edu.nintendo.model.TypeColors;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.SVGPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class IndexC implements EventHandler {

    @FXML private StackPane root;
    @FXML private ListView<HBox> list;

    private JFXDialog weaknesses;
    private JFXDialogLayout layout;

    private JSONArray data;
    private JSONParser parser;

    private String pokeballIcon = "M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4C7.92,4 4.55,7.05 4.06,11H8.13C8.57,9.27 10.14,8 12,8C13.86,8 15.43,9.27 15.87,11H19.94C19.45,7.05 16.08,4 12,4M12,20C16.08,20 19.45,16.95 19.94,13H15.87C15.43,14.73 13.86,16 12,16C10.14,16 8.57,14.73 8.13,13H4.06C4.55,16.95 7.92,20 12,20M12,10A2,2 0 0,0 10,12A2,2 0 0,0 12,14A2,2 0 0,0 14,12A2,2 0 0,0 12,10Z";

    public void init(JSONArray data) {

        this.data = data;
        parser = new JSONParser();
        weaknesses = new JFXDialog();
        layout = new JFXDialogLayout();

        //CUADRICULA
        int i = 0;
        //==========

        ArrayList<Node> elements = new ArrayList<Node>();
        for (Object dat : data) {
            try {
                JSONObject da = (JSONObject) parser.parse(dat.toString());


                Label number = new Label(da.get("number").toString());
                number.getStyleClass().add("number");

                ImageView img = new ImageView(new Image(da.get("img").toString(), 100, 100, true, true));

                Label name = new Label(da.get("name").toString());
                name.getStyleClass().add("name");

                HBox type = new HBox();
                type.setAlignment(Pos.CENTER);
                type.setSpacing(5);

                String[] types = da.get("type").toString().replace("[","").replace("]","").replace("\"","").split(",");
                TypeColors.setColor(type,types);

                SVGPath weaknessesIcon = new SVGPath();
                weaknessesIcon.setContent(pokeballIcon);
                weaknessesIcon.getStyleClass().add("icon");


                JFXButton action = new JFXButton("",weaknessesIcon);
                action.setId(da.get("weaknesses").toString());
                action.setOnAction(this);

                VBox box = new VBox(number,img,name,type,action);
                box.getStyleClass().add("card");
                box.setAlignment(Pos.CENTER);
                box.setSpacing(10);
                box.setPadding(new Insets(20,20,20,20));
                StackPane finalBox = new StackPane(box);
                finalBox.setMargin(box,new Insets(10,10,10,10));

                elements.add(finalBox);

                //ORDEN DE LA CUADRICULA
                i++;
                if ( i %4== 0) {

                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    elements.forEach( o -> {
                        hBox.getChildren().add(o);
                    });

                    list.getItems().add(hBox);
                    elements.clear();
                } else {
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    elements.forEach( o -> {
                        hBox.getChildren().add(o);
                    });
                    System.out.println("HBOX -> " + elements.size());
                    if (elements.size() != 0) {
                        list.getItems().add(hBox);
                    }
                }
                //======================

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void handle(Event event) {

        if (event.getSource().getClass().getName().equals("com.jfoenix.controls.JFXButton")) {
            JFXButton action = (JFXButton) event.getSource();
            String[] datas = action.getId().replace("[","").replace("]","").replace("\"","").split(",");;

            layout.setHeading(new Label("Debilidades"));
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.setSpacing(10);

           TypeColors.setColor(hBox,datas);

            layout.setBody(hBox.getChildren().size() == 0 ? new Label("No se encuentran debilidades.") : hBox);
            weaknesses.setContent(layout);
            weaknesses.show(root);
        }

    }
}