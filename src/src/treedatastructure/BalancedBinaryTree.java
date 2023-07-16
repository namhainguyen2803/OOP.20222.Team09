package src.treedatastructure;

import javafx.fxml.FXML;
import src.exception.*;

import javax.swing.*;
import java.awt.*;

public class BalancedBinaryTree extends BalancedTree {
    public static final int MAX_CHILDREN = 2;
    public BalancedBinaryTree(int MAX_DIFF_DISTANCE) {
        super(MAX_DIFF_DISTANCE);
    }
    public boolean checkFullChildrenNode(Node node){
        return node.getNumChildren() == MAX_CHILDREN;
    }
    public boolean checkBinaryProperty(Node node) {
        return node.getNumChildren() <= MAX_CHILDREN;
    }
    public void checkInsertNode(int parentId, int childId) throws TreeException, TreeException {
        super.checkInsertNode(parentId, childId);

        Node parent = searchNode(parentId);
        if (parent == null) {
            throw new NodeNotExistsException("The parent node does not exist! Can't add!");
        }

        if (checkFullChildrenNode(parent)) {
            throw new NodeFullChildrenException("The parent already has 2 children! Can't add!");
        }

        if (!checkBinaryProperty(parent)) {
            throw new NonBinaryNodeException("The parent is not a binary node! Can't add!");
        }

        Node child = searchNode(childId);
        if (child != null) {
            throw new NodeExistedException("The child node has already existed! Can't add!");
        }
    }

    @Override
    public Node insertNode(int parentId, int childId) throws TreeException {
        // 1. Phần Binary
        checkInsertNode(parentId, childId); // Check constraints
        Node parent = searchNode(parentId);
        Node child = parent.addChild(childId);

        // 2. Phần Balance: Xử lý giống của hàm insertNode trong BalancedTree
        if (!this.isBalanced()){
            String answer = JOptionPane.showInputDialog("WARNING: The tree is not balanced!\nDo you want to make it balanced again?\nIf you don't want, the inserted node will be deleted!");
            if (answer.equals("Yes")){
                this.makeBalanced(childId);
            }
            else{
                super.deleteNode(childId);
            }
        }

        return child;
    }


}
