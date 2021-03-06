package edu.nintendo.controller;

import com.jfoenix.controls.*;

import edu.nintendo.model.Animation;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import edu.nintendo.model.ReadData;
import edu.nintendo.pojo.PokeEntity;
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

    private JFXDialog weaknesses;
    private JFXDialogLayout layout;
    private JFXAutoCompletePopup autoCompletePopup;
    private MaskerPane maskerPane;

    private String pokeballIcon = "M12,2A10,10 0 0,1 22,12A10,10 0 0,1 12,22A10,10 0 0,1 2,12A10,10 0 0,1 12,2M12,4C7.92,4 4.55,7.05 4.06,11H8.13C8.57,9.27 10.14,8 12,8C13.86,8 15.43,9.27 15.87,11H19.94C19.45,7.05 16.08,4 12,4M12,20C16.08,20 19.45,16.95 19.94,13H15.87C15.43,14.73 13.86,16 12,16C10.14,16 8.57,14.73 8.13,13H4.06C4.55,16.95 7.92,20 12,20M12,10A2,2 0 0,0 10,12A2,2 0 0,0 12,14A2,2 0 0,0 14,12A2,2 0 0,0 12,10Z";

    public void init(List<PokeEntity> data) {

        weaknesses = new JFXDialog();
        layout = new JFXDialogLayout();
        autoCompletePopup = new JFXAutoCompletePopup();
        maskerPane = new MaskerPane();

        ReadData.loadPokeData(data,list,pokeballIcon,this);
        ReadData.loadCategories(drawer);

        custom();
        onAction();
    }

    private void custom() {
        maskerPane.setVisible(false);
        drawer.setVisible(false);
        root.getChildren().add(maskerPane);
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
            Animation.hamburgerTransition(hamburger);
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

        searchBtn.setOnAction( evt -> {
            if (!searchFld.getText().isEmpty()) {
                ReadData.autoComplete(autoCompletePopup, searchFld);
            }
        });

        reloadBtn.setOnAction( evt ->{
            ReadData.reload(maskerPane,contenRoot,list,searchFld,pokeballIcon,this);
        });

        autoCompletePopup.setSelectionHandler( event -> {
            ReadData.loadPokemon(event,searchFld,list,contenRoot,pokeballIcon,this);
        });

    }

    @Override
    public void handle(Event event) {

        if (event.getSource().getClass().getName().equals("com.jfoenix.controls.JFXButton")) {
            ReadData.showInfo(event,layout,weaknesses,root);
        }

    }

}
