package edu.nintendo.model;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXAutoCompleteEvent;
import edu.nintendo.pojo.Entity;
import edu.nintendo.pojo.PokeEntity;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import org.controlsfx.control.MaskerPane;

import java.util.ArrayList;
import java.util.List;

public class ReadData {

    //METODOS PUBLICOS
    public static void loadPokeData(List<PokeEntity> data, ListView<HBox> list, String icon, EventHandler evnt) {

        ArrayList<Node> elements = new ArrayList<>();
        int i = 0;

        for (PokeEntity dat : data) {

                System.out.print(dat.getName() + " ");

                Label number = new Label(dat.getNumber());
                number.getStyleClass().add("number");

                ImageView img = new ImageView(dat.getImg(100,100));

                Label name = new Label(dat.getName());
                name.getStyleClass().add("name");

                HBox type = new HBox();
                type.setAlignment(Pos.CENTER);
                type.setSpacing(5);

                String[] types = dat.getType();
                TypeColors.setColor(type,types);


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

    public static void loadBigPokeData(PokeEntity data,StackPane conten, String icon, EventHandler evnt) {

        HBox hBox = new HBox();
        hBox.getStyleClass().add("card");
        conten.setMargin(hBox,new Insets(20,20,20,20));

        //SECCION DE NUMERO E IMAGEN
        Label number = new Label(data.getNumber());
        number.setPadding(new Insets(20,0,0,20));
        number.getStyleClass().add("number");
        number.setFont(new Font(16));

        ImageView img = new ImageView(data.getImg(215,215));
        VBox number_img = new VBox(number,img);
        //============================================================


        number_img.getChildren().add(createTypesCard(data.getType(),"Tipo"));
        number_img.getChildren().add(createTypesCard(data.getWeaknesses(),"Debilidad"));
        hBox.getChildren().add(number_img);

        VBox vBox = new VBox();
        Label info = new Label("A Bulbasaur es fácil verle echándose una siesta al\n" +
                                   "sol. La semilla que tiene en el lomo va creciendo\n" +
                                   "cada vez más a medida que absorbe los rayos\n del sol.");
        info.setFont(new Font(16));
        info.setPadding(new Insets(20,20,20,0));

        HBox spesificInfo = new HBox();
        spesificInfo.getStyleClass().add("card-info");
        spesificInfo.setMargin(vBox,new Insets(20,20,20,20));
        spesificInfo.setSpacing(20);

        Label height = new Label("Altura");
        height.setFont(new Font(16));
        height.getStyleClass().add("title");

        Label heightDat = new Label("0,7 m");
        heightDat.setFont(new Font(16));


        Label weight = new Label("Peso");
        weight.setFont(new Font(16));
        weight.getStyleClass().add("title");

        Label weightDat = new Label("6,9 kg");
        weightDat.setFont(new Font(16));

        Label sex = new Label("Sexo");
        sex.setFont(new Font(16));
        sex.getStyleClass().add("title");

        Label sexDat = new Label("♂ ♀");
        sexDat.setFont(new Font(16));

        Label category = new Label("Categoría");
        category.setFont(new Font(16));
        category.getStyleClass().add("title");

        Label categoryDat = new Label("Semilla");
        categoryDat.setFont(new Font(16));

        Label ability = new Label("Habilidad");
        ability.setFont(new Font(16));
        ability.getStyleClass().add("title");

        Label abilityDat = new Label("Espesura");
        abilityDat.setFont(new Font(16));

        VBox lef = new VBox(height,heightDat,weight,weightDat,sex,sexDat);
        lef.setPadding(new Insets(20,20,20,30));

        VBox rig = new VBox(category,categoryDat,ability,abilityDat);
        rig.setPadding(new Insets(20,20,20,20));

        spesificInfo.getChildren().addAll(lef,rig);

        vBox.getChildren().addAll(info,spesificInfo);
        hBox.getChildren().add(vBox);

        conten.getChildren().clear();
        conten.getChildren().add(hBox);
    }

    public static void loadCategories(JFXDrawer drawer) {

        VBox box = new VBox();
        ScrollPane scrollPane = new ScrollPane(box);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ImageView imageView = new ImageView(new Image("/resource/img/giphy.gif",250, 250, true, true));

        List<String> categories = Entity.getCategories(PokeEntity.class);
        box.setSpacing(10);

        for (String  stg : categories) {
            JFXButton button = new JFXButton(stg);
            TypeColors.setColor(button);
            button.setPrefSize(235,25);
            box.getChildren().add(button);
        }

        drawer.setSidePane(new VBox(imageView,scrollPane));
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

    public static void autoComplete(JFXAutoCompletePopup autoCompletePopup,JFXTextField searchFld){

        List<PokeEntity> pokes = Entity.findAllPokemon(PokeEntity.class,searchFld.getText());
        autoCompletePopup.getSuggestions().clear();

        for (PokeEntity o : pokes){

            Label pokemon = new Label(o.getNumber() +" "+ o.getName());
            pokemon.setId(o.getId() + "");
            HBox types = new HBox();
            TypeColors.setColor(types,o.getType());
            types.setAlignment(Pos.CENTER);
            types.setSpacing(10);

            HBox box = new HBox(new ImageView(o.getImg(50,50)),pokemon,types);
            box.setMargin(box,new Insets(10,10,0,10));
            box.setAlignment(Pos.CENTER_LEFT);
            box.getStyleClass().add("card-search");
            box.setSpacing(10);

            box.setOnMouseEntered( event -> {
                box.getStyleClass().clear();
                box.getStyleClass().add("card-search-entered");
            });

            box.setOnMouseExited( event -> {
                box.getStyleClass().clear();
                box.getStyleClass().add("card-search");
            });

            autoCompletePopup.getSuggestions().add(box);
        }

        autoCompletePopup.setFixedCellSize(60);
        autoCompletePopup.setPrefSize(500,50);
        autoCompletePopup.show(searchFld);
    }

    public static void reload(MaskerPane maskerPane,StackPane contenRoot,ListView<HBox> list,JFXTextField searchFld,String pokeballIcon,EventHandler event) {

        maskerPane.setText("Cargando datos...");
        maskerPane.setVisible(true);

        contenRoot.getChildren().clear();
        contenRoot.getChildren().add(list);

        Runnable task = () -> {
            Platform.runLater(() -> {
                List<PokeEntity> pokeEntities = Entity.inject(PokeEntity.class);
                list.getItems().clear();
                searchFld.setText("");
                ReadData.loadPokeData(pokeEntities, list, pokeballIcon, event);
                maskerPane.setVisible(false);
            });
        };

        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

    public static void loadPokemon(Event event,JFXTextField searchFld,ListView<HBox> list,StackPane contenRoot,String pokeballIcon,EventHandler evt) {
        HBox pokemonContainer = (HBox) ((JFXAutoCompleteEvent) event).getObject();
        Label pokemon = (Label) pokemonContainer.getChildren().get(1);
        searchFld.setText(pokemon.getText());

        PokeEntity pokemon1 = Entity.findPokemon(PokeEntity.class,Integer.parseInt(pokemon.getId()));
        list.getItems().clear();

        ReadData.loadBigPokeData(pokemon1,contenRoot,pokeballIcon,evt);
    }
    //==============================================================================

    //METODOS PRIVADOS
    private static VBox createTypesCard(String[] datas, String title) {

        HBox types = new HBox();
        TypeColors.setColor(types,datas);
        types.setPadding(new Insets(0,20,20,20));

        types.getChildren().forEach( o ->{
            Label label = (Label) o;
            label.setFont(new Font(12));
        });
        types.setSpacing(5);

        Label typeLbl = new Label(title);
        typeLbl.setFont(new Font(16));
        typeLbl.setPadding(new Insets(10,0,10,20));

        VBox vBox = new VBox(typeLbl,types);
        vBox.setMargin(vBox,new Insets(10,20,20,20));
        vBox.getStyleClass().add("card-search");

        return vBox;
    }
    //=====================================================================
}
