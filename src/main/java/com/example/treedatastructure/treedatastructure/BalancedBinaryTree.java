package treedatastructure;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;

import javax.swing.*;

public class BalancedBinaryTree extends BalancedTree{
    public static final int MAX_CHILDREN = 2;
    public BalancedBinaryTree(int MAX_DIFF_DISTANCE) {
        super(MAX_DIFF_DISTANCE);
    }

    @Override
    public void insertNode(int parentId, int childId) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
        // 1. Phần Binary
        Node parent = searchNode(parentId);
        if (parent == null){ // có thể throw 1 cái exception ở đây
            throw new NodeNotExistsException("The parent node does not exist! Can't add!");
        }

        if (this.checkFullChildrenNode(parent)){
            throw new NodeFullChildrenException("The parent already has 2 children! Can't add!");
        }

        Node child = searchNode(childId);
        if (child!=null){ // có thể throw 1 cái exception ở đây
            throw new NodeExistedException("The child node has already existed! Can't add!");
        }
        parent.addChild(childId);

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

    }

    public boolean checkFullChildrenNode(Node node){
        return node.getNumChildren() == MAX_CHILDREN;
    }
}
