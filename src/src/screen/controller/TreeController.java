package src.screen.controller;

import src.treedatastructure.Node;
import src.treedatastructure.Tree;

import java.util.ArrayList;

public abstract class TreeController {

    private Tree treeDataStructure;

    public void setTreeDataStructure (Tree tree) {
        this.treeDataStructure = tree;
    }

    public Tree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    public abstract void drawAnimationsInsert(ArrayList<Node> searchDirection, int parentval, int nodeVal);

    public abstract void rebuildTree();
}
