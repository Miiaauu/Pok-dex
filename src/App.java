import edu.nintendo.controller.IndexC;
import edu.nintendo.model.ReadData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.simple.JSONArray;

import java.io.IOException;

public class App extends Application {

    Stage stage;

    private Object getFXML(String path, String title, Image icon, String css) throws IOException {
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.getIcons().add(icon);
        scene.getRoot().getStylesheets().add(css);
        return loader.getController();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        App app = new App();
        JSONArray data = ReadData.getData();
        //Asigna el controlador del fxml
        IndexC indexC = (IndexC) app.getFXML("/edu/nintendo/view/index.fxml", "Pokédex", new Image("/resource/img/icon.png"), "/resource/stylesheets/style.css");
        indexC.init(data);
        app.getStage().setMinHeight(700);
        app.getStage().setMinWidth(630);
        app.getStage().show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
