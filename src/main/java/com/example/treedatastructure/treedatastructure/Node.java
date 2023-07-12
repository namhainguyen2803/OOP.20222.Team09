package treedatastructure;

import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Node extends StackPane {
    /*
    Các attribute của Node (KHÔNG FXML)
     */
    private int nodeId;
    private ArrayList<Node> listOfChildren = new ArrayList<Node>();

    private int depth = 0;

    private Node parentNode = null;

    /*
    Các attribute của FXML
     */
    private double cirleRadius = 30;
    private Circle circle;
    private Text tfId;
    private Line parentLine;
    /**
     * Constructor
     * @param nodeId
     */
    public Node(int nodeId){
        this.nodeId = nodeId;

        //FXML
        circle = new Circle(cirleRadius, Color.WHITE);
        tfId = new Text(nodeId+"");
        parentLine = new Line();

        this.getChildren().addAll(circle, tfId);
    }

    /*
    Getter và Setter
     */
    public int getNodeId(){
        return this.nodeId;
    }

    public ArrayList<Node> getListOfChildren(){
        return this.listOfChildren;
    }

    public int getDepth() {
        return this.depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Node getParentNode(){
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Line getParentLine() {
        return parentLine;
    }

    public Circle getCircle(){
        return circle;
    }

    /*
    Hai method addChild()
     */
    public void addChild(int childId){
        Node child = new Node(childId);
        child.depth = this.depth +1;
        child.parentNode = this;
        listOfChildren.add(child);

        //FXML: setX,Y cho StackPane và cho parentLine
        double distanceX_from_parentNode = 3*cirleRadius*(this.getNumChildren()-3);
        double distanceY_from_parentNode = cirleRadius*4;
        child.setLayoutX(this.getLayoutX()+distanceX_from_parentNode);
        child.setLayoutY(this.getLayoutY()+distanceY_from_parentNode);
        child.getParentLine().setStartX(this.getLayoutX()+this.circle.getRadius());
        child.getParentLine().setStartY(this.getLayoutY()+this.circle.getRadius()*2);
        child.getParentLine().setEndX(child.getLayoutX()+this.circle.getRadius());
        child.getParentLine().setEndY(child.getLayoutY());
    }

    public void addChild(Node child){ // method này dùng cho makeBalance() trong BalancedTree
        child.depth = this.depth + 1;
        child.parentNode = this;
        listOfChildren.add(child);

        if (child.getNumChildren()>0){ // nếu là 1 subtree

            ArrayList<Node> queue = new ArrayList<Node>();
            queue.add(child);

            Node tmp;
            while (queue.size() > 0){
                tmp = queue.remove(0); // lấy node đầu tiên của queue

                if (tmp.getNumChildren() > 0) {
                    for (Node n : tmp.getListOfChildren()) {
                        n.depth = tmp.depth + 1;
                        queue.add(n);
                    }
                }
            }
        }
    }


    public int getNumChildren(){
        return this.listOfChildren.size();
    }

    public void updateId(int newId){
        this.nodeId = newId;
    }

    public boolean isLeaf(){
        return this.getNumChildren() == 0;
    }

    public boolean isAncestor(Node node){
        ArrayList<Node> queue = new ArrayList<Node>();

        queue.add(this);

        Node tmp;
        while (queue.size() > 0){
            tmp = queue.remove(0); // lấy node đầu tiên của queue

            if (tmp.getNumChildren() > 0) {
                for (Node n : tmp.getListOfChildren()) {
                    if (n.equals(node)){
                        return true;
                    }
                    queue.add(n);
                }
            }
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Node){
            Node tmp = (Node) obj;
            return tmp.nodeId ==this.nodeId;
        }
        return false;
    }


}
