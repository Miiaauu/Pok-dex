package edu.nintendo.controller;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXAutoCompleteEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import edu.nintendo.model.Animation;
import edu.nintendo.pojo.Entity;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import edu.nintendo.model.ReadData;
import edu.nintendo.pojo.PokeEntity;
import javafx.scene.shape.SVGPath;
import org.controlsfx.control.MaskerPane;

import java.util.List;


public class IndexC implements EventHandler {

    @FXML private StackPane root;
    @FXML private StackPane contenRoot;
    @FXML private ListView<HBox> list;
    @FXML private JFXDrawer drawer;
    @FXML private JFXHamburger hamburger;
    @FXML private JFXTextField searchFld;
    @FXML private JFXButton searchBtn;
    @FXML private JFXButton reloadBtn;

    private String pokeballIcon = "M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4C7.92,4 4.55,7.05 4.06,11H8.13C8.57,9.27 10.14,8 12,8C13.86,8 15.43,9.27 15.87,11H19.94C19.45,7.05 16.08,4 12,4M12,20C16.08,20 19.45,16.95 19.94,13H15.87C15.43,14.73 13.86,16 12,16C10.14,16 8.57,14.73 8.13,13H4.06C4.55,16.95 7.92,20 12,20M12,10A2,2 0 0,0 10,12A2,2 0 0,0 12,14A2,2 0 0,0 14,12A2,2 0 0,0 12,10Z";

    private JFXDialog weaknesses;
    private JFXDialogLayout layout;
    private HamburgerBackArrowBasicTransition transition;
    private JFXAutoCompletePopup autoCompletePopup;
    private MaskerPane maskerPane;

    public void init(List<PokeEntity> data) {

        weaknesses = new JFXDialog();
        layout = new JFXDialogLayout();
        transition = new HamburgerBackArrowBasicTransition(hamburger);
        autoCompletePopup = new JFXAutoCompletePopup();
        maskerPane = new MaskerPane();

        SVGPath svgPath = new SVGPath();

        svgPath.setContent(pokeballIcon);

        maskerPane.setVisible(false);
        drawer.setVisible(false);
        transition.setRate(-1);
        root.getChildren().add(maskerPane);

        ReadData.loadPokeData(data,list,pokeballIcon,this);
        ReadData.loadCategories(drawer);

        onAction();
    }

    private void onAction() {

        hamburger.setOnMouseClicked( evt -> {

            if (drawer.isClosed()) {
                drawer.open();
                drawer.setVisible(true);
                Animation.close(searchFld);
                Animation.close(reloadBtn);
                Animation.close(searchBtn);
            } else {
                drawer.close();
            }

        });

        hamburger.setOnMousePressed( evt ->{
            transition.setRate(transition.getRate() * -1);
            transition.play();
        });

        drawer.setOnDrawerClosed( evt -> {
            drawer.setVisible(false);
            Animation.open(searchFld);
            Animation.open(reloadBtn);
            Animation.open(searchBtn);
        });

        searchFld.setOnKeyPressed( evt -> {

            if (evt.getCode() == KeyCode.ENTER) {
                if (!searchFld.getText().isEmpty()) {
                    ReadData.autoComplete(autoCompletePopup, searchFld);
                }
            }

        });

        reloadBtn.setOnAction( evt ->{
            reload();
        });

        searchBtn.setOnAction( evt -> {
            if (!searchFld.getText().isEmpty()) {
                ReadData.autoComplete(autoCompletePopup, searchFld);
            }
        });

        autoCompletePopup.setSelectionHandler( event -> {
            HBox pokemonContainer = (HBox) ((JFXAutoCompleteEvent) event).getObject();
            Label pokemon = (Label) pokemonContainer.getChildren().get(1);
            searchFld.setText(pokemon.getText());

            PokeEntity pokemon1 = Entity.findPokemon(PokeEntity.class,Integer.parseInt(pokemon.getId()));
            list.getItems().clear();

            ReadData.loadBigPokeData(pokemon1,contenRoot,pokeballIcon,this);
        });

    }

    @Override
    public void handle(Event event) {

        if (event.getSource().getClass().getName().equals("com.jfoenix.controls.JFXButton")) {
            ReadData.showInfo(event,layout,weaknesses,root);
        }

    }

    private void reload() {
        maskerPane.setText("Cargando datos...");
        maskerPane.setVisible(true);

        contenRoot.getChildren().clear();
        contenRoot.getChildren().add(list);

        Runnable task = () -> {
            Platform.runLater(() -> {
                List<PokeEntity> pokeEntities = Entity.inject(PokeEntity.class);
                list.getItems().clear();
                searchFld.setText("");
                ReadData.loadPokeData(pokeEntities, list, pokeballIcon, IndexC.this);
                maskerPane.setVisible(false);
            });
        };

        Thread backgroundThread = new Thread(task);
        backgroundThread.setDaemon(true);
        backgroundThread.start();
    }

}
