package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import src.exception.*;
import src.screen.controller.operation.DeletePressed;
import src.screen.controller.operation.InsertPressed;
import src.treedatastructure.BalancedTree;
import src.treedatastructure.BinaryTree;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

public class BalancedTreeController extends GenericTreeController{

    public BalancedTreeController(Stage stage, String treeType) {
        super(stage, treeType);
    }

    @FXML
    protected void btnInsertPressed(ActionEvent event) throws NodeExistedException, NodeFullChildrenException, NodeNotExistsException {
        try {
            String node_val = tfNodeInsert.getText();
            String parent_val = tfParentInsert.getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);


            BalancedTree balancedTree = (BalancedTree) this.getTreeDataStructure();
            balancedTree.checkInsertNode(intParentVal, intNodeVal);


            InsertPressed insertPressed = new InsertPressed((BalancedTree) this.getTreeDataStructure(), this, scenePane, intNodeVal, intParentVal);
            insertPressed.run();
            history.add(insertPressed);

        } catch (NodeExistedException e) {
            showAlert("Node Already Exists", "The node with the given value already exists in the tree.");
        } catch (NodeFullChildrenException e) {
            showAlert("Node Full Children", "The parent node already has the maximum number of children allowed.");
        } catch (NodeNotExistsException e) {
            showAlert("Parent Node Not Found", "The parent node with the given value does not exist.");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid integer values for node and parent node.");
        } catch (TreeException e) {
            showAlert("Tree Exception", "An unexpected error occurred during insertion.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void btnDeletePressed(ActionEvent event) {
        try {
            String delNodeVal = tfNodeDelete.getText();
            int intDelNodeVal = Integer.parseInt(delNodeVal);


            BalancedTree balancedTree = (BalancedTree) this.getTreeDataStructure();
            balancedTree.checkDeleteNode(intDelNodeVal);

            DeletePressed deletePressed = new DeletePressed((BalancedTree) this.getTreeDataStructure(), scenePane, this, intDelNodeVal);
            deletePressed.run();
            history.add(deletePressed);



        } catch (NodeNotExistsException e) {
            showAlert("Node Not Found", "The node with the given value does not exist in the tree.");
        } catch (TreeNotBalancedException ex) {
            // Show a confirmation dialog before proceeding with deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Tree Not Balanced");
            alert.setHeaderText("Deleting the node may cause the tree to become unbalanced.");
            alert.setContentText("Do you want to proceed?");

            // Add Yes and Cancel buttons to the dialog
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.CANCEL);

            // Show the dialog and wait for the user's choice
            ButtonType choice = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (choice == ButtonType.YES) {

                //delete happens
            }
        } catch (TreeException e) {
            showAlert("Tree Exception", "An unexpected error occurred during deletion.");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid integer value for the node to delete.");
        } catch (NodeNotRemovableException e) {
            throw new RuntimeException(e);
        }
    }
}


