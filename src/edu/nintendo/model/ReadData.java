package edu.nintendo.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import edu.nintendo.pojo.PokeEntity;
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
import java.util.ArrayList;
import java.util.List;

public class ReadData {

    public static void loadPokeData(List<PokeEntity> data, ListView<HBox> list, String icon, EventHandler evnt) {

        ArrayList<Node> elements = new ArrayList<>();
        int i = 0;

        for (PokeEntity dat : data) {

                System.out.print(dat.getName() + " ");

                Label number = new Label(dat.getNumber());
                number.getStyleClass().add("number");
                
                ImageView img = new ImageView(new Image(dat.getImg(), 100, 100, true, true));

                Label name = new Label(dat.getName());
                name.getStyleClass().add("name");

                HBox type = new HBox();
                type.setAlignment(Pos.CENTER);
                type.setSpacing(5);

                String[] types = dat.getType();
                TypeColors.setColor(type,types);

                for (String o :types) {
                    System.out.println(o);
                }


                SVGPath weaknessesIcon = new SVGPath();
                weaknessesIcon.setContent(icon);
                weaknessesIcon.getStyleClass().add("icon");


                JFXButton action = new JFXButton("",weaknessesIcon);
                action.setId(Stringfy.arrayToString(dat.getWeaknesses()));
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

                    if (elements.size() != 0) {
                        list.getItems().add(hBox);
                    }
                }
                //======================

                System.out.println("✓");
        }

        ArrayList<HBox> erases = new ArrayList<>();
        list.getItems().forEach( o -> {

            if (o.getChildren().size() == 0) {
                erases.add(o);
            }

        });

        erases.forEach( o -> {
            list.getItems().removeAll(o);
        });

    }

    public static void showInfo(Event event, JFXDialogLayout layout, JFXDialog dialog, StackPane root) {

        //IDENTIFICA EL CONTENIDO DEL BOTON
        JFXButton action = (JFXButton) event.getSource();
        String[] datas = Stringfy.stringToArray(action.getId());
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
