package src.screen.controller;

import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import src.treedatastructure.Node;
import src.treedatastructure.Tree;

import java.util.ArrayList;

public abstract class TreeController {

    private String treeType;

    private Tree treeDataStructure;

    public String getTreeType() {
        return treeType;
    }

    public void setTreeType(String treeType) {
        this.treeType = treeType;
    }

    public void setTreeDataStructure (Tree tree) {
        this.treeDataStructure = tree;
    }

    public Tree getTreeDataStructure() {
        return this.treeDataStructure;
    }

    public abstract void drawAnimationsInsert(ArrayList<Node> searchDirection, int parentval, int nodeVal);

    public abstract void rebuildTree();

    public abstract void resetPressed();

    public abstract Rectangle getRecPseudoBFS1();

    public abstract Rectangle getRecPseudoBFS2();

    public abstract Rectangle getRecPseudoBFS3();

    public abstract Rectangle getRecPseudoBFS4();

    public abstract Rectangle getRecPseudoBFS5();

    public abstract Rectangle getRecPseudoDFS1();

    public abstract Rectangle getRecPseudoDFS2();

    public abstract Rectangle getRecPseudoDFS3();

    public abstract Rectangle getRecPseudoDFS4();

    public abstract Rectangle getRecPseudoDFS5();
}
