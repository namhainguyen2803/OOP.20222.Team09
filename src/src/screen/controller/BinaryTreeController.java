package src.screen.controller;

import javafx.stage.Stage;
import src.treedatastructure.BinaryTree;
import src.treedatastructure.GenericTree;

public class BinaryTreeController extends GenericTreeController{
    public BinaryTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeDataStructure(new BinaryTree());
    }
}
