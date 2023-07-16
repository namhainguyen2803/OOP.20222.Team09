package src.test;

import src.screen.controller.mainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader mainLoader = new FXMLLoader(mainWindowApplication.class.getResource("/src/screen/fxml/mainWindow.fxml"));
            mainWindowController mainController = new mainWindowController();
            mainLoader.setController(mainController);
            Scene scene = new Scene(mainLoader.load());
            stage.setTitle("Tree View Visualizer");
            stage.setScene(scene);
//            stage.setFullScreen(true);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}