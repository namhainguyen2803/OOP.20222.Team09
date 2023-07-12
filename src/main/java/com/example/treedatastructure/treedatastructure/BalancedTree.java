package treedatastructure;

import exception.NodeExistedException;
import exception.NodeFullChildrenException;
import exception.NodeNotExistsException;

import javax.swing.*;
import java.util.ArrayList;

public class BalancedTree extends GenericTree{
    public static int MAX_DIFF_DISTANCE;

    public BalancedTree(int MAX_DIFF_DISTANCE){
        this.MAX_DIFF_DISTANCE = MAX_DIFF_DISTANCE;
    }

    public boolean isBalanced(){
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
        for (int i=0; i<numberOfLeaves; i++){
            for(int j=i+1; j<numberOfLeaves; j++){
                diff_distance = Math.abs(listOfLeaves.get(i).getDepth() - listOfLeaves.get(j).getDepth());
                if (diff_distance > MAX_DIFF_DISTANCE){
                    balance = false;
                }
            }
        }
        return balance;
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

    @Override
    public void insertNode(int parentId, int childId) throws NodeExistedException, NodeNotExistsException, NodeFullChildrenException {
        super.insertNode(parentId, childId);

        /*
        Nếu cây không balance thì làm 2 bước:
        1, Đưa ra thông báo:
        "Cây không balance.
        Bạn có muôn cây được khôi phục tính cân bằng không ?
        Nếu không, chúng tôi sẽ xoá node vừa được insert!"

        2, Hỏi ý kiến có make balance lại cho cây không ?
            - Nếu user bảo Có thì makeBalanced()
            - Nếu user bảo Ko thì delete node vừa add
         */

        if (!this.isBalanced()){
            String answer = JOptionPane.showInputDialog("WARNING: The tree is not balanced!\nDo you want to make it balanced again?\nIf you don't want, the inserted node will be deleted!");

            if (answer.equals("Yes")){
                this.makeBalanced(childId);
            }
            else{
                super.deleteNode(childId);
            }
        }
    }

    @Override
    public void deleteNode(int delId) throws NodeNotExistsException,NodeExistedException,NodeFullChildrenException {
        /*
        Kiểm tra nodeDel có tồn tại không ?
         */
        Node nodeDel = searchNode(delId);
        if (nodeDel == null) {
            throw new NodeNotExistsException("The node does not exist! Can't delete");
        }

        /*
        1, Check nếu delete liệu có làm mất tính balance?
            Để check, ta kiểm tra xem cha của nodeDel có bao nhiêu con, nếu chỉ có 1 con (Đây chỉ là ĐK cần) thì sẽ có thể mất balance
         */
        if (nodeDel.getParentNode().getNumChildren() == 1) { //thoả mãn ĐK cần thì sẽ check thử có mất balance không?
            BalancedTree tmpTree = copyAndDel(delId);
            boolean balanced = tmpTree.isBalanced();

            if (balanced){ // nếu vẫn balance thì ok delete
                super.deleteNode(delId);
                return;
            }
            else{ // nếu không balance thì đưa ra warning và hỏi ý kiến
                String answer = JOptionPane.showInputDialog("WARNING: Deleting the node can make the tree unbalanced\nDo you still want to delete?\nWARNING: If yes, the tree wil be made balanced and be changed!");
                if (answer.equals("Yes")){
                    super.deleteNode(delId);
                    this.makeBalanced(nodeDel.getParentNode().getNodeId());
                    return;
                }
                else {
                    return;
                }
            }

        }

        /*
        2, Nếu không thoả mãn ĐK cần để mất balance thì delete
         */
        super.deleteNode(delId);

    }

    public void makeBalanced(int fromId){
        Node tmp = searchNode(fromId);

        Node node = nodeMakeUnbalanced(tmp);
        while (node==null){
            tmp = tmp.getParentNode();
            node = nodeMakeUnbalanced(tmp);
        }
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
            tmp.addChild(ancestorBigger.getListOfChildren().remove(i));
        }

        tmp.getListOfChildren().remove(ancestorBigger);
        if (tmp.getParentNode()==null){ // nếu tmp là rootNode
            this.setRootNode(ancestorBigger);
            ancestorBigger.addChild(tmp);
            return;
        }
        tmp.getParentNode().addChild(ancestorBigger);
        tmp.getParentNode().getListOfChildren().remove(tmp);
        ancestorBigger.addChild(tmp);
    }

    public BalancedTree copyAndDel(int delId) throws NodeNotExistsException, NodeExistedException, NodeFullChildrenException{
        ArrayList<Node> queue = new ArrayList<Node>();
        BalancedTree tmpTree = new BalancedTree(this.MAX_DIFF_DISTANCE);

        tmpTree.createTree(this.getRootNode().getNodeId());
        queue.add(this.getRootNode());

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    tmpTree.insertNode(tmp.getNodeId(), n.getNodeId()); // print node tmp
                    queue.add(n);
                }
            }
        }

        tmpTree.delInCopy(delId);

        return tmpTree;
    }

    public void delInCopy(int delId) throws NodeFullChildrenException, NodeExistedException, NodeNotExistsException{
        super.deleteNode(delId);
    }
}
