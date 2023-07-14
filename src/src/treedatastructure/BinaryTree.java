package src.treedatastructure;

import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;

public class BinaryTree extends GenericTree {
    public static final int MAX_CHILDREN = 2;

    public BinaryTree() {
        this.setTreeType("binary");
    }

    @Override
    public Node insertNode(int parentId, int childId) throws NodeNotExistsException, NodeFullChildrenException, NodeExistedException {
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
        return parent.addChild(childId);
    }

    /**
     * Check 1 node đã đủ 2 con chưa vì đây là cây Binary
     * @param node
     * @return
     */
    public boolean checkFullChildrenNode(Node node){
        return node.getNumChildren() == MAX_CHILDREN;
    }
}
