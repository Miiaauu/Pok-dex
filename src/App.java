
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

    private Stage stage;

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

        JSONArray data = ReadData.getData();

        App app = new App();

        IndexC indexC = (IndexC) app.getFXML("/edu/nintendo/drawable/index.fxml","Pokedex",new Image("/res/img/icon.png"),"/res/stylesheet/style.css");
        indexC.init(data);

        app.getStage().setMinWidth(700);
        app.getStage().setMinHeight(630);
        app.getStage().show();
    }

    public static void main(String[] args) { launch(args); }
}
