package src.screen.controller;

import src.screen.controller.operation.*;
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
import java.util.*;

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

    @FXML
    private Label mainLabel;



    private Stage menuStage;

    private Scene mainScene;

    private GenericTree genericTree = new GenericTree();

    private String algorithm;

    private String treeType;

    private ArrayList<UserAction> history = new ArrayList<UserAction>();

    public GenericTreeController(Stage stage, String treeType) {
        this.menuStage = stage;
        this.treeType = treeType;
    }

    @FXML
    private void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        stackPaneController.setVisible(false);
        mainLabel.setText(this.treeType);
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
        CreatePressed createPressed;
        if (radioBtnManual.isSelected()) {
            createPressed = new CreatePressed(this, genericTree, scenePane, tfRootCreate.getText());
        }
        else {
            createPressed = new CreatePressed(this, genericTree, scenePane);
        }
        createPressed.run();
        history.add((UserAction) createPressed);
        tfRootCreate.clear();
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
    private void tfNodeDeleteTyping(ActionEvent event) {
        this.btnDeletePressed(event);
    }


    @FXML
    private void btnDeletePressed(ActionEvent event) {
        String delNodeVal = tfNodeDelete.getText();

        int intDelNodeVal = Integer.parseInt(delNodeVal);
        try {
            DeletePressed deletePressed = new DeletePressed(genericTree, scenePane, this, intDelNodeVal);
            deletePressed.run();
            history.add(deletePressed);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you want to delete does not exist.");

            alert.showAndWait();
        }
        tfNodeDelete.clear();
    }


    @FXML
    private void tfParentInsertTyping(ActionEvent event) {}

    @FXML
    private void tfNodeInsertTyping(ActionEvent event) {}

    @FXML
    private void btnInsertPressed(ActionEvent event) throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException {

        try {

            String node_val = tfNodeInsert.getText();
            String parent_val = tfParentInsert.getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);

            genericTree.checkInsertNode(intParentVal, intNodeVal);

            InsertPressed insertPressed = new InsertPressed(genericTree, this, scenePane, intNodeVal, intParentVal);

            insertPressed.run();

            history.add(insertPressed);
        }

        catch (NodeNotExistsException | NodeExistedException | NodeFullChildrenException e){
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
    private void btnUpdatePressed(ActionEvent event) {
        String new_val = tfNewNodeUpdate.getText();
        String old_val = tfOldNodeUpdate.getText();

        int intNewVal = Integer.parseInt(new_val);
        int intOldVal = Integer.parseInt(old_val);

        try {
            UpdatePressed updatePressed = new UpdatePressed(genericTree, this, scenePane, intOldVal, intNewVal);
            updatePressed.run();

            history.add(updatePressed);

        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like old node does not exist.");

            alert.showAndWait();
        } catch (NodeExistedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like new node have existed.");

            alert.showAndWait();
        }
        tfNewNodeUpdate.clear();
        tfOldNodeUpdate.clear();
    }

    @FXML
    void btnSearchPressed(ActionEvent event) throws InterruptedException {
        String val_node = tfNodeSearch.getText();
        int intNodeVal = Integer.parseInt(val_node);

        try {
            SearchPressed searchPressed = new SearchPressed(genericTree, this, scenePane, intNodeVal);
            searchPressed.run();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you are searching for does not exist.");

            alert.showAndWait();
        }
        tfNodeSearch.clear();
    }

    private SequentialTransition drawAnimations(ArrayList<Node> list_nodes, ArrayList<Line> listLines, ArrayList<StackPane> listStackPane) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (Node node : list_nodes) {

            Line connectedLine = node.getParentLine();
            Duration durationLine = Duration.seconds(1);
            Color fromLineColor = node.getColorStrokeLine();
            Color toLineColor = node.getColorFontText();
            Line copiedLine = new Line(connectedLine.getStartX(), connectedLine.getStartY(), connectedLine.getEndX(), connectedLine.getEndY());
            copiedLine.setStrokeWidth(connectedLine.getStrokeWidth());
            StrokeTransition strokeLineTransition = new StrokeTransition(durationLine, copiedLine, fromLineColor, toLineColor);
            strokeLineTransition.setAutoReverse(true);

            Duration durationNode = Duration.seconds(1);
            Color fromNodeColor = node.getColorStrokeCircle();
            Color toNodeColor = node.getColorFontText();
            Circle copied_circle = new Circle(node.getCircle().getRadius(), node.getColorCircle());
            copied_circle.setStrokeWidth(node.getStrokeWidthCircle());
            copied_circle.setCenterX(node.getLayoutX() + node.getCircleRadius());
            copied_circle.setCenterY(node.getLayoutY()+ node.getCircleRadius());
//            System.out.println(copied_circle.getCenterX() + " " + copied_circle.getCenterY() + " " + copied_circle.getRadius());
            StrokeTransition strokeNodeTransition = new StrokeTransition(durationNode, copied_circle, fromNodeColor, toNodeColor);
            strokeNodeTransition.setAutoReverse(true);

            Text copiedText = new Text(String.valueOf(node.getNodeId()));
            Duration durationText = Duration.seconds(0.1);
            copiedText.setStrokeWidth(node.getStrokeWidthText());
            copiedText.setStroke(node.getColorStrokeText());
            copiedText.setFill(node.getColorFontText());
            StrokeTransition strokeTextTransition = new StrokeTransition(durationText, copiedText, fromNodeColor, node.getColorFontText());
            strokeTextTransition.setAutoReverse(true);

            StackPane tmpPane = new StackPane();
            tmpPane.getChildren().add(copied_circle);
            tmpPane.getChildren().add(copiedText);

            tmpPane.setLayoutX(node.getLayoutX());
            tmpPane.setLayoutY(node.getLayoutY());

            sequentialTransition.getChildren().add(strokeLineTransition);
            sequentialTransition.getChildren().add(strokeNodeTransition);
            sequentialTransition.getChildren().add(strokeTextTransition);

            scenePane.getChildren().add(copiedLine);
            scenePane.getChildren().add(tmpPane);

            listLines.add(copiedLine);
            listStackPane.add(tmpPane);

        }

        return sequentialTransition;

    }

    public void drawAnimationsSearch(ArrayList<Node> search_direction) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(e -> showPopupWindowSearch(listLines, listStackPane));
        seq.play();
    }

    public void drawAnimationsUpdate(ArrayList<Node> search_direction, Node oldNode, int newNodeVal) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(event -> turnOffAnimationsUpdate(listLines, listStackPane, oldNode, newNodeVal));
        seq.play();
    }

    public void drawAnimationsInsert(ArrayList<Node> search_direction, int intParentVal, int intNodeVal) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(event -> turnOffAnimationsInsert(listLines, listStackPane, intParentVal, intNodeVal));
        seq.play();
    }

    public void drawAnimationsDelete(ArrayList<Node> search_direction, Node delNode) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(event -> turnOffAnimationsDelete(listLines, listStackPane, delNode));
        seq.play();
    }


    private void turnOffAnimationsInsert(ArrayList<Line> listLines, ArrayList<StackPane> listPanes, int intParentVal, int intNodeVal) {
        try {
            Node parent = genericTree.searchNode(intParentVal);
            Node childNode = parent.addChild(intNodeVal);
            int secondsToSleep = 1;
            long millisecondsToSleep = secondsToSleep * 1000;
            scenePane.getChildren().add(childNode.getParentLine());
            scenePane.getChildren().add(childNode);
            Thread.sleep(millisecondsToSleep);
            scenePane.getChildren().removeAll(listLines);
            scenePane.getChildren().removeAll(listPanes);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void turnOffAnimationsUpdate(ArrayList<Line> listLines, ArrayList<StackPane> listPanes, Node childNode, int newNodeVal) {
        try {
            int secondsToSleep = 1;
            long millisecondsToSleep = secondsToSleep * 1000;

            childNode.getTfId().setText(String.valueOf(newNodeVal));
            childNode.updateId(newNodeVal);
            Thread.sleep(millisecondsToSleep);
            scenePane.getChildren().removeAll(listLines);
            scenePane.getChildren().removeAll(listPanes);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void rebuildTree() {
        Node root = genericTree.getRootNode();

        scenePane.getChildren().remove(root);
        ArrayList<Node> listNodeDel = new ArrayList<Node>(root.getListOfChildren());
        while (listNodeDel.size() > 0) {
            Node tmp = listNodeDel.remove(0);
            if (tmp.getListOfChildren().size() > 0){
                listNodeDel.addAll(tmp.getListOfChildren());
            }
            scenePane.getChildren().remove(tmp);
            scenePane.getChildren().remove(tmp.getParentLine());
        }

        scenePane.getChildren().add(root);
        ArrayList<Node> listNode = new ArrayList<Node>();
        listNode.add(root);
        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0){
                ArrayList<Node> tmpListNode = new ArrayList<Node>(tmp.getListOfChildren());
                tmp.getListOfChildren().removeAll(tmp.getListOfChildren());
                for (Node childNode: tmpListNode) {
                    tmp.addChild(childNode);
                    listNode.add(childNode);
                }
            }

            if (!tmp.equals(root)) {
                scenePane.getChildren().add(tmp);
                scenePane.getChildren().add(tmp.getParentLine());
            }
        }
    }

    private void deleteSubtree(Node root) {
        scenePane.getChildren().remove(root);
        if (!root.equals(genericTree.getRootNode())) {
            scenePane.getChildren().remove(root.getParentLine());
            root.getParentNode().getListOfChildren().remove(root);
        }

        ArrayList<Node> listNode = new ArrayList<Node>(root.getListOfChildren());
        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0){
                listNode.addAll(tmp.getListOfChildren());
            }
            tmp.getParentNode().getListOfChildren().remove(tmp);
            tmp.setId(null);
            scenePane.getChildren().remove(tmp);
            scenePane.getChildren().remove(tmp.getParentLine());
        }
    }

    private void turnOffAnimationsDelete(ArrayList<Line> listLines, ArrayList<StackPane> listPanes, Node delRootNode) {
        try {
            int secondsToSleep = 1;
            long millisecondsToSleep = secondsToSleep * 1000;

            deleteSubtree(delRootNode);

            Thread.sleep(millisecondsToSleep);
            scenePane.getChildren().removeAll(listLines);
            scenePane.getChildren().removeAll(listPanes);
            rebuildTree();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void showPopupWindowSearch(ArrayList<Line> listLines, ArrayList<StackPane> listPane) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complete Searching Operation");
                alert.setHeaderText(null);
                alert.setContentText("Node required searching has been found.");

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }

    @FXML
    void helpPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/help.fxml"));
        HelpController helpController = new HelpController();
        loader.setController(helpController);
        Stage helpStage = new Stage();
        Scene scene = new Scene(loader.load());
        helpStage.setScene(scene);
        helpStage.setTitle("Help");
        helpStage.initOwner(menuStage);
        helpStage.showAndWait();
    }

    @FXML
    void undoPressed() {
        if (history.size() > 0) {
            UserAction lastAction = history.remove(history.size() - 1);
            lastAction.undo();
        }
    }

    @FXML
    public void resetPressed() {
        if (genericTree.getRootNode() != null) {
            this.deleteSubtree(genericTree.getRootNode());
        }
    }

    @FXML
    void mainLabelPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }
}