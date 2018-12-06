package edu.nintendo.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class ReadData {

    public static JSONArray getData() {

        String out = "";
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(ReadData.class.getResourceAsStream("/resource/db/data.json"), "utf-8"));

            String string;
            while ((string = in.readLine())!=null) {
                out += string;
            }

            array = (JSONArray) parser.parse(out);

            return array;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            array = (JSONArray) parser.parse("[{\"error\":\"No se pudieron obtener los datos.\"}]");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return array;
    }

    public static void loadPokeData(JSONArray data, ListView<HBox> list, String icon, EventHandler evnt) {
        ArrayList<Node> elements = new ArrayList<Node>();
        JSONParser parser = new JSONParser();

        int i = 0;
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
                weaknessesIcon.setContent(icon);
                weaknessesIcon.getStyleClass().add("icon");


                JFXButton action = new JFXButton("",weaknessesIcon);
                action.setId(da.get("weaknesses").toString());
                action.setOnAction(evnt);

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

    public static void showInfo(Event event, JFXDialogLayout layout, JFXDialog dialog, StackPane root) {

        //IDENTIFICA EL CONTENIDO DEL BOTON
        JFXButton action = (JFXButton) event.getSource();
        String[] datas = action.getId().replace("[","").replace("]","").replace("\"","").split(",");;
        //============================================

        //ARREGLOS PARA EL CUERPO DEL DIALOGO
        layout.setHeading(new Label("Debilidades"));
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        //==============================================

        //SE PONE EL COLOR DE LOS TIPOS DE DEVILIDADES
        TypeColors.setColor(hBox,datas);
        //============================================

        //SE PONEN LAS DEBILIDADES IDENTIFICADAS CON SUS RESPECTIVOS ESTILOS
        layout.setBody(hBox.getChildren().size() == 0 ? new Label("No se encuentran debilidades.") : hBox);
        dialog.setContent(layout);
        //==================================================================

        //SE MUESTRA EL DIALOGO
        dialog.show(root);
        //=====================
    }

}
