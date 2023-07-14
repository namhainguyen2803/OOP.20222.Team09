package src.screen.controller.operation;

import javafx.scene.layout.Pane;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.screen.controller.GenericTreeController;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

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
        this.genericTreeController.drawAnimationsInsert(search_direction, parentval, nodeVal);
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
}
