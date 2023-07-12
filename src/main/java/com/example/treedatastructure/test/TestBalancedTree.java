package test;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import treedatastructure.BalancedTree;

public class TestBalancedTree {
    public static void main(String[] args) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
        int MAX_DIFF_DISTANCE = 2;
        BalancedTree balTree = new BalancedTree(MAX_DIFF_DISTANCE);

        balTree.createTree(1);
        balTree.insertNode(1,2);
        balTree.insertNode(1,3);
        balTree.insertNode(1,4);
        balTree.insertNode(2,5);
        balTree.insertNode(3,8);
        balTree.insertNode(4,9);
        balTree.insertNode(5,6);
        balTree.insertNode(5,10);
        balTree.insertNode(6,7);

        System.out.println(balTree.isBalanced());

        balTree.deleteNode(8);
        System.out.println(balTree.isBalanced());
        balTree.traverseTreeBFS();
    }
}
