package com.example.treedatastructure;

import com.example.treedatastructure.exception.NodeExistedException;
import com.example.treedatastructure.exception.NodeFullChildrenException;
import com.example.treedatastructure.exception.NodeNotExistsException;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class genericTreeController {

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
    private StackPane stackPanePseudo;

    @FXML
    private StackPane stackPaneController;


    @FXML
    private String valRootNodeCreate;

    @FXML
    private Pane scenePane;

    @FXML
    private TextField tfNodeSearch;




    private Stage menuStage;

    private Scene mainScene;

    private GenericTree genericTree = new GenericTree();

    private String algorithm;

    public genericTreeController(Stage stage) {
        this.menuStage = stage;
    }

    @FXML
    private void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        stackPaneController.setVisible(false);
    }


    @FXML // done
    void btnOpsInsertPressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxInsert);

    }

    @FXML // done
    void btnOpsDeletePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxDelete);
    }

    @FXML //done
    void btnOpsCreatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxCreate);


    }

    @FXML // done
    void btnOpsUpdatePressed(ActionEvent event) {
        stackPaneInput.setVisible(true);

        setControl(hBoxUpdate);
    }

    @FXML // done
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
        String rootId = tfRootCreate.getText();
        int rootIdInt;
        if (rootId.equals("")){
            rootIdInt = 1;
        }
        else{
            rootIdInt = Integer.parseInt(rootId);
        }
        genericTree.createTree(rootIdInt);
        Node root = genericTree.getRootNode();

        scenePane.getChildren().add(root);
    }

    @FXML
    private void radioBtnBFSPressed(ActionEvent event) {}

    @FXML
    private void radioBtnDFSPressed(ActionEvent event) {}

    @FXML
    private void tfNodeTraverseTyping(ActionEvent event) {}

    @FXML
    private void btnTraversePressed(ActionEvent event) {}

    @FXML
    private void tfNodeDeleteTyping(ActionEvent event) {}

    @FXML
    private void btnDeletePressed(ActionEvent event) {}

    @FXML
    private void tfParentInsertTyping(ActionEvent event) {}

    @FXML
    private void tfNodeInsertTyping(ActionEvent event) {}

    @FXML
    private void btnInsertPressed(ActionEvent event) throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException {
        String node_val = tfNodeInsert.getText();
        String parent_val = tfParentInsert.getText();
        int intNodeVal = Integer.parseInt(node_val);;
        int intParentVal = Integer.parseInt(parent_val);
        try {
            Node childNode = genericTree.insertNode(intParentVal, intNodeVal);
            scenePane.getChildren().add(childNode.getParentLine());
            scenePane.getChildren().add(childNode);

        } catch (NodeNotExistsException | NodeExistedException | NodeFullChildrenException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like you have tried invalid insertion operation.");

            alert.showAndWait();
        }
        tfNodeInsert.clear();
        tfParentInsert.clear();
    }

    @FXML
    private void tfOldNodeUpdateTyping(ActionEvent event) {}

    @FXML
    private void tfNewNodeUpdateTyping(ActionEvent event) {}

    @FXML
    private void btnUpdatePressed(ActionEvent event) {}

    @FXML // will have animation, not implemented yet
    void btnSearchPressed(ActionEvent event) throws InterruptedException {
        String val_node = tfNodeSearch.getText();
        int intNodeVal = Integer.parseInt(val_node);

        Node nodeObject = genericTree.searchNode(intNodeVal);
        ArrayList<Node> search_direction = genericTree.getPathToRoot(nodeObject);
        Collections.reverse(search_direction);

        drawAnimations(search_direction);

    }

    private void drawAnimations(ArrayList<Node> list_nodes) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.setOnFinished(event -> turnOffAnimations(sequentialTransition));

        for (Node node : list_nodes) {

            Line connectedLine = node.getParentLine();

            Duration durationLine = Duration.seconds(1);
            Color fromLineColor = Color.BLACK;
            Color toLineColor = Color.GREEN;

            StrokeTransition strokeLineTransition = new StrokeTransition(durationLine, connectedLine, fromLineColor, toLineColor);
            strokeLineTransition.setAutoReverse(true);

            Duration durationNode = Duration.seconds(1);
            Color fromNodeColor = Color.BLACK;
            Color toNodeColor = Color.GREEN;

            StrokeTransition strokeNodeTransition = new StrokeTransition(durationNode, node.getCircle(), fromNodeColor, toNodeColor);
            strokeNodeTransition.setAutoReverse(true);

            sequentialTransition.getChildren().add(strokeLineTransition);
            sequentialTransition.getChildren().add(strokeNodeTransition);

        }

        sequentialTransition.play();

    }

    private void turnOffAnimations(SequentialTransition sequentialTransition) {
        sequentialTransition.stop();
//        for(Animation stroke: sequentialTransition.getChildren()) {
//            stroke.pause();
//        }
    }

    private void showPopupWindow(SequentialTransition sequentialTransition) {
        sequentialTransition.setOnFinished(null);
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Animation Finished");
                alert.setHeaderText(null);
                alert.setContentText("The animation has finished!");

                ButtonType okayButton = new ButtonType("Okay");
                alert.getButtonTypes().setAll(okayButton);

                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == okayButton) {
                        sequentialTransition.stop();
                        System.out.println("stop");
                    }
                });
            });
        });

        pauseTransition.play();
    }

    private ArrayList<Line> drawLineAnimation(ArrayList<Line> list_lines) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        ArrayList<Line> listAnimatedLines = new ArrayList<Line>();
        for (Line connectedLine : list_lines) {

            Line copiedLine = new Line();
            copiedLine.setStrokeWidth(2.0);
            copiedLine.setStartX(connectedLine.getStartX());
            copiedLine.setStartY(connectedLine.getStartY());
            copiedLine.setEndX(connectedLine.getEndX());
            copiedLine.setEndY(connectedLine.getEndY());

            Duration duration = Duration.seconds(2);
            Color fromColor = Color.BLACK;
            Color toColor = Color.GREEN;

            StrokeTransition strokeTransition = new StrokeTransition(duration, copiedLine, fromColor, toColor);
            strokeTransition.setAutoReverse(true);
            sequentialTransition.getChildren().add(strokeTransition);
            listAnimatedLines.add(copiedLine);
        }

        sequentialTransition.play();

        return listAnimatedLines;
    }


    private Line drawLineAnimation(Line connectedLine) {

        Line copiedLine = new Line();
        copiedLine.setStrokeWidth(2.0);
        copiedLine.setStartX(connectedLine.getStartX());
        copiedLine.setStartY(connectedLine.getStartY());
        copiedLine.setEndX(connectedLine.getEndX());
        copiedLine.setEndY(connectedLine.getEndY());
        System.out.println(connectedLine.getStartX() + "" + connectedLine.getStartY() + "" + connectedLine.getEndX() + "" + connectedLine.getEndY());
        Duration duration = Duration.seconds(3);
        Color fromColor = Color.BLACK;
        Color toColor = Color.LIGHTYELLOW;

        StrokeTransition strokeTransition = new StrokeTransition(duration, copiedLine, fromColor, toColor);
        strokeTransition.setAutoReverse(true);
        strokeTransition.play();

        return copiedLine;
    }

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
