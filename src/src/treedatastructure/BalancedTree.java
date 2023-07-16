package src.treedatastructure;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.exception.*;

import javax.swing.*;
import java.util.ArrayList;

public class BalancedTree extends GenericTree{
    private int MAX_DIFF_DISTANCE;

    public BalancedTree() {
        this.MAX_DIFF_DISTANCE = 2;
    }


    public BalancedTree(int MAX_DIFF_DISTANCE){
        this.MAX_DIFF_DISTANCE = MAX_DIFF_DISTANCE;
    }

    public void setMaxDiffDistance(int maxDiff) {
        this.MAX_DIFF_DISTANCE = maxDiff;
    }

    public int getMaxDiffDistance() {
        return this.MAX_DIFF_DISTANCE;
    }

    public boolean isBalanced() {
        boolean balance = true;
        Node rootNode = this.getRootNode();
        ArrayList<Node> listOfLeaves = new ArrayList<Node>();

        // 1. Lấy danh sách các Leaf: O(N) với N = số node của cây
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.isLeaf()){
            listOfLeaves.add(rootNode);
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue
            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.isLeaf()) {
                        listOfLeaves.add(n);
                    }
                    queue.add(n);
                }
            }
        }

        // 2. Check xem có balance không ? O(M^2) với M là số lá của cây
        int diff_distance;
        int numberOfLeaves = listOfLeaves.size();
        System.out.println(numberOfLeaves);
        for (int i=0; i<numberOfLeaves; i++){
            for(int j=i+1; j<numberOfLeaves; j++){
                diff_distance = Math.abs(listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth());
                System.out.println(listOfLeaves.get(i).getNodeId() + " " + listOfLeaves.get(j).getNodeId() + " " + diff_distance);
                if (diff_distance > MAX_DIFF_DISTANCE){
                    balance = false;
                }
            }
        }
        return balance;
    }

    @Override
    public void checkInsertNode(int parentId, int childId) throws TreeException {
        super.checkInsertNode(parentId, childId);

        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        tmpBalancedTree.insertNode(parentId, childId);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

    public BalancedTree copyBalanceTree(BalancedTree oldTree) {
        ArrayList<Node> oldQueue = new ArrayList<Node>();
//        ArrayList<Node> newQueue = new ArrayList<Node>();
        BalancedTree newTree = new BalancedTree(this.getMaxDiffDistance());
        oldQueue.add(oldTree.getRootNode());
        Node newRoot = new Node(oldTree.getRootNode().getNodeId());
        newTree.setRootNode(newRoot);
//        newQueue.add(newRoot);
        while (oldQueue.size() > 0) {
            Node tmp = oldQueue.remove(0);
//            Node newTmp = newQueue.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                for (Node childNode: tmp.getListOfChildren()) {
                    Node newChild = newTree.insertNode(tmp.getNodeId(), childNode.getNodeId());
                    oldQueue.add(childNode);
//                    newQueue.add(newChild);
                }
            }
        }
        return newTree;
    }


    @Override
    public void checkDeleteNode(int oldNode) throws TreeException {
        super.checkDeleteNode(oldNode);

        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        System.out.println("hello " + tmpBalancedTree.getRootNode().getNodeId());
        tmpBalancedTree.deleteNode(oldNode);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

}




