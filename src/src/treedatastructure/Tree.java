package src.treedatastructure;

import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.exception.NoneAlgorithmSpecifiedException;

import java.util.ArrayList;

public abstract class Tree {

    private String treeType;

    public String getTreeType() {
        return this.treeType;
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

    public abstract void createTree(int rootId);
    public abstract Node getRootNode();

    public abstract void setRootNode(Node newRoot);

    public abstract Node insertNode(int parentId, int childId) throws
            NodeNotExistsException, NodeExistedException, NodeFullChildrenException;

    public abstract void deleteNode(int delId) throws
            NodeNotExistsException, NodeExistedException, NodeFullChildrenException;

    public abstract void updateNode(int oldId, int newId) throws NodeNotExistsException, NodeExistedException;

    public abstract Node searchNode(int intParentVal);

    public abstract void traverseTree(String algorithm) throws NoneAlgorithmSpecifiedException;

    public abstract void checkInsertNode(int intParentVal, int intNodeVal) throws
            NodeNotExistsException, NodeExistedException, NodeFullChildrenException;
}
