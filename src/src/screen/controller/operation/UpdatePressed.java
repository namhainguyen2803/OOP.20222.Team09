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

public class UpdatePressed implements UserAction {
    private int intOldVal;
    private int intNewVal;
    private GenericTreeController genericTreeController;
    private GenericTree genericTree;
    private Pane scenePane;

    public UpdatePressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane, int intOldVal, int intNewVal) {
        this.genericTree = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.intNewVal = intNewVal;
        this.intOldVal = intOldVal;
    }

    @Override
    public void run() throws NodeExistedException, NodeNotExistsException {
        genericTree.checkUpdateNode(intOldVal, intNewVal);
        Node nodeObject = genericTree.searchNode(intOldVal);
        ArrayList<Node> search_direction = genericTree.getPathToRoot(nodeObject);
        search_direction.add(genericTree.getRootNode());
        Collections.reverse(search_direction);
        this.genericTreeController.drawAnimationsUpdate(search_direction, nodeObject, intNewVal);
        System.out.println("Update operation.");
    }

    @Override
    public void undo() {
        Node nodeObject = genericTree.searchNode(intNewVal);
        nodeObject.getTfId().setText(String.valueOf(intOldVal));
        nodeObject.updateId(intOldVal);
        System.out.println("Update operation undo.");
    }
}
