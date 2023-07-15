package src.treedatastructure;

import src.exception.*;
import src.screen.controller.TreeController;

import java.util.ArrayList;

public abstract class Tree {

    public boolean checkBinary;
    private ArrayList<Node> data;
    private String treeType;

    private TreeController treeController;

    public String getTreeType() {
        return this.treeType;
    }

    public void setTreeController(TreeController treeController) {
        this.treeController = treeController;
    }

    public TreeController getTreeController() {
        return this.treeController;
    }

    public void setTreeType(String type) {
        ArrayList<String> possibleTypes = new ArrayList<String>();
        possibleTypes.add("generic");
        possibleTypes.add("binary");
        possibleTypes.add("balanced");
        possibleTypes.add("balanced binary");
        assert possibleTypes.contains(type): "Wrong tree type";
        this.treeType = type;
    }

    public void addData(Node node) {
        this.data.add(node);
    }

    public void addData(ArrayList<Node> nodeList) {
        this.data.addAll(nodeList);
    }

    public ArrayList<Node> getData() {
        return this.data;
    }

    public abstract void createTree(int rootId);
    public abstract Node getRootNode();

    public abstract void setRootNode(Node newRoot);

    public abstract void checkInsertNode(int intParentVal, int intNodeVal) throws TreeException;

    public abstract Node insertNode(int parentId, int childId);

//    public abstract void deleteNode(int delId) throws TreeException;

    public abstract void updateNode(int oldId, int newId) throws TreeException;

    public abstract Node searchNode(int intParentVal);

    public abstract void traverseTree(String algorithm) throws NoneAlgorithmSpecifiedException;

    public abstract ArrayList<Node> getPathToRoot(Node nodeObject);

    public abstract void checkUpdateNode(int intOldVal, int intNewVal) throws TreeException;

    public abstract void checkNodeExisted(int nodeVal) throws TreeException;

}
