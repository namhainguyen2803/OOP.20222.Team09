package src.screen.controller.operation;

import javafx.animation.SequentialTransition;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.screen.controller.GenericTreeController;
//import src.treedatastructure.BalancedBinaryTree;
//import src.treedatastructure.BalancedTree;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DeletePressed implements UserAction {

    private GenericTreeController genericTreeController;
    private int intDelNodeVal;
    private Node parentDelNode;
    private GenericTree genericTree;
    private Pane scenePane;
    private HashMap<Integer, ArrayList<Integer>> listDelNode = new HashMap<>();

    public DeletePressed(GenericTree genericTree, Pane scenePane, GenericTreeController genericTreeController, int intDelNodeVal) {
        this.genericTreeController = genericTreeController;
        this.genericTree = genericTree;
        this.intDelNodeVal = intDelNodeVal;
        this.scenePane = scenePane;
    }

    @Override
    public void run() {

        if (this.genericTreeController.getTreeDataStructure() instanceof GenericTree) {
            ArrayList<Node> queue = new ArrayList<Node>();
            Node nodeObject = this.genericTree.searchNode(intDelNodeVal);
            queue.add(nodeObject);
            while (queue.size() > 0) {
                Node tmp = queue.remove(0);
                if (tmp.getListOfChildren().size() > 0) {
                    queue.addAll(tmp.getListOfChildren());
                    ArrayList<Integer> tmpChildren = new ArrayList<Integer>();
                    for (Node child : tmp.getListOfChildren()) {
                        tmpChildren.add(child.getNodeId());
                    }
                    listDelNode.put(tmp.getNodeId(), tmpChildren);
                }
            }

            this.parentDelNode = nodeObject.getParentNode();
            ArrayList<Node> search_direction = this.genericTree.getPathToRoot(nodeObject);
            search_direction.add(this.genericTree.getRootNode());
            Collections.reverse(search_direction);
            drawAnimationsDelete(search_direction, nodeObject);
            System.out.println("Delete operation.");

        }
    }

    @Override
    public void undo() {
        Node newNode;
        if (this.parentDelNode != null) {
            newNode = this.parentDelNode.addChild(intDelNodeVal);
        }
        else {
//            if user delete root
            newNode = new Node(intDelNodeVal);
            this.genericTree.setRootNode(newNode);
        }

        ArrayList<Node> createdDelNode = new ArrayList<Node>();
        createdDelNode.add(newNode);

        while (createdDelNode.size() > 0) {
            Node tmp = createdDelNode.remove(0);
            if (listDelNode.containsKey(tmp.getNodeId())) {
                ArrayList<Integer> tmpChildrenVal = listDelNode.get(tmp.getNodeId());
                for (int childVal: tmpChildrenVal) {
                    Node child = tmp.addChild(childVal);
                    createdDelNode.add(child);
                }
            }
            this.scenePane.getChildren().add(tmp);
            this.scenePane.getChildren().add(tmp.getParentLine());
        }

        System.out.println("Delete operation undo.");
    }

    private void turnOffAnimationsDelete(ArrayList<Line> listLines, ArrayList<StackPane> listPanes, Node delRootNode) {
        try {
            int secondsToSleep = 1;
            long millisecondsToSleep = secondsToSleep * 1000;

            this.genericTreeController.deleteSubtree(delRootNode);

            Thread.sleep(millisecondsToSleep);
            scenePane.getChildren().removeAll(listLines);
            scenePane.getChildren().removeAll(listPanes);
            this.genericTreeController.rebuildTree();
//            System.out.println("hello Delete operation, root node: " + this.genericTree.getRootNode().getNodeId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void drawAnimationsDelete(ArrayList<Node> search_direction, Node delNode) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = this.genericTreeController.drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(event -> turnOffAnimationsDelete(listLines, listStackPane, delNode));
        seq.play();
    }
}
