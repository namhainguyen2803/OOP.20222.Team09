package src.screen.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HelpController implements Initializable {

    @FXML
    private ChoiceBox<String> ChoiceBox;
    private String[] tree = {"Overview","Basic Usage and Aim", "Generic Tree", "Binary Tree", "Balanced Tree", "Balanced Binary Tree"};
    @FXML
    private Text title;
    @FXML
    private TextArea contentTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChoiceBox.getItems().addAll(tree);
        ChoiceBox.setOnAction(this::getTree);
    }
    public void getTree(ActionEvent event){
        String myTree = ChoiceBox.getValue();
        title.setText(myTree);

        String content;
        switch (myTree) {
            case "Overview":
                content = "- A tree is a useful data structure with various applications in computer science.\n" +
                        "- It is a nonlinear hierarchical structure consisting of nodes connected by edges.\n" +
                        "- The project focuses on four types of trees: generic tree, binary tree, balanced tree,\n and balanced binary tree.\n" +
                        "- Each type has its own characteristics and specific operations.";
                break;
            case "Basic Usage and Aim":
                content = "- The project aims to design a program to display and explain basic operations on four types of trees.\n" +
                        "- The user can create, insert, delete, update, and traverse nodes in the selected tree type.\n" +
                        "- The program provides a visualization of the tree structure and the execution of each operation.";
                break;
            case "Generic Tree":
                content = "- A tree is a nonlinear hierarchical data structure\n" +
                        "that consists of nodes connected by edges and contains no cycles.\n"
                        + "- It does not impose any specific ordering or constraint on the child nodes.\n"
                        + "- It provides a flexible way to represent hierarchical relationships among elements.";
                break;
            case "Binary Tree":
                content = "- A binary tree is a tree data structure in which each node has at most two child nodes,\n referred to as the left child and the right child.\n"
                        + "- It follows the constraint that the left child node's value is less than the parent node's\n value, and the right child node's value is greater than or equal to the parent node's value.\n"
                        + "- Binary trees are commonly used for efficient searching, sorting, and traversal algorithms.";
                break;
            case "Balanced Tree":
                content = "- A balanced tree is a tree where each leaf node is “not more than a\n" +
                        "certain distance” away from the root than any other leaf.\n"
                        + "- It ensures that the tree remains balanced, which helps in maintaining efficient search,\n insertion, and deletion operations.\n"
                        + "- Popular examples of balanced trees include AVL trees and red-black trees.";
                break;
            case "Balanced Binary Tree":
                content = "- A balanced binary tree combines the properties of both binary trees and balanced trees.\n"
                        + "- It provides the benefits of both efficient searching and maintaining balance for better\n performance.";
                break;
            default:
                content = "No information available for the selected tree.";
                break;
        }

        contentTextArea.setText(content);


    }
}