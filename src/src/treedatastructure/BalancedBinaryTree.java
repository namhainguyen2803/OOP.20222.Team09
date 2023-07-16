package src.treedatastructure;

import src.exception.*;

import javax.swing.*;

public class BalancedBinaryTree extends BalancedTree {
    public static final int MAX_CHILDREN = 2;

    public BalancedBinaryTree() {
        super(2);
    }

    public BalancedBinaryTree(int MAX_DIFF_DISTANCE) {
        super(MAX_DIFF_DISTANCE);
    }

    @Override
    public void checkInsertNode(int parentId, int childId) throws TreeException {
        super.checkInsertNode(parentId, childId);

        if (this.checkFullChildrenNode(parentId)){
            throw new NodeFullChildrenException("The parent already has 2 children! Can't add!");
        }
    }

    public boolean checkFullChildrenNode(Node node){
        return node.getNumChildren() == MAX_CHILDREN;
    }

    public boolean checkFullChildrenNode(int nodeVal){
        Node node = this.searchNode(nodeVal);
        return node.getNumChildren() == MAX_CHILDREN;
    }

}
