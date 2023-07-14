package src.treedatastructure;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Node extends StackPane {
    /*
    Các attribute của Node (KHÔNG FXML)
     */
    private int nodeId;
    private ArrayList<Node> listOfChildren = new ArrayList<Node>();

    private int depth = 0;

    private Node parentNode = null;

    private int state;

//    ------------------------------------------
//    CIRCLE AND LINE ATTRIBUTES
    private double circleRadius = 30;
    private double strokeWidthCircle = 3.0;
    private double strokeWidthLine = 3.0;
    private Color colorCircle = Color.WHITE;

    private Color colorStrokeCircle = Color.BLACK;

    private Color colorStrokeLine = Color.BLACK;

    private Color colorStrokeText = Color.BLACK;

    private Color colorFontText = Color.ORANGE;

    private double strokeWidthText = 1.0;
//    ------------------------------------------
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
        this.setPrefSize(circleRadius, circleRadius);
        circle = new Circle(circleRadius, this.colorCircle);
        circle.setStrokeWidth(this.strokeWidthCircle);
        circle.setStroke(this.colorStrokeCircle);


        tfId = new Text(String.valueOf(nodeId));
        tfId.setStrokeWidth(this.strokeWidthText);
        tfId.setStroke(this.colorStrokeText);
        tfId.setFill(this.colorFontText);

        parentLine = new Line();

        this.getChildren().addAll(circle, tfId);

        // Hiep fixes
        this.setLayoutX(410);
        this.setLayoutY(55);
    }

    public Color getColorCircle() {return this.colorCircle;}
    public Color getColorStrokeText() {return this.colorStrokeText;}
    public Color getColorFontText() {return this.colorFontText;}
    public double getCircleRadius() {return this.circleRadius;}
    public double getStrokeWidthCircle() {return this.strokeWidthCircle;}
    public double getStrokeWidthLine() {return this.strokeWidthLine;}
    public Color getColorStrokeLine() {return this.colorStrokeLine;}
    public Color getColorStrokeCircle() {return this.colorStrokeCircle;}
    public double getStrokeWidthText() {return this.strokeWidthText;}
    public Text getTfId() {return this.tfId;}

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /*
    Hai method addChild()
     */

    public Node addChild(int childId){
        Node newNode = new Node(childId);
        addChild(newNode);
        return newNode;
    }

    public void addChild(Node childNode) {
        this.addUpdate();
        childNode.setDepth(this.getDepth() + 1);

        childNode.setLayoutY(this.getLayoutY() + 100);
        if (this.getListOfChildren().isEmpty()) {
            childNode.setLayoutX(this.getLayoutX());
        } else if (childNode.getDepth() == 1) {
            childNode.setLayoutX(this.getListOfChildren().get(this.getListOfChildren().size() - 1).getLayoutX() + 500);
        } else if (childNode.getDepth() == 2) {
            childNode.setLayoutX(this.getListOfChildren().get(this.getListOfChildren().size() - 1).getLayoutX() + 220);
        } else {
            childNode.setLayoutX(this.getListOfChildren().get(this.getListOfChildren().size() - 1).getLayoutX() + 80);
        }
        
        System.out.println(this.getLayoutX() + " " + this.getLayoutY());
        System.out.println(childNode.getLayoutX() + " " + childNode.getLayoutY());

        Line line = childNode.getParentLine();
        line.setStroke(this.colorStrokeLine);
        line.setStrokeWidth(this.strokeWidthLine);
        line.setStartX(this.getLayoutX() + this.circleRadius);
        line.setStartY(this.getLayoutY() + 2 * this.circleRadius);
        line.setEndX(childNode.getLayoutX() + this.circleRadius);
        line.setEndY(childNode.getLayoutY());
        System.out.println(line.getStartX() + " " + line.getStartY() + " " + line.getEndX() + " " + line.getEndY());

        this.getListOfChildren().add(childNode);
        childNode.setParentNode(this);
    }

    public void addUpdate() {
        ArrayList<Node> queue = new ArrayList<Node>();
        queue.add(this);
        Node currentNode;

        int distance;
        if (depth == 0) {
            distance = 250;
        } else if (depth == 1) {
            distance = 110;
        } else {
            distance = 40;
        }

        while (!queue.isEmpty()) {
            currentNode = queue.get(0);
            if (!currentNode.getListOfChildren().isEmpty()) {
                for (Node node : currentNode.getListOfChildren()) {
                    node.setLayoutX(node.getLayoutX() - distance);

                    Line line = node.getParentLine();
                    line.setStartX(currentNode.getLayoutX() + this.circleRadius);
                    line.setStartY(currentNode.getLayoutY() + 2 * this.circleRadius);
                    line.setEndX(node.getLayoutX() + this.circleRadius);
                    line.setEndY(node.getLayoutY());

                    queue.add(node);
                }
            }
            queue.remove(0);
        }
    }


    public void addChildMakeBalance(Node child){ // method này dùng cho makeBalance() trong BalancedTree
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


