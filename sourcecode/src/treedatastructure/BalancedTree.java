package src.treedatastructure;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import src.exception.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class BalancedTree extends GenericTree{
    private int MAX_DIFF_DISTANCE;

    public BalancedTree() {
        this.MAX_DIFF_DISTANCE = 2;
    }


    public BalancedTree(int MAX_DIFF_DISTANCE){
        this.MAX_DIFF_DISTANCE = MAX_DIFF_DISTANCE;
    }

    public int getMaxDiffDistance() {
        return this.MAX_DIFF_DISTANCE;
    }

    public boolean isBalanced() {
        boolean balance = true;
        Node rootNode = this.getRootNode();
        ArrayList<Node> listOfLeaves = new ArrayList<Node>();

        // 1. Lấy danh sách các Leaf: O(N) với N = số node của cây
        ArrayList<Node> queue = new ArrayList<Node>();
        if (rootNode.isLeaf()){
            listOfLeaves.add(rootNode);
        }
        queue.add(rootNode);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue
            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.isLeaf()) {
                        listOfLeaves.add(n);
                    }
                    queue.add(n);
                }
            }
        }

        // 2. Check xem có balance không ? O(M^2) với M là số lá của cây
        int diff_distance;
        int numberOfLeaves = listOfLeaves.size();
        System.out.println(numberOfLeaves);
        for (int i=0; i<numberOfLeaves; i++){
            for(int j=i+1; j<numberOfLeaves; j++){
                diff_distance = Math.abs(listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth());
                System.out.println(listOfLeaves.get(i).getNodeId() + " " + listOfLeaves.get(j).getNodeId() + " " + diff_distance);
                if (diff_distance > MAX_DIFF_DISTANCE){
                    balance = false;
                }
            }
        }
        return balance;
    }

    @Override
    public void checkInsertNode(int parentId, int childId) throws TreeException {
        super.checkInsertNode(parentId, childId);

        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        tmpBalancedTree.insertNode(parentId, childId);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

    public BalancedTree copyBalanceTree(BalancedTree oldTree) {
        ArrayList<Node> oldQueue = new ArrayList<Node>();
        BalancedTree newTree = new BalancedTree(this.getMaxDiffDistance());
        oldQueue.add(oldTree.getRootNode());
        Node newRoot = new Node(oldTree.getRootNode().getNodeId());
        newTree.setRootNode(newRoot);
        while (oldQueue.size() > 0) {
            Node tmp = oldQueue.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                for (Node childNode: tmp.getListOfChildren()) {
                    Node newChild = newTree.insertNode(tmp.getNodeId(), childNode.getNodeId());
                    oldQueue.add(childNode);
                }
            }
        }
        return newTree;
    }


    @Override
    public void checkDeleteNode(int oldNode) throws TreeException {
        super.checkDeleteNode(oldNode);
        BalancedTree tmpBalancedTree = copyBalanceTree(this);
        System.out.println("hello " + tmpBalancedTree.getRootNode().getNodeId());
        tmpBalancedTree.deleteNode(oldNode);

        if (!tmpBalancedTree.isBalanced()) {
            throw new TreeNotBalancedException("The inserted node will invade the balance property of tree.");
        }
    }

    public Node makeBalanceInsert(int newNodeVal) { // đổi tên từ makeBalance -> makeBalanceInsert
        ArrayList<Node> queue = new ArrayList<Node>();
        HashMap<Integer, Integer> depthLeaf = new HashMap<Integer, Integer>();

        int minDepth = 100;
        Node minNode = new Node(0);
        Node newRoot = new Node(this.getRootNode().getNodeId());
        queue.add(this.getRootNode());
        while (queue.size() > 0) {
            Node tmp = queue.remove(0);
            if (tmp.getListOfChildren().size() > 0) {
                queue.addAll(tmp.getListOfChildren());
            }
            if (tmp.getNumChildren() == 0) {
                if (minDepth > tmp.getDepth()) {
                    minDepth = tmp.getDepth();
                    minNode = tmp;
                }
            }
        }
        Node newNode = minNode.addChild(newNodeVal);
        return newNode;
    }

    public void makeBalanceDelete(int delId){

        //1. Delete trên cây gốc
        ArrayList<Node> queue = new ArrayList<Node>();
        if (this.getRootNode().getNodeId()==delId){
            this.setRootNode(null);
            return;
        }
        queue.add(this.getRootNode());
        Node tmp = null; // đây là node cha của node bị delete. Đây chính là nguyên nhân gây ra unbalanced
        boolean kdDel = false;
        while (queue.size()>0){
            tmp = queue.remove(0);
            if (tmp.getNumChildren() > 0){
                for (int i = 0; i < tmp.getNumChildren();i++){
                    if (tmp.getListOfChildren().get(i).getNodeId()==delId){
                        tmp.getListOfChildren().remove(i);
                        kdDel = true;
                        break;
                    }
                    queue.add(tmp.getListOfChildren().get(i));
                }
            }
            if (kdDel){
                break;
            }
        }
        System.out.println("1. Delete node tren cây gốc (Done)");
        this.printBFS();
        System.out.println("tmp.id = "+tmp.getNodeId());

        //2. Copy tree từ cây gốc
        System.out.println("Bắt đầu bước 2. của hàm makeBalanceDelete; tmp==null: ");
        BalancedTree copiedTree;
        copiedTree = this.copyTreeHao();
        //2.1. Make balanced cho cây copiedTree
        System.out.println("Trước khi make balance (xoay cây) cho cây copiedTree, cây copiedTree: "+copiedTree.isBalanced());
        copiedTree.printBFS();

        Node node = nodeMakeUnbalanced(tmp);
        while (true){
            if (node!=null){
                tmp = copiedTree.makeBalanced(tmp.getNodeId());
                if (copiedTree.isBalanced()){
                    break;
                }
                else{
                    node = nodeMakeUnbalanced(tmp);
                }
            }
            else if (tmp.isRootNode()){
                break;
            }
            else if (!tmp.isRootNode()){
                tmp = tmp.getParentNode();
                node = nodeMakeUnbalanced(tmp);
            }

        }

        System.out.println("Sau khi make balance (xoay cây) cho cây copiedTree, cây copiedTree: "+copiedTree.isBalanced());
        copiedTree.printBFS();

        //3. Copy từ cây copiedTree đã cân bằng sang cây this và hiển thị cây this lên GUI
        this.setRootNode(null);

        this.createTree(copiedTree.getRootNode().getNodeId());
        Node root = this.getRootNode();
        this.getTreeController().getScenePane().getChildren().add(root);
        queue =new ArrayList<Node>();

        queue.add(copiedTree.getRootNode());
        Node tmp_child;
        while (queue.size() > 0){
            tmp = queue.remove(0);
            if (tmp.getNumChildren() > 0 ){
                for (Node child : tmp.getListOfChildren()){
                    tmp_child= this.insertNode(tmp.getNodeId(), child.getNodeId());
                    this.getTreeController().getScenePane().getChildren().add(tmp_child);
                    this.getTreeController().getScenePane().getChildren().add(tmp_child.getParentLine());
                    queue.add(child);
                }

            }
        }

    }

    public BalancedTree copyTreeHao(){
        BalancedTree copiedTree = new BalancedTree(this.MAX_DIFF_DISTANCE);
        if (this.getRootNode()==null){
            System.out.println("COPY: this có root = null, copy root = null!");
            copiedTree.setRootNode(null);
            return copiedTree;
        }
        ArrayList<Node>  queue = new ArrayList<Node>();
        copiedTree.setRootNode(new Node(this.getRootNode().getNodeId()));
        queue.add(this.getRootNode());
        Node this_tmp;
        Node copy_tmp;
        while (queue.size()>0){
            this_tmp = queue.remove(0);
            copy_tmp = copiedTree.searchNode(this_tmp.getNodeId());
            if (this_tmp.getNumChildren() > 0){
                for (Node child : this_tmp.getListOfChildren()){
                    copy_tmp.addChild_NO_GUI(child.getNodeId());
                    queue.add(child);
                }
            }
        }
        return copiedTree;
    }

    public Node makeBalanced(int fromId){
        Node tmp = searchNode(fromId);

        Node node = nodeMakeUnbalanced(tmp);

        /*
        Đến đây, ta thu được:
        - tmp: node bị mất cân bằng
        - nodes: 2 node làm mất cân bằng
         */
        Node biggerNode = node;
        Node ancestorBigger = null;

        for (Node n : tmp.getListOfChildren()){
            if (n.isAncestor(biggerNode)){
                ancestorBigger = n;
                break;
            }

        }

        Node secondAncestorBigger = null;
        for (Node n : ancestorBigger.getListOfChildren()){
            if (n.isAncestor(biggerNode)){
                secondAncestorBigger = n;
                break;
            }
        }

        /*
        Bắt đầu xoay cây
         */
        for (int i=0; i < ancestorBigger.getNumChildren(); i++){
            if (ancestorBigger.getListOfChildren().get(i).equals(secondAncestorBigger)){
                continue;
            }
            tmp.addChildmakeBalanceDel(ancestorBigger.getListOfChildren().remove(i));
        }

        tmp.getListOfChildren().remove(ancestorBigger);
        if (tmp.getParentNode()==null){ // nếu tmp là rootNode
            this.setRootNode(ancestorBigger);
            ancestorBigger.addChildmakeBalanceDel(tmp);
            return ancestorBigger;
        }
        tmp.getParentNode().addChildmakeBalanceDel(ancestorBigger);
        tmp.getParentNode().getListOfChildren().remove(tmp);
        ancestorBigger.addChildmakeBalanceDel(tmp);
        return ancestorBigger;
    }

    public Node nodeMakeUnbalanced(Node root){
        Node node = null;
        ArrayList<Node> queue = new ArrayList<Node>();
        ArrayList<Node> listOfLeaves = new ArrayList<Node>();
        if (root.isLeaf()){
            return node;
        }

        queue.add(root);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.isLeaf()){
                        listOfLeaves.add(n);
                    }
                    queue.add(n);
                }
            }
        }

        for (int i = 0; i < listOfLeaves.size(); i++){
            for (int j = i; j < listOfLeaves.size(); j++){
                int diff = listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth();
                if (Math.abs(diff) > MAX_DIFF_DISTANCE){
                    if (diff > 0){
                        node = listOfLeaves.get(i);
                    }
                    else{
                        node = listOfLeaves.get(j);
                    }

                    return node; // node có depth lớn hơn.
                }
            }
        }
        return node;
    }
}




