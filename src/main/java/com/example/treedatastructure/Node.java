package com.example.treedatastructure;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class Node extends StackPane {
    public static ArrayList<String> listValue = new ArrayList<String>();
    private String value;
    private ArrayList<Node> childNodes = new ArrayList<Node>();
    private Node parentNode;
    private Circle circle;
    private Text nodeValue;
    private Line parentLine;
    private int depth = 0;
    private int state = 1;
    private boolean leave;

    public Node(String value) {
        this.value = value;
        listValue.add(this.value);
        this.setPrefSize(60, 60);
        this.parentLine = new Line();

        circle = new Circle(30, Color.WHITE);
        circle.setStroke(Color.BLACK);
        this.getChildren().add(circle);

        nodeValue = new Text(value);
        this.getChildren().add(nodeValue);
        
        this.setLayoutX(200);
        this.setLayoutY(25);
        this.setVisible(true);
    }

    public void addChild(Node childNode) {
        this.addUpdate();
        childNode.setDepth(this.getDepth() + 1);

        childNode.setLayoutY(this.getLayoutY() + 100);
        if (this.childNodes.isEmpty()) {
            childNode.setLayoutX(this.getLayoutX());
        } else if (childNode.getDepth() == 1) {
            childNode.setLayoutX(this.childNodes.get(this.childNodes.size() - 1).getLayoutX() + 500);
        } else if (childNode.getDepth() == 2) {
            childNode.setLayoutX(this.childNodes.get(this.childNodes.size() - 1).getLayoutX() + 220);
        } else {
            childNode.setLayoutX(this.childNodes.get(this.childNodes.size() - 1).getLayoutX() + 80);
        }
        Line line = childNode.getParentLine();
        line.setLayoutX(this.getLayoutX() + 30);
        line.setLayoutY(this.getLayoutY() + 60);
        line.setEndX(childNode.getLayoutX() - this.getLayoutX());

        childNode.setLeave(true);
        this.setLeave(false);
        line.setEndY(40);

        this.childNodes.add(childNode);
        childNode.setParentNode(this);
    }

    public void deleteChild(String nodeValue) {
        Node.listValue.remove(Integer.valueOf(nodeValue));

        for (Node node : this.childNodes) {
            if (node.getValue().equals(nodeValue)) {
                this.childNodes.remove(node);
                break;
            }
        }
        if (childNodes.isEmpty()) {
            this.leave = true;
        }
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
            if (!currentNode.getChildNodes().isEmpty()) {
                for (Node node : currentNode.getChildNodes()) {
                    node.setLayoutX(node.getLayoutX() - distance);
                    node.getParentLine().setLayoutX(currentNode.getLayoutX() + 30);
                    node.getParentLine().setEndX(node.getLayoutX() - currentNode.getLayoutX());
                    queue.add(node);
                }
            }
            queue.remove(0);
        }
    }

    public void deleteUpdate() {
        ArrayList<Node> queue = new ArrayList<Node>();
        queue.add(this);
        Node currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.get(0);

            if (!currentNode.getChildNodes().isEmpty()) {
                for (Node node : currentNode.getChildNodes()) {
                    if (node.getDepth() == 1) {
                        node.setLayoutX(node.getLayoutX() + 250);
                    } else if (node.getDepth() == 2) {
                        node.setLayoutX(node.getLayoutX() + 110);
                    } else {
                        node.setLayoutX(node.getLayoutX() + 40);
                    }
                    node.getParentLine().setLayoutX(currentNode.getLayoutX() + 30);
                    node.getParentLine().setEndX(node.getLayoutX() - currentNode.getLayoutX());
                    queue.add(node);
                }
            }
            queue.remove(0);
        }
    }
    
    
    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node node = (Node) o;
            return node.value == this.value;
        }
        return false;
    }

    public void setChildNodes(ArrayList<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getValue() {
        return value;
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public Line getParentLine() {
        return parentLine;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setState(int state) {
        this.state = state;
        if (state == 0) {
            circle.setFill(Color.WHITE);
        } else if (state == 1) {
            circle.setFill(Color.LIGHTYELLOW);
        } else if (state == 2) {
            circle.setFill(Color.LIGHTBLUE);
        }
    }

    public void setValue(String value) {
        int valueIndex = Node.listValue.indexOf(this.value);
        Node.listValue.remove(valueIndex);
        this.value = value;
        this.nodeValue.setText(String.valueOf(value));
        Node.listValue.add(value);
    }

    public boolean isLeave() {
        return leave;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }
}


