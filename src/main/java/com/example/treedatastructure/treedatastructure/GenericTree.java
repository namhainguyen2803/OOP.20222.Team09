package treedatastructure;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;
import exception.NoneAlgorithmSpecifiedException;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.ArrayList;

public class GenericTree {
    private Node rootNode;

//    private Duration TRANSITION_DURATION = new Duration(3); // timesleep = 3

    public Node getRootNode(){
        return this.rootNode;
    }
    public void setRootNode(Node newRoot){
        this.rootNode = newRoot;
        this.rootNode.setDepth(0);
        this.rootNode.setParentNode(null);

        if (this.rootNode.getNumChildren() > 0){ // nếu là 1 subtree
            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(this.rootNode);

            Node tmp;
            while (queue.size() > 0){
                tmp = queue.remove(0); // lấy node đầu tiên của queue

                if (tmp.getNumChildren() > 0) {
                    for (Node n : tmp.getListOfChildren()) {
                        n.setDepth(tmp.getDepth() + 1);
                        queue.add(n);
                    }
                }
            }
        }
    }

    /**
     * Tạo 1 cái cây mới; khởi tạo rootNode của nó bằng giá trị ID truyền vào
     * @param rootId
     */
    public void createTree(int rootId){
        rootNode = new Node(rootId);
    }

    /**
     * Tạo 1 cái cây mới; khởi tạo rootNode của nó với ID = 3.
     * Có thể thay đổi bằng hàm random hoặc nhập input từ user
     */
    public void createTree(){
        int id = 3;
        createTree(id);
    }


    public void traverseTreeBFS(){
        ArrayList<Node> queue = new ArrayList<Node>();

        // Xử lý rootnode
        System.out.println(rootNode.getNodeId());
        queue.add(rootNode);
        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    // Xử lý node con
                    System.out.println(n.getNodeId()+" "+n.getDepth()+" "+n.getParentNode().getNodeId()); // print node tmp

                    queue.add(n);
                }
            }
        }}

    public void traverseTreeDFS(){
        ArrayList<Node> stack = new ArrayList<Node>();
        stack.add(rootNode);

        Node tmp;
        while (stack.size() > 0){
            tmp = stack.remove(stack.size()-1); // lấy phần tử đầu miệng của stack (nghĩa là cuối arrayList)
            System.out.println(tmp.getNodeId()); // print node tmp

            if (tmp.getNumChildren() > 0) {
                for (int i = tmp.getNumChildren()-1; i >=0; i--) {
                    stack.add(tmp.getListOfChildren().get(i));
                }
            }
        }
    }

    public void traverseTree(String algorithm) throws NoneAlgorithmSpecifiedException {
        if (!algorithm.equals("BFS") & !algorithm.equals("DFS")){
            throw new NoneAlgorithmSpecifiedException("The algorithm should be BFS or DFS!");
        }
        if (algorithm.equals("BFS")){
            traverseTreeBFS();
        }
        else{
            traverseTreeDFS();
        }
    }

    /**
     * Hàm search từ rootNode của cây sử dụng BFS
     * @param searchId
     * @return
     */
    public Node searchNode(int searchId){
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.getNodeId()==searchId){
            return rootNode;
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.getNodeId() == searchId) {
                        return n;
                    }
                    queue.add(n);
                }
            }
        }

        return null;
    }

    public void insertNode(int parentId, int childId) throws NodeNotExistsException, NodeExistedException, NodeFullChildrenException {
        Node parent = searchNode(parentId);
        if (parent == null){ // có thể throw 1 cái exception ở đây
            throw new NodeNotExistsException("The parent node does not exist! Can't add!");
        }

        Node child = searchNode(childId);
        if (child!=null){ // có thể throw 1 cái exception ở đây
            throw new NodeExistedException("The child node has already existed! Can't add!");
        }

        parent.addChild(childId);
    }

    /**
     * Sử dụng BFS để tìm kiếm và xoá node
     * @param delId
     */
    public void deleteNode(int delId) throws NodeNotExistsException, NodeExistedException, NodeFullChildrenException{
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.getNodeId()==delId){
            rootNode = null;
            return;
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue
            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.getNodeId() == delId) {
                        tmp.getListOfChildren().remove(n);
                        return;
                    }

                    queue.add(n);
                }
            }
        }

        throw new NodeNotExistsException("The node does not exist! Can't delete");
    }

    public void updateNode(int oldId, int newId) throws NodeNotExistsException, NodeExistedException {
        Node oldNode = searchNode(oldId);
        if (oldNode==null){
            throw new NodeNotExistsException("The node does not exist! Can't update!");
        }

        Node newNode = searchNode(newId);
        if (newNode != null){
            throw new NodeExistedException("The new ID has existed in another node! Can't update");
        }

        oldNode.updateId(newId);
    }

}
