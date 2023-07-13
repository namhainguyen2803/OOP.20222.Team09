package screen.controller;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import exception.NoneAlgorithmSpecifiedException;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import treedatastructure.GenericTree;
import treedatastructure.Node;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GenericTreeController {
    private GenericTree genericTree = new GenericTree();
    private String algorithm;
    /*
    FXML
     */
    @FXML
    private Rectangle recPseudoBFS1;
    @FXML
    private Rectangle recPseudoBFS2;
    @FXML
    private Rectangle recPseudoBFS3;
    @FXML
    private Rectangle recPseudoBFS4;
    @FXML
    private Rectangle recPseudoBFS5;
    @FXML
    private Pane scenePane;
    @FXML
    private Button btnOpsCreate;

    @FXML
    private Button btnOpsInsert;

    @FXML
    private Button btnOpsDelete;

    @FXML
    private Button btnOpsUpdate;

    @FXML
    private Button btnOpsTraverse;

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

    @FXML
    private RadioButton radioBtnBFS;

    @FXML
    private RadioButton radioBtnDFS;

    @FXML
    private TextField tfNodeTraverse;

    @FXML
    private Button btnTraverse;

    @FXML
    private HBox hBoxDelete;

    @FXML
    private TextField tfNodeDelete;

    @FXML
    private Button btnDelete;

    @FXML
    private HBox hBoxInsert;

    @FXML
    private HBox hBoxSearch;

    @FXML
    private TextField tfParentInsert;

    @FXML
    private TextField tfNodeInsert;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnSearch;

    @FXML
    private HBox hBoxUpdate;

    @FXML
    private TextField tfOldNodeUpdate;

    @FXML
    private TextField tfNewNodeUpdate;

    @FXML
    private Button btnUpdate;

    @FXML
    private VBox vBoxPseudo;

    @FXML
    private VBox vBoxBFS;

    @FXML
    private VBox vBoxDFS;

    @FXML
    private StackPane stackPanePseudo;

    @FXML
    private StackPane stackPaneController;

    private Stage menuStage;

    public GenericTreeController(Stage stage) {
        this.menuStage = stage;
    }

    public Rectangle getRecPseudoBFS1() {
        return recPseudoBFS1;
    }

    public Rectangle getRecPseudoBFS2() {
        return recPseudoBFS2;
    }

    public Rectangle getRecPseudoBFS3() {
        return recPseudoBFS3;
    }

    public Rectangle getRecPseudoBFS4() {
        return recPseudoBFS4;
    }

    public Rectangle getRecPseudoBFS5() {
        return recPseudoBFS5;
    }

    @FXML
    private void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        stackPaneController.setVisible(false);
    }


    @FXML
    void btnOpsInsertPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxInsert);

    }

    @FXML
    void btnOpsDeletePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxDelete);
    }

    @FXML
    void btnOpsCreatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxCreate);
    }

    @FXML
    void btnOpsUpdatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxUpdate);
    }

    @FXML
    void btnOpsTraversePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxTraverse);
    }

    @FXML
    void btnOpsSearchPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxSearch);
    }

    @FXML
    private void radioBtnRandomPressed(ActionEvent event) {
        hBoxManual.setVisible(false);
    }

    @FXML
    private void radioBtnManualPressed(ActionEvent event) {
        hBoxManual.setVisible(true);
    }

    @FXML
    private void tfRootCreateTyping(ActionEvent event) {}

    @FXML
    private void btnCreatePressed(ActionEvent event) {
        //Get the ID
        String rootId = tfRootCreate.getText();
        int rootIdInt;
        if (rootId.equals("")){
            rootIdInt = 1;
        }
        else{
            rootIdInt = Integer.parseInt(rootId);
        }

        // Add node in genericTree and in scencePane
        genericTree.createTree(rootIdInt);
        genericTree.setTreeController(this);
        Node root = genericTree.getRootNode();
        root.setLayoutX(scenePane.getPrefWidth()*1.5);
        root.setLayoutY(scenePane.getPrefHeight()/4);
        scenePane.getChildren().add(root);

        // Ở đây add root nên sẽ không có parentLine. Về sau addChild thì nhớ thêm parentLine
    }

    @FXML
    private void radioBtnBFSPressed(ActionEvent event) {
        algorithm = "BFS";
    }

    @FXML
    private void radioBtnDFSPressed(ActionEvent event) {
        algorithm = "DFS";
    }

    @FXML
    private void tfNodeTraverseTyping(ActionEvent event) {}

    @FXML
    private void btnTraversePressed(ActionEvent event) {
        try {
            stackPanePseudo.setVisible(true);
            if (algorithm.equals("BFS")){
                vBoxBFS.setVisible(true);
                vBoxDFS.setVisible(false);
            }
            else{
                vBoxDFS.setVisible(true);
                vBoxBFS.setVisible(false);
            }
            stackPaneController.setVisible(true);
            genericTree.traverseTree(algorithm);
        } catch (NoneAlgorithmSpecifiedException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @FXML
    private void tfNodeDeleteTyping(ActionEvent event) {}

    @FXML
    private void btnDeletePressed(ActionEvent event) {}

    @FXML
    private void tfParentInsertTyping(ActionEvent event) {}

    @FXML
    private void tfNodeInsertTyping(ActionEvent event) {}

    @FXML
    private void btnInsertPressed(ActionEvent event) {
        //Get parentId and childId
        int parentId = Integer.parseInt(tfParentInsert.getText());
        int childId = Integer.parseInt(tfNodeInsert.getText());

        //Insert vào genericTree
        try {
            genericTree.insertNode(parentId, childId);
        } catch (NodeNotExistsException | NodeExistedException | NodeFullChildrenException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //FXML: Add vào Pane
        Node child = genericTree.searchNode(childId);
        scenePane.getChildren().add(child);
        scenePane.getChildren().add(child.getParentLine());
    }

    @FXML
    private void tfOldNodeUpdateTyping(ActionEvent event) {}

    @FXML
    private void tfNewNodeUpdateTyping(ActionEvent event) {}

    @FXML
    private void btnUpdatePressed(ActionEvent event) {}

    @FXML
    void btnSearchPressed(ActionEvent event) {}

    @FXML
    void tfNodeSearchTyping(ActionEvent event) {}

    private void setControl(HBox hBoxOn) {
        ArrayList<HBox> setHBox = new ArrayList<HBox>(Arrays.asList(hBoxCreate, hBoxTraverse, hBoxInsert, hBoxDelete, hBoxUpdate, hBoxSearch));
        for (HBox hbox: setHBox) {
            if (hBoxOn.equals(hbox)) {
                hbox.setVisible(true);
            }
            else {
                hbox.setVisible(false);
            }
        }
    }
    @FXML
    void backPressed() throws IOException {
//        System.out.println("Back pressed"); for debugging
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/mainWindow.fxml"));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/screen/fxml/mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 600, 600);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }
}
