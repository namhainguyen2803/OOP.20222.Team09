package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.exception.TreeException;
import src.screen.controller.operation.CreatePressed;
import src.screen.controller.operation.DeletePressed;
import src.screen.controller.operation.InsertPressed;
import src.screen.controller.operation.UserAction;
import src.treedatastructure.BinaryTree;
import src.treedatastructure.GenericTree;

public class BinaryTreeController extends GenericTreeController{
    public BinaryTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeDataStructure(new BinaryTree());
    }

    @FXML
    @Override
    protected void btnInsertPressed(ActionEvent event) throws TreeException {

        try {

            String node_val = this.getTfNodeInsert().getText();
            String parent_val = this.getTfParentInsert().getText();
            int intNodeVal = Integer.parseInt(node_val);
            int intParentVal = Integer.parseInt(parent_val);

            this.getTreeDataStructure().checkInsertNode(intParentVal, intNodeVal);

            InsertPressed insertPressed = new InsertPressed((GenericTree) this.getTreeDataStructure(), this, this.getScenePane(), intNodeVal, intParentVal);

            insertPressed.run();

            this.getHistory().add(insertPressed);
        }

        catch (NodeExistedException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Look likes the parent node does not exist.");

            alert.showAndWait();
        }

        catch (NodeNotExistsException e){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the child node has existed.");

            alert.showAndWait();
        }

        catch (NodeFullChildrenException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node invades the binary property of tree. Do you still want to insert it?");

            ButtonType insertButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("No");
            alert.getButtonTypes().setAll(insertButton, cancelButton);
            alert.showAndWait().ifPresent(response -> {
                if (response == insertButton) {
                    // Handle the "Insert" button action here
                    System.out.println("Insert button clicked!");
                } else if (response == cancelButton) {
                    // Handle the "Cancel" button action here
                    System.out.println("Cancel button clicked!");
                }
            });
        }
        this.getTfNodeInsert().clear();
        this.getTfParentInsert().clear();
    }

}

