package com.example.treedatastructure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class genericTreeController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private StackPane stackPaneInput;

    @FXML
    private HBox hBoxCreate;

    @FXML
    private HBox hBoxManual;

    @FXML
    private TextField tfRootCreate;

    @FXML
    private Button btnCreate;

    @FXML
    private HBox hBoxTraverse;


    private Stage menuStage;

    public genericTreeController(Stage stage) {
        this.menuStage = stage;
    }

    @FXML
    private void initialize() {
        stackPaneInput.setVisible(false);
    }


    @FXML
    void btnOpsInsertPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
    }

    @FXML
    void btnOpsDeletePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
    }

    @FXML
    void btnOpsCreatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
    }

    @FXML
    void btnOpsUpdatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
    }

    @FXML
    void btnOpsTraversePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);
    }

    @FXML
    void radioBtnRandomPressed(ActionEvent event) {

    }

    @FXML
    void radioBtnManualPressed(ActionEvent event) {

    }

    @FXML
    void tfRootCreateTyping(ActionEvent event) {

    }

    @FXML
    void btnCreatePressed(ActionEvent event) {

    }

    @FXML
    void radioBtnBFSPressed(ActionEvent event) {

    }


















    @FXML
    void backPressed() throws IOException {
//        System.out.println("Back pressed"); for debugging
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 600, 600);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }

    @FXML
    void helpPressed() {

    }

    @FXML
    void mainLabelPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 600, 600);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }
}
