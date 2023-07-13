package src.screen.controller;

import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.exception.NoneAlgorithmSpecifiedException;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GenericTreeController {

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
    private VBox vBoxBFS;

    @FXML
    private VBox vBoxDFS;

    @FXML
    private RadioButton radioBtnManual;

    @FXML
    private RadioButton radioBtnRandom;



    private Stage menuStage;

    private Scene mainScene;

    private GenericTree genericTree = new GenericTree();

    private String algorithm;

    public GenericTreeController(Stage stage) {
        this.menuStage = stage;
    }

    @FXML
    private void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        stackPaneController.setVisible(false);
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
    private void btnCreatePressed(ActionEvent event) throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException {

        if (radioBtnManual.isSelected()) {
            String rootId = tfRootCreate.getText();
            int rootIdInt;
            if (rootId.equals("")){
                rootIdInt = 1;
            }
            else{
                rootIdInt = Integer.parseInt(rootId);
            }
            genericTree.createTree(rootIdInt);
            genericTree.setTreeController(this);
            Node root = genericTree.getRootNode();
            scenePane.getChildren().add(root);
        }
        else {
            Random randint = new Random();
            int numNodes = randint.nextInt(6);
//            System.out.println(numNodes);
            ArrayList<Integer> listValNodes = new ArrayList<Integer>();
            for (int i = 0; i < numNodes; i++) {
                int newVal = randint.nextInt(10);
                while (listValNodes.contains(newVal)) {
                    newVal = randint.nextInt(10);
                }
//                System.out.println(newVal);
                listValNodes.add(newVal);
            }
            // set root node
            Node root = new Node(listValNodes.get(0));
            genericTree.setTreeController(this);
            genericTree.setRootNode(root);
            scenePane.getChildren().add(root);

            for (int i = 1; i < numNodes; i++) {
                int parentDecision = randint.nextInt(i);
                Node childNode = genericTree.insertNode(listValNodes.get(parentDecision), listValNodes.get(i));
                scenePane.getChildren().add(childNode.getParentLine());
                scenePane.getChildren().add(childNode);
            }
        }
    }

    @FXML
    private void radioBtnBFSPressed(ActionEvent event) {
        this.algorithm = "BFS";
    }

    @FXML
    private void radioBtnDFSPressed(ActionEvent event) {
        this.algorithm = "DFS";
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

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Invalid algorithm name.");

            alert.showAndWait();
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
    private void btnInsertPressed(ActionEvent event) throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException {

        String node_val = tfNodeInsert.getText();
        String parent_val = tfParentInsert.getText();
        int intNodeVal = Integer.parseInt(node_val);
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
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        sequentialTransition.setOnFinished(event -> showPopupWindow(listLines, listStackPane));

        for (Node node : list_nodes) {

            Line connectedLine = node.getParentLine();
            Duration durationLine = Duration.seconds(1);
            Color fromLineColor = Color.BLACK;
            Color toLineColor = Color.WHITE;
            Line copiedLine = new Line(connectedLine.getStartX(), connectedLine.getStartY(), connectedLine.getEndX(), connectedLine.getEndY());
            copiedLine.setStrokeWidth(3.0);
            StrokeTransition strokeLineTransition = new StrokeTransition(durationLine, copiedLine, fromLineColor, toLineColor);
            strokeLineTransition.setAutoReverse(true);

            Duration durationNode = Duration.seconds(1);
            Color fromNodeColor = Color.BLACK;
            Color toNodeColor = Color.GREEN;
            Circle copied_circle = new Circle(node.getCircle().getRadius(), Color.LIGHTPINK);
            copied_circle.setCenterX(node.getLayoutX() + node.cirleRadius);
            copied_circle.setCenterY(node.getLayoutY()+ node.cirleRadius);
//            System.out.println(copied_circle.getCenterX() + " " + copied_circle.getCenterY() + " " + copied_circle.getRadius());
            StrokeTransition strokeNodeTransition = new StrokeTransition(durationNode, copied_circle, fromNodeColor, toNodeColor);
            strokeNodeTransition.setAutoReverse(true);

            Text copiedText = new Text(node.getNodeId() +"");

            StackPane tmpPane = new StackPane();
            tmpPane.getChildren().add(copied_circle);
            tmpPane.getChildren().add(copiedText);

            tmpPane.setLayoutX(node.getLayoutX());
            tmpPane.setLayoutY(node.getLayoutY());

            sequentialTransition.getChildren().add(strokeLineTransition);
            sequentialTransition.getChildren().add(strokeNodeTransition);

            scenePane.getChildren().add(copiedLine);
            scenePane.getChildren().add(tmpPane);

            listLines.add(copiedLine);
            listStackPane.add(tmpPane);

        }

        sequentialTransition.play();

    }

    private void turnOffAnimations(ArrayList<Line> listLines, ArrayList<Circle> listCircles) {
        scenePane.getChildren().removeAll(listLines);
        scenePane.getChildren().removeAll(listCircles);
    }

    private void showPopupWindow(ArrayList<Line> listLines, ArrayList<StackPane> listPane) {
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
                        scenePane.getChildren().removeAll(listLines);
                        scenePane.getChildren().removeAll(listPane);
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
