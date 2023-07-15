package src.treedatastructure;

import src.exception.*;
import src.screen.controller.GenericTreeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public abstract class GenericTree extends Tree {
    private Node rootNode;
    private GenericTreeController treeController;

    private double timesleep = 1;

    private Color recColor1 = Color.web("#99f28a");
    private Color recColor2 = Color.BLUE;

    private Color VISIT_COLOR = Color.BLUE;

    private ArrayList<Node> queue;

    private Timeline timeline;

    private Node traverseNode; // Node này sử dụng cho hàm traverse

    public GenericTree () {
        this.setTreeType("generic");
    }

    @Override
    public Node getRootNode() {
        return this.rootNode;
    }

    //    why we need this method?
    @Override
    public void setRootNode(Node newRoot) {
        this.rootNode = newRoot;
        this.rootNode.setDepth(0);
        this.rootNode.setParentNode(null);

        if (this.rootNode.getNumChildren() > 0) { // nếu là 1 subtree
            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(this.rootNode);

            Node tmp;
            while (queue.size() > 0) {
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

    public Timeline getTimeline() {
        return timeline;
    }


    @Override
    public void createTree(int rootId) {
        this.rootNode = new Node(rootId);
    }

    public void createTree() {
        int id = 1;
        this.rootNode = new Node(id);
    }

    public void traverseTreeBFS() {
        queue = new ArrayList<Node>();

        queue.add(rootNode);
        treeController.getRecPseudoBFS1().setFill(recColor2);
        rootNode.setState(1);

        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (traverseNode == null) {
                    // thay đổi màu
                    treeController.getRecPseudoBFS1().setFill(recColor1);
                    treeController.getRecPseudoBFS2().setFill(recColor2);
                    treeController.getRecPseudoBFS3().setFill(recColor2);

                    traverseNode = queue.remove(0);
                }
                if (traverseNode.getState() == 1) { // Nếu đã được add vào queue và đc remove ra (xong state1) thì tiếp state2 (Sys.print)
                    try { // duyệt
                        traverseNode.getCircle().setFill(VISIT_COLOR);
                        System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth() + " " + traverseNode.getParentNode().getNodeId()); // print node tmp
                        traverseNode.setState(2);
                    } catch (NullPointerException e) {
                        traverseNode.getCircle().setFill(VISIT_COLOR);
                        System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                        traverseNode.setState(2);
                    }
                } else if (traverseNode.getState() == 2) {
                    // thay đổi màu
                    treeController.getRecPseudoBFS2().setFill(recColor1);
                    treeController.getRecPseudoBFS3().setFill(recColor1);
                    treeController.getRecPseudoBFS4().setFill(recColor2);
                    treeController.getRecPseudoBFS5().setFill(recColor2);

                    if (traverseNode.getNumChildren() > 0) { // add con nếu có
                        for (Node n : traverseNode.getListOfChildren()) {
                            queue.add(n);
                            n.setState(1);
                        }
                    }
                    traverseNode.setState(3);
                } else if (traverseNode.getState() == 3) {
                    // thay đổi màu
                    treeController.getRecPseudoBFS4().setFill(recColor1);
                    treeController.getRecPseudoBFS5().setFill(recColor1);
                    treeController.getRecPseudoBFS2().setFill(recColor2);
                    treeController.getRecPseudoBFS3().setFill(recColor2);

                    if (queue.size() > 0) { // lấy node đầu của queue ra
                        traverseNode = queue.remove(0);
                    } else {
                        timeline.stop();

                    }
                }
            }
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void backWardTraverseBFS(){
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        }));
    }

    public void traverseTreeDFS(){
        ArrayList<Node> stack = new ArrayList<Node>();

        stack.add(rootNode);
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (stack.size() > 0){
                    Node tmp = stack.remove(stack.size()-1); // lấy phần tử đầu miệng của stack (nghĩa là cuối arrayList)
                    try{
                        tmp.getCircle().setFill(VISIT_COLOR);
                        System.out.println(tmp.getNodeId() + " " + tmp.getDepth() + " " + tmp.getParentNode().getNodeId()); // print node tmp
                    }catch (NullPointerException e){
                        tmp.getCircle().setFill(VISIT_COLOR);
                        System.out.println(tmp.getNodeId()+" "+tmp.getDepth()); // print node tmp
                    }

                    if (tmp.getNumChildren() > 0) {
                        for (int i = tmp.getNumChildren()-1; i >=0; i--) {
                            stack.add(tmp.getListOfChildren().get(i));
                        }
                    }
                }
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
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

    public void checkNodeExisted(int searchId) throws TreeException {
        Node tmp = searchNode(searchId);
        if (tmp == null) {
            throw new NodeNotExistsException("Node does not exist.");
        }
    }

    public void checkNodeNotExisted(int searchId) throws TreeException {
        Node tmp = searchNode(searchId);
        if (tmp != null) {
            throw new NodeExistedException("Node does exist.");
        }
    }


    public Node searchNode (int searchId){
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.getNodeId() == searchId) {
            return rootNode;
        }
        queue.add(rootNode);
        Node tmp;
        while (queue.size() > 0) {
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

    public Node searchNodeByBFS ( int searchId){
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.getNodeId() == searchId) {
            return rootNode;
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0) {
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

    public ArrayList<Node> getDirectionByDFS ( int searchId){
        Stack<Node> stack = new Stack<Node>();
        ArrayList<Node> visited_nodes = new ArrayList<Node>();

        stack.push(rootNode);
        visited_nodes.add(rootNode);
        if (rootNode.getNodeId() == searchId) {
            return visited_nodes;
        }

        Node tmp;
        while (stack.size() > 0) {
            tmp = stack.remove(stack.size() - 1); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    stack.push(n);
                    visited_nodes.add(n);
                    if (n.getNodeId() == searchId) {
                        return visited_nodes;
                    }
                }
            }
        }

        return null;
    }

    public ArrayList<Node> getPathToRoot (Node node){
        ArrayList<Node> list_node = new ArrayList<Node>();
        while (!node.equals(this.rootNode)) {
            list_node.add(node);
            node = node.getParentNode();
        }
        return list_node;
    }

    @Override
    public Node insertNode (int parentId, int childId) throws TreeException {
        Node parent = searchNode(parentId);
        Node childNode = parent.addChild(childId);
//        this.addData(childNode);
        return childNode;
    }

    public void checkInsertNode (int parentId, int childId) throws TreeException {
        checkNodeExisted(parentId);
        checkNodeNotExisted(childId);
    }


    @Override
    public void updateNode ( int oldId, int newId) {
        Node oldNode = searchNode(oldId);
        oldNode.updateId(newId);
    }

    public void checkUpdateNode ( int oldId, int newId) throws TreeException {

        checkNodeExisted(oldId);

        checkNodeNotExisted(newId);

    }


    public void deleteNode(int delId) throws NodeNotExistsException,NodeExistedException,NodeFullChildrenException{

    }
}
