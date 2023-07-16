package src.screen.controller.operation;

import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import src.screen.controller.GenericTreeController;
import src.treedatastructure.*;

import java.util.ArrayList;
import java.util.Collections;

public class InsertPressed implements UserAction {
    private int nodeVal;
    private int parentval;

    private Node parent;
    private GenericTreeController genericTreeController;
    private GenericTree genericTree;
    private Pane scenePane;

    public InsertPressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane, int nodeVal, int parentval) {
        this.genericTree = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.nodeVal = nodeVal;
        this.parentval = parentval;
    }

    @Override
    public void run() {

        Node nodeObject = this.genericTree.searchNode(this.parentval);
        this.parent = nodeObject;
        ArrayList<Node> search_direction = this.genericTree.getPathToRoot(nodeObject);
        search_direction.add(this.genericTree.getRootNode());
        Collections.reverse(search_direction);
        drawAnimationsInsert(search_direction, parentval, nodeVal);
        System.out.println("Insert operation.");

    }

    @Override
    public void undo() {
        Node insertedObject = this.genericTree.searchNode(this.nodeVal);
        this.parent.getListOfChildren().remove(insertedObject);
        this.scenePane.getChildren().remove(insertedObject);
        this.scenePane.getChildren().remove(insertedObject.getParentLine());
        this.genericTreeController.rebuildTree();
        System.out.println("Insert operation undo.");
    }

    public void drawAnimationsInsert(ArrayList<Node> search_direction, int intParentVal, int intNodeVal) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = genericTreeController.drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(event -> turnOffAnimationsInsert(listLines, listStackPane, intParentVal, intNodeVal));
        seq.play();
    }

    private void turnOffAnimationsInsert(ArrayList<Line> listLines, ArrayList<StackPane> listPanes, int intParentVal, int intNodeVal) {
        try {
            Node childNode = this.genericTree.insertNode(intParentVal, intNodeVal);
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
}
