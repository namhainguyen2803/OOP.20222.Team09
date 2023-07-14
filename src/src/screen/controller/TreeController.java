package src.screen.controller;

import src.treedatastructure.Tree;

public abstract class TreeController {

    private Tree treeDataStructure;

    public void setTreeDataStructure (Tree tree) {
        this.treeDataStructure = tree;
    }

    public Tree getTreeDataStructure() {
        return this.treeDataStructure;
    }
}
