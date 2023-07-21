package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import src.exception.*;
import src.screen.controller.operation.InsertPressed;
import src.treedatastructure.BalancedBinaryTree;
import src.treedatastructure.BalancedTree;
import src.treedatastructure.GenericTree;

import java.util.Optional;

public class BalancedBinaryTreeController extends BalancedTreeController {

    public BalancedBinaryTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeType(treeType);
        this.setTreeDataStructure(new BalancedBinaryTree());
    }

    public BalancedBinaryTreeController(Stage stage, String treeType, int max_depth) {
        super(stage, treeType);
        this.setTreeType(treeType);
        this.setTreeDataStructure(new BalancedBinaryTree(max_depth));
    }

    @FXML
    @Override
    protected void btnInsertPressed(ActionEvent event) throws TreeException {

        String node_val = this.getTfNodeInsert().getText();
        String parent_val = this.getTfParentInsert().getText();
        int intNodeVal = Integer.parseInt(node_val);
        int intParentVal = Integer.parseInt(parent_val);

        try {

            this.getTreeDataStructure().checkInsertNode(intParentVal, intNodeVal);

            InsertPressed insertPressed = new InsertPressed(this.getTreeDataStructure(), this, this.getScenePane(), intNodeVal, intParentVal);

            insertPressed.run();

            this.getHistory().add(insertPressed);
        } catch (NodeExistedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Look likes the parent node does not exist.");

            alert.showAndWait();
        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the child node has existed.");

            alert.showAndWait();
        } catch (TreeNotBalancedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node invades the balance property of tree. Do you still want to insert it?");
            alert.showAndWait();
        } catch (NodeFullChildrenException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node invades the binary property of tree. Do you still want to insert it?");
            alert.showAndWait();

        }
        this.getTfNodeInsert().clear();
        this.getTfParentInsert().clear();
    }
}
