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
    private String[] tree = {"Generic Tree", "Binary Tree", "Balance Tree", "Balance Binary Tree"};
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
            case "Generic Tree":
                content = "- A generic tree is a tree data structure in which each node can have an arbitrary\n number of child nodes.\n"
                        + "- It does not impose any specific ordering or constraint on the child nodes.\n"
                        + "- It provides a flexible way to represent hierarchical relationships among elements.";
                break;
            case "Binary Tree":
                content = "- A binary tree is a tree data structure in which each node has at most two child nodes,\n referred to as the left child and the right child.\n"
                        + "- It follows the constraint that the left child node's value is less than the parent node's\n value, and the right child node's value is greater than or equal to the parent node's value.\n"
                        + "- Binary trees are commonly used for efficient searching, sorting, and traversal algorithms.";
                break;
            case "Balance Tree":
                content = "- A balanced tree is a tree data structure in which the heights of the left and right\n subtrees of any node differ by at most one.\n"
                        + "- It ensures that the tree remains balanced, which helps in maintaining efficient search,\n insertion, and deletion operations.\n"
                        + "- Popular examples of balanced trees include AVL trees and red-black trees.";
                break;
            case "Balance Binary Tree":
                content = "- A balanced binary tree combines the properties of both binary trees and balanced trees.\n"
                        + "- It is a binary tree in which the heights of the left and right subtrees of any node differ\n by at most one, and it follows the constraint of a binary tree.\n"
                        + "- It provides the benefits of both efficient searching and maintaining balance for better\n performance.";
                break;
            default:
                content = "No information available for the selected tree.";
                break;
        }

        contentTextArea.setText(content);


    }
}