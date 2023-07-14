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

public class SearchPressed implements UserAction {
    private int intNodeVal;
    private GenericTreeController genericTreeController;
    private GenericTree genericTree;
    private Pane scenePane;

    public SearchPressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane, int nodeVal) {
        this.genericTree = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.intNodeVal = nodeVal;
    }

    @Override
    public void run() {
        Node nodeObject = genericTree.searchNode(intNodeVal);
        ArrayList<Node> search_direction = genericTree.getPathToRoot(nodeObject);
        search_direction.add(genericTree.getRootNode());
        Collections.reverse(search_direction);

        this.genericTreeController.drawAnimationsSearch(search_direction);
        System.out.println("Search operation.");
    }

    @Override
    public void undo() {
        // do nothing
        System.out.println("Search operation undo.");
    }
}
