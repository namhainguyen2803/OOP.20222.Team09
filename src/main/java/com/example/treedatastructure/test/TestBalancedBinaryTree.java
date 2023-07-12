package test;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import treedatastructure.BalancedBinaryTree;

public class TestBalancedBinaryTree {
    public static void main(String[] args) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
        int MAX_DIFF_DISTANCE = 1;
        BalancedBinaryTree balBinTree = new BalancedBinaryTree(MAX_DIFF_DISTANCE);
        int rootId = 1;
        balBinTree.createTree(rootId);

        balBinTree.insertNode(1,2);
        balBinTree.insertNode(1,3);
        balBinTree.insertNode(2,4);
        balBinTree.insertNode(2,10);
        balBinTree.insertNode(4,5);

        balBinTree.traverseTreeBFS();
    }
}
