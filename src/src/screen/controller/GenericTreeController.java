package src.screen.controller;

import src.exception.*;
import src.screen.controller.operation.*;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;
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
    private Rectangle recPseudoDFS1;

    @FXML
    private Rectangle recPseudoDFS2;

    @FXML
    private Rectangle recPseudoDFS3;

    @FXML
    private Rectangle recPseudoDFS4;

    @FXML
    private Rectangle recPseudoDFS5;

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

    private String algorithm;
    @FXML
    private Button forwardTraverseBtn;
    @FXML
    private Button backwardTraverseBtn;
    @FXML
    private Button pauseTraverseBtn;
    @FXML
    private Button continueTraverseBtn;
    @FXML
    private Button okTraverseBtn;


    private ArrayList<UserAction> history = new ArrayList<UserAction>();


    private String treeType;

    private GenericTree treeDataStructure;

    public GenericTree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    public GenericTreeController(Stage stage, String treeType) {
        this.menuStage = stage;
        this.setTreeType(treeType);
        this.setTreeDataStructure(new GenericTree());
    }
    @FXML
    protected void initialize() {
        stackPaneInput.setVisible(false);
        stackPanePseudo.setVisible(false);
        stackPaneController.setVisible(false);
        mainLabel.setText(this.getTreeType());
    }

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public void setTreeDataStructure (GenericTree tree) {
        this.treeDataStructure = tree;
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

    public Rectangle getRecPseudoDFS1() {
        return recPseudoDFS1;
    }

    public Rectangle getRecPseudoDFS2() {
        return recPseudoDFS2;
    }

    public Rectangle getRecPseudoDFS3() {
        return recPseudoDFS3;
    }

    public Rectangle getRecPseudoDFS4() {
        return recPseudoDFS4;
    }

    public Rectangle getRecPseudoDFS5() {
        return recPseudoDFS5;
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
    void radioBtnRandomPressed(ActionEvent event) {
        hBoxManual.setVisible(false);
    }

    @FXML
    void radioBtnManualPressed(ActionEvent event) {
        hBoxManual.setVisible(true);
    }

    @FXML
    void tfRootCreateTyping(ActionEvent event) {}


    public Button getBtnOpsCreate() {
        return btnOpsCreate;
    }

    public Button getBtnOpsInsert() {
        return btnOpsInsert;
    }

    public Button getBtnOpsDelete() {
        return btnOpsDelete;
    }

    public Button getBtnOpsUpdate() {
        return btnOpsUpdate;
    }

    public Button getBtnOpsTraverse() {
        return btnOpsTraverse;
    }

    public StackPane getStackPaneInput() {
        return stackPaneInput;
    }

    public HBox gethBoxCreate() {
        return hBoxCreate;
    }

    public HBox gethBoxManual() {
        return hBoxManual;
    }

    public TextField getTfRootCreate() {
        return tfRootCreate;
    }

    public Button getBtnCreate() {
        return btnCreate;
    }

    public HBox gethBoxTraverse() {
        return hBoxTraverse;
    }

    public RadioButton getRadioBtnBFS() {
        return radioBtnBFS;
    }

    public RadioButton getRadioBtnDFS() {
        return radioBtnDFS;
    }

    public TextField getTfNodeTraverse() {
        return tfNodeTraverse;
    }

    public Button getBtnTraverse() {
        return btnTraverse;
    }

    public HBox gethBoxDelete() {
        return hBoxDelete;
    }

    public TextField getTfNodeDelete() {
        return tfNodeDelete;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public HBox gethBoxInsert() {
        return hBoxInsert;
    }

    public HBox gethBoxSearch() {
        return hBoxSearch;
    }

    public TextField getTfParentInsert() {
        return tfParentInsert;
    }

    public TextField getTfNodeInsert() {
        return tfNodeInsert;
    }

    public Button getBtnInsert() {
        return btnInsert;
    }

    public Button getBtnSearch() {
        return btnSearch;
    }

    public HBox gethBoxUpdate() {
        return hBoxUpdate;
    }

    public TextField getTfOldNodeUpdate() {
        return tfOldNodeUpdate;
    }

    public TextField getTfNewNodeUpdate() {
        return tfNewNodeUpdate;
    }

    public Button getBtnUpdate() {
        return btnUpdate;
    }

    public VBox getvBoxPseudo() {
        return vBoxPseudo;
    }

    public StackPane getStackPanePseudo() {
        return stackPanePseudo;
    }

    public StackPane getStackPaneController() {
        return stackPaneController;
    }

    public String getValRootNodeCreate() {
        return valRootNodeCreate;
    }

    public Pane getScenePane() {
        return scenePane;
    }

    public TextField getTfNodeSearch() {
        return tfNodeSearch;
    }

    public VBox getvBoxBFS() {
        return vBoxBFS;
    }

    public VBox getvBoxDFS() {
        return vBoxDFS;
    }

    public RadioButton getRadioBtnManual() {
        return radioBtnManual;
    }

    public RadioButton getRadioBtnRandom() {
        return radioBtnRandom;
    }

    public Label getMainLabel() {
        return mainLabel;
    }

    public Stage getMenuStage() {
        return menuStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public ArrayList<UserAction> getHistory() {
        return history;
    }

    @FXML
    protected void btnCreatePressed(ActionEvent event) {
        this.resetPressed();
        CreatePressed createPressed;
        if (radioBtnManual.isSelected()) {
            createPressed = new CreatePressed(this, this.getTreeDataStructure(), scenePane, tfRootCreate.getText());
        }
        else {
            createPressed = new CreatePressed(this, this.getTreeDataStructure(), scenePane);
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
            this.getTreeDataStructure().traverseTree(algorithm);
        } catch (NoneAlgorithmSpecifiedException e){

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Invalid algorithm name.");

            alert.showAndWait();
        }
    }

    @FXML
    protected void forwardTraverseBtnPressed(ActionEvent e){
        if (algorithm.equals("BFS")) {
            this.getTreeDataStructure().forwardBFS1Step();
        }
        else{
            this.getTreeDataStructure().forwardDFS1Step();
        }
    }

    @FXML
    protected void backwardTraverseBtnPressed(ActionEvent e){
        if (algorithm.equals("BFS")) {
            this.getTreeDataStructure().backwardBFS1Step();
        }
        else{
            this.getTreeDataStructure().backwardDFS1Step();
        }

    }
    @FXML
    protected void pauseTraverseBtnPressed(ActionEvent e){
        this.getTreeDataStructure().pauseTraverse();
    }
    @FXML
    protected void continueTraverseBtnPressed(ActionEvent e){
        this.getTreeDataStructure().continueTraverse();
    }

    @FXML
    public void okTraverseBtnPressed(ActionEvent e){
        this.getTreeDataStructure().okTraverse();
    }

    @FXML
    private void tfNodeDeleteTyping(ActionEvent event) {}


    @FXML
    protected void btnDeletePressed(ActionEvent event) throws TreeException {
        String delNodeVal = tfNodeDelete.getText();

        int intDelNodeVal = Integer.parseInt(delNodeVal);
        try {
            this.getTreeDataStructure().checkDeleteNode(intDelNodeVal);
            DeletePressed deletePressed = new DeletePressed((GenericTree) this.getTreeDataStructure(), scenePane, this, intDelNodeVal);
            deletePressed.run();
            history.add(deletePressed);

        } catch (NodeNotExistsException e) {
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
    private void tfOldNodeUpdateTyping(ActionEvent event) {}

    @FXML
    private void tfNewNodeUpdateTyping(ActionEvent event) {}

    @FXML
    protected void btnInsertPressed(ActionEvent event) throws TreeException {

        try {

            String node_val = tfNodeInsert.getText();
            String parent_val = tfParentInsert.getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);

            this.getTreeDataStructure().checkInsertNode(intParentVal, intNodeVal);

            InsertPressed insertPressed = new InsertPressed((GenericTree) this.getTreeDataStructure(), this, scenePane, intNodeVal, intParentVal);

            insertPressed.run();

            history.add(insertPressed);
        }

        catch (NodeExistedException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like you have tried invalid insertion operation.");

            alert.showAndWait();
        }

        catch (NodeNotExistsException e){
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
    protected void btnUpdatePressed(ActionEvent event) throws TreeException {
        String new_val = tfNewNodeUpdate.getText();
        String old_val = tfOldNodeUpdate.getText();

        int intNewVal = Integer.parseInt(new_val);
        int intOldVal = Integer.parseInt(old_val);

        try {
            this.getTreeDataStructure().checkUpdateNode(intOldVal, intNewVal);

            UpdatePressed updatePressed = new UpdatePressed((GenericTree) this.getTreeDataStructure(), this, scenePane, intOldVal, intNewVal);
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
    protected void btnSearchPressed(ActionEvent event) throws TreeException {
        String val_node = tfNodeSearch.getText();
        int intNodeVal = Integer.parseInt(val_node);

        try {
            this.getTreeDataStructure().checkNodeExisted(intNodeVal);
            SearchPressed searchPressed = new SearchPressed((GenericTree) this.getTreeDataStructure(), this, scenePane, intNodeVal);
            searchPressed.run();
        }
        catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you are searching for does not exist.");
            alert.showAndWait();
        }
        tfNodeSearch.clear();
    }

    public SequentialTransition drawAnimations(ArrayList<Node> list_nodes, ArrayList<Line> listLines, ArrayList<StackPane> listStackPane) {
        SequentialTransition sequentialTransition = new SequentialTransition();

        for (Node node : list_nodes) {

            Line connectedLine = node.getParentLine();
            Duration durationLine = Duration.seconds(0.1);
            Color fromLineColor = node.getColorStrokeLine();
            Color toLineColor = node.getColorFontText();
            Line copiedLine = new Line(connectedLine.getStartX(), connectedLine.getStartY(), connectedLine.getEndX(), connectedLine.getEndY());
            copiedLine.setStrokeWidth(connectedLine.getStrokeWidth());
            StrokeTransition strokeLineTransition = new StrokeTransition(durationLine, copiedLine, fromLineColor, toLineColor);
            strokeLineTransition.setAutoReverse(true);

            Duration durationNode = Duration.seconds(0.1);
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


    public void buildGUI(Node root) {
        scenePane.getChildren().add(root);
        ArrayList<Node> listNode = new ArrayList<Node>();
        listNode.add(root);

        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0){
                listNode.addAll(tmp.getListOfChildren());
            }
            if (!tmp.equals(root)) {
                scenePane.getChildren().add(tmp);
                scenePane.getChildren().add(tmp.getParentLine());
            }
        }
    }




    public void rebuildTree() {
        Node root = this.getTreeDataStructure().getRootNode();

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

    public void deleteSubtree(Node root) {
        scenePane.getChildren().remove(root);
        if (!root.equals(this.getTreeDataStructure().getRootNode())) {
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
    public void backPressed() throws IOException {
//        System.out.println("Back pressed"); for debugging
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setFullScreen(true);
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
    public void undoPressed() {
        if (history.size() > 0) {
            UserAction lastAction = history.remove(history.size() - 1);
            lastAction.undo();
        }
    }

    @FXML
    public void resetPressed() {
        if (this.getTreeDataStructure().getRootNode() != null) {
            this.deleteSubtree(this.getTreeDataStructure().getRootNode());
        }
    }

    @FXML
    public void mainLabelPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/mainWindow.fxml"));
        mainWindowController mainController = new mainWindowController();
        loader.setController(mainController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.menuStage.setTitle("Tree View Visualizer");
        this.menuStage.setFullScreen(true);
        this.menuStage.setScene(scene);
        this.menuStage.show();
    }
}


