package src.treedatastructure;

import src.exception.*;
import src.screen.controller.GenericTreeController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class GenericTree {
    private Node rootNode;

    private double timesleep = 1;

    private Color recColor1 = Color.web("#99f28a");
    private Color recColor2 = Color.BLUE;

    private Color NOT_VISIT_COLOR = Color.WHITE;

    private Color VISIT_COLOR = Color.BLUE;

    private ArrayList<Node> queue;

    private ArrayList<Node> stack;

    private Timeline timeline;

    private Node traverseNode; // Node này sử dụng cho hàm traverse

    private GenericTreeController treeController;

    public void setTreeController(GenericTreeController treeController) {
        this.treeController = treeController;
    }

    public GenericTreeController getTreeController() {
        return this.treeController;
    }

    public Node getRootNode() {
        return this.rootNode;
    }

    //    why we need this method?
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


    public void createTree(int rootId) {
        this.rootNode = new Node(rootId);
        System.out.println(rootId + "");
    }

    public void createTree() {
        int id = 1;
        this.rootNode = new Node(id);
    }

    /*
    Các method cho traverse BFS
     */
    public void forwardBFS1Step(){
        if (traverseNode == null) {
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);

            traverseNode = queue.remove(0);
        }
        if (traverseNode.getState() == 1) { // TH1
            // Nếu đã được add vào queue và đc remove ra (xong state1) thì tiếp state2 (Sys.print)
            try { // duyệt
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth() + " " + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(2);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
            }
        } else if (traverseNode.getState() == 2) { //TH2
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.add(n);
                    n.setState(1);
                }
            }
            traverseNode.setState(3);
        } else if (traverseNode.getState() == 3) { //TH3
            // thay đổi màu
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);

            if (queue.size() > 0) { // lấy node đầu của queue ra
                traverseNode = queue.remove(0);
            } else {
                timeline.stop();

            }
        }
    }

    public void backwardBFS1Step(){
        if (traverseNode==null){
            return;
        }
        if (traverseNode.getState()==1){
            queue.add(0,traverseNode);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);

            /*
            TH0: nó ko có bố mẹ (nó là root)
             */
            if (traverseNode.isRootNode()){
                traverseNode = null;
                this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
                this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
                this.getTreeController().getRecPseudoBFS1().setFill(recColor2);
                return;
            }

            boolean traverseNodeIsFirstChild;
            try{
                traverseNodeIsFirstChild = traverseNode.isFirstChild();
            }catch (NodeNotExistsException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
                return;
            }

            /*
            TH1: nó có anh em ở trước
             */
            if (!traverseNodeIsFirstChild){
                traverseNode = traverseNode.getLeftSibling();
                return;
            }

            /*
            TH2: nó ko có anh em ở trước (nó là con đầu)
             */

            //TH2.1: Nếu cha là rootNode
            Node parent = traverseNode.getParentNode();
            if (parent.isRootNode()){
                traverseNode = rootNode;
            }

            //TH2.2: Nếu cha nó ko phải rootNode
            else{
                Node leftSblingOfParent = parent.getLeftSibling();
                /*
                Nếu cha của nó có leftSibling
                 */
                if (leftSblingOfParent!=null) {
                    /*
                    Nếu leftSibling đó có con
                     */
                    if (leftSblingOfParent.getNumChildren()>0) {
                        traverseNode = traverseNode.getParentNode().getLeftSibling().getTheLastChild();
                    }

                    /*
                    Nếu leftSibling đó ko có con
                     */
                    else{
                        traverseNode = parent.getParentNode().getTheLastChild();
                    }
                }
                /*
                Nếu cha nó là con đầu của ông nó
                 */
                else{
                    Node grandpa = parent.getParentNode();
                    traverseNode = grandpa.getTheLastChild();
                }
            }
        }

        else if (traverseNode.getState()==2){
            try { // duyệt
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " " + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(1);
            } catch (NullPointerException e) {
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward traverse" + traverseNode.getNodeId() + " " + traverseNode.getDepth()); // print node tmp
                traverseNode.setState(1);
            }
        }
        else if (traverseNode.getState()==3){
            this.getTreeController().getRecPseudoBFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor2);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);

            if (traverseNode.getNumChildren() > 0) { // add con nếu có
                for (Node n : traverseNode.getListOfChildren()) {
                    queue.remove(n);
                    n.setState(0);
                }
            }

            traverseNode.setState(2);
        }
    }

    public void startTraverseTreeBFS() {
        queue = new ArrayList<Node>();

        queue.add(rootNode);
        this.getTreeController().getRecPseudoBFS1().setFill(recColor2);
        rootNode.setState(1);

        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardBFS1Step();
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    /*
    Các method cho traverse DFS
     */
    public void forwardDFS1Step(){
        if (traverseNode==null){
            this.getTreeController().getRecPseudoDFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);
            traverseNode = stack.remove(stack.size()-1);
        }

        if (traverseNode.getState()==1){
            try{
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId() + " " + traverseNode.getDepth() + " " + traverseNode.getParentNode().getNodeId()); // print node tmp
                traverseNode.setState(2);
            }catch (NullPointerException e){
                traverseNode.getCircle().setFill(VISIT_COLOR);
                System.out.println(traverseNode.getNodeId()+" "+traverseNode.getDepth()); // print node tmp
                traverseNode.setState(2);
            }
        }

        else if (traverseNode.getState()==2){
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor2);

            if (traverseNode.getNumChildren() > 0) {
                for (int i = traverseNode.getNumChildren()-1; i >=0; i--) {
                    stack.add(traverseNode.getListOfChildren().get(i));
                    traverseNode.getListOfChildren().get(i).setState(1);
                }
            }
            traverseNode.setState(3);
        }

        else if (traverseNode.getState()==3){
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);

            if (stack.size()>0){
                traverseNode = stack.remove(stack.size()-1);
            }
            else{
                timeline.stop();
            }
        }
    }

    public void backwardDFS1Step(){
        if (traverseNode==null){
            return;
        }

        if (traverseNode.getState()==1){
            stack.add(traverseNode);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);

            if (traverseNode.isRootNode()){
                traverseNode = null;
                this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
                this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
                this.getTreeController().getRecPseudoDFS1().setFill(recColor2);
                return;
            }

            boolean traverseNodeIsFisrtChild;
            try {
                traverseNodeIsFisrtChild = traverseNode.isFirstChild();
            }catch(NodeNotExistsException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
                return;
            }

            if (traverseNodeIsFisrtChild){
                traverseNode = traverseNode.getParentNode();
                return;
            }


            Node leftSibling = traverseNode.getLeftSibling();
            traverseNode = leftSibling;
            while (traverseNode.getNumChildren()>0){
                traverseNode = traverseNode.getListOfChildren().get(traverseNode.getNumChildren()-1);
            }
        }

        else if (traverseNode.getState()==2){
            traverseNode.setState(1);

            try{
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward " + traverseNode.getNodeId() + " " + traverseNode.getDepth() + " " + traverseNode.getParentNode().getNodeId()); // print node tmp

            }catch (NullPointerException e){
                traverseNode.getCircle().setFill(NOT_VISIT_COLOR);
                System.out.println("Backward " + traverseNode.getNodeId()+" "+traverseNode.getDepth()); // print node tmp

            }

        }
        else if (traverseNode.getState()==3){
            traverseNode.setState(2);

            this.getTreeController().getRecPseudoDFS2().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor2);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);

            if (traverseNode.getNumChildren() > 0) {
                for (int i = traverseNode.getNumChildren()-1; i >=0; i--) {
                    stack.remove(traverseNode.getListOfChildren().get(i));
                    traverseNode.getListOfChildren().get(i).setState(0);
                }
            }
        }
    }
    public void startTraverseTreeDFS(){
        stack = new ArrayList<Node>();

        this.getTreeController().getRecPseudoDFS1().setFill(recColor2);
        stack.add(rootNode);
        rootNode.setState(1);
        timeline = new Timeline(new KeyFrame(Duration.seconds(timesleep), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                forwardDFS1Step();
            }
        }));

        timeline.setCycleCount(-1);
        timeline.play();
    }

    /*
    Các method cho traverse chung
     */
    public void pauseTraverse(){
        timeline.pause();
    }

    public void continueTraverse(){
        timeline.play();
    }

    public void okTraverse(){
        timeline.stop();
        //0. Empty cái stack/queue và empty cái traverse node = null
        String algo = this.getTreeController().getAlgorithm();
        if (algo.equals("BFS")){
            queue = null;
        }
        else{
            stack = null;
        }
        traverseNode = null;
        //1. Set lại state=0 và colorCircle = NOT_VISIT_COLOR
        queue = new ArrayList<Node>();
        queue.add(rootNode);
        Node tmp;

        while (queue.size()>0){
            tmp = queue.remove(0);
            tmp.setState(0);
            tmp.getCircle().setFill(NOT_VISIT_COLOR);

            if (tmp.getNumChildren()>0){
                for (Node n: tmp.getListOfChildren()){
                    queue.add(n);
                }
            }
        }

        queue = null;
        //2. Set lại màu của các pseudoCode
        if (algo.equals("BFS")){
            this.getTreeController().getRecPseudoBFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoBFS5().setFill(recColor1);
        }
        else{
            this.getTreeController().getRecPseudoDFS1().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS2().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS3().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS4().setFill(recColor1);
            this.getTreeController().getRecPseudoDFS5().setFill(recColor1);
        }
        //3. set visible = false cho cái stackPane
        this.getTreeController().getStackPanePseudo().setVisible(false);
        this.getTreeController().getStackPaneController().setVisible(false);
        if (algo.equals("BFS")){
            this.getTreeController().getvBoxBFS().setVisible(false);
        }
        if (algo.equals("BFS")){
            this.getTreeController().getvBoxDFS().setVisible(false);
        }

    }
    public void traverseTree(String algorithm) throws NoneAlgorithmSpecifiedException {
        if (!algorithm.equals("BFS") & !algorithm.equals("DFS")){
            throw new NoneAlgorithmSpecifiedException("The algorithm should be BFS or DFS!");
        }
        if (algorithm.equals("BFS")){
            startTraverseTreeBFS();
        }
        else{
            startTraverseTreeDFS();
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
        if (this.getRootNode().getNodeId() == searchId) {
            return this.getRootNode();
        }
        queue.add(this.getRootNode());
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

    public ArrayList<Node> getPathToRoot (Node node){
        ArrayList<Node> list_node = new ArrayList<Node>();
        while (!node.equals(this.rootNode)) {
            list_node.add(node);
            node = node.getParentNode();
        }
        return list_node;
    }

    public Node insertNode (int parentId, int childId) {
        Node parent = searchNode(parentId);
        Node childNode = parent.addChild(childId);
//        this.addData(childNode);
        return childNode;
    }

    public void checkInsertNode (int parentId, int childId) throws TreeException {
        checkNodeExisted(parentId);
        checkNodeNotExisted(childId);
    }


    public void updateNode ( int oldId, int newId) {
        Node oldNode = searchNode(oldId);
        oldNode.updateId(newId);
    }

    public void checkUpdateNode ( int oldId, int newId) throws TreeException {

        checkNodeExisted(oldId);

        checkNodeNotExisted(newId);

    }

    protected void deleteNode(int rootId) {
        Node root = this.searchNode(rootId);
        if (!root.equals(this.getRootNode())) {
            root.getParentNode().getListOfChildren().remove(root);
        }

        ArrayList<Node> listNode = new ArrayList<Node>(root.getListOfChildren());
        while (listNode.size() > 0) {
            Node tmp = listNode.remove(0);
            if (tmp.getListOfChildren().size() > 0){
                listNode.addAll(tmp.getListOfChildren());
            }
            tmp.getParentNode().getListOfChildren().remove(tmp);
            tmp.setId(null);
        }
    }

    public void checkDeleteNode(int delId) throws TreeException {
        this.checkNodeExisted(delId);
    }
}
