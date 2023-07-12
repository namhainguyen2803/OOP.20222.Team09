package test;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import treedatastructure.BinaryTree;

public class TestBinaryTree {
    public static void main(String[] args) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
        BinaryTree binTree = new BinaryTree();

        int rootId = 1;
        binTree.createTree(1);

        binTree.insertNode(1,2);
        binTree.insertNode(1,3);
        binTree.insertNode(9,4);
        binTree.insertNode(3,5);

        binTree.traverseTreeDFS();
    }
}
