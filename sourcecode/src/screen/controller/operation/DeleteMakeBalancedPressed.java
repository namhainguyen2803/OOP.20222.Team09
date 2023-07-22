package src.screen.controller.operation;

import javafx.scene.layout.Pane;
import src.screen.controller.BalancedTreeController;
import src.screen.controller.GenericTreeController;
import src.treedatastructure.BalancedTree;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

public class DeleteMakeBalancedPressed implements UserAction{
    private GenericTreeController genericTreeController;
    private GenericTree genericTree;
    private Node oldRootNode;

    private int delId;

    public DeleteMakeBalancedPressed(GenericTreeController genericTreeController, GenericTree genericTree, int delId){
        this.genericTreeController = genericTreeController;
        this.genericTree = genericTree;
        this.oldRootNode = genericTree.getRootNode();
        this.delId = delId;
    }

    @Override
    public void run() {
        BalancedTreeController balancedTreeController = (BalancedTreeController) genericTreeController;
        balancedTreeController.removeTreeFromGUI();
        BalancedTree balancedTree = (BalancedTree) genericTree;
        balancedTree.makeBalanceDelete(delId);
    }

    @Override
    public void undo() {
        BalancedTreeController balancedTreeController = (BalancedTreeController) genericTreeController;
        balancedTreeController.removeTreeFromGUI();

        BalancedTree balancedTree = (BalancedTree) genericTree;
        balancedTree.setRootNode(null);
        balancedTree.setRootNode(oldRootNode);

        balancedTreeController.buildGUI(oldRootNode);
    }
}
