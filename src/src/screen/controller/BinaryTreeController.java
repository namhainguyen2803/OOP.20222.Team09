package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.exception.TreeException;
import src.screen.controller.operation.DeletePressed;
import src.screen.controller.operation.InsertPressed;
import src.treedatastructure.BinaryTree;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

public class BinaryTreeController extends GenericTreeController{
    public BinaryTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeDataStructure(new BinaryTree());
    }
    @FXML
    public void btnInsertPressed(ActionEvent event) {

        try {

            String node_val = tfNodeInsert.getText();
            String parent_val = tfParentInsert.getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);


            BinaryTree binaryTree = (BinaryTree) this.getTreeDataStructure();
            binaryTree.checkInsertNode(intParentVal, intNodeVal);


            InsertPressed insertPressed = new InsertPressed((BinaryTree) this.getTreeDataStructure(), this, scenePane, intNodeVal, intParentVal);

            insertPressed.run();

            history.add(insertPressed);

        } catch (NodeFullChildrenException e) {
            // Handle the exception
            showAlert("Exception", "Node Insertion Error", e.getMessage());
        } catch (NodeNotExistsException e) {
            // Handle the exception
            showAlert("Exception", "Node Insertion Error", e.getMessage());
        } catch (NumberFormatException e) {
            // Handle invalid input
            showAlert("Error", "Invalid Input", "Please enter valid node and parent IDs.");
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }
    }
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    protected void btnDeletePressed(ActionEvent event) {
        try {
            String delNodeVal = tfNodeDelete.getText();
            int intDelNodeVal = Integer.parseInt(delNodeVal);
            Node nodeToDelete = this.getTreeDataStructure().searchNode(intDelNodeVal);


            // Perform binary tree specific validation before deletion
            this.getTreeDataStructure().checkNodeExisted(intDelNodeVal);

            BinaryTree binaryTree = (BinaryTree) this.getTreeDataStructure();
            binaryTree.checkFullChildrenNode(intDelNodeVal);
            binaryTree.checkBinaryProperty(nodeToDelete);

            DeletePressed deletePressed = new DeletePressed((BinaryTree) this.getTreeDataStructure(), scenePane, this, intDelNodeVal);
            deletePressed.run();
            history.add(deletePressed);

        } catch (NumberFormatException e) {
            // Handle invalid number format
            showAlert("Invalid Input", "Please enter a valid integer value for the node ID.", e.getMessage());
        } catch (NodeExistedException | NodeFullChildrenException | NodeNotExistsException e) {
            // Handle binary tree specific exceptions
            showAlert("Tree Exception","Try to check again", e.getMessage());
        } catch (TreeException e) {
            throw new RuntimeException(e);
        }

        rebuildTree();
        tfNodeDelete.clear();
    }



}
