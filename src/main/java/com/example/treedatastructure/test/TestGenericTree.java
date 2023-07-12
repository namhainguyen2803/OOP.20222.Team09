package test;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import treedatastructure.GenericTree;

public class TestGenericTree {
    public static void main(String[] args) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
        GenericTree genTree = new GenericTree();

        int rootId = 1;
        genTree.createTree(rootId);

        genTree.insertNode(1, 2);
        genTree.insertNode(1,3);
        genTree.insertNode(1,4);
        genTree.insertNode(2,5);
        genTree.insertNode(2,6);

        genTree.updateNode(2,999);
        genTree.traverseTreeBFS();
    }
}
