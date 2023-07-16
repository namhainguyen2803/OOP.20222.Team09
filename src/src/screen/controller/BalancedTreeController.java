package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import src.exception.*;
import src.screen.controller.operation.DeletePressed;
import src.screen.controller.operation.InsertPressed;
import src.treedatastructure.BalancedTree;
import src.treedatastructure.GenericTree;

import java.util.Optional;

public class BalancedTreeController extends GenericTreeController {

    public BalancedTreeController(Stage stage, String treeType) {
        super(stage, treeType);
        this.setTreeType(treeType);
    }

    @FXML
    @Override
    protected void initialize() {
        this.getStackPaneInput().setVisible(false);
        this.getStackPanePseudo().setVisible(false);
        this.getStackPaneController().setVisible(false);
        this.getMainLabel().setText(this.getTreeType());

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(null);
        dialog.setHeaderText("Enter the maximum difference in distance\n from root of the leaf nodes");
        dialog.setContentText("MAX_DIFF_DISTANCE:");
        dialog.setGraphic(null);

        Optional<String> result = dialog.showAndWait();

        int max_depth = Integer.parseInt(result.get());

        BalancedTree balancedTree = new BalancedTree(max_depth);

        this.setTreeDataStructure(balancedTree);
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

        catch (TreeNotBalancedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node invades the balance property of tree. Do you still want to insert it?");
            alert.showAndWait();
        }
        this.getTfNodeInsert().clear();
        this.getTfParentInsert().clear();
    }

    @FXML
    protected void btnDeletePressed(ActionEvent event) throws TreeException {
        String delNodeVal = this.getTfNodeDelete().getText();

        int intDelNodeVal = Integer.parseInt(delNodeVal);
        try {
            this.getTreeDataStructure().checkDeleteNode(intDelNodeVal);
            DeletePressed deletePressed = new DeletePressed((GenericTree) this.getTreeDataStructure(), this.getScenePane(), this, intDelNodeVal);
            deletePressed.run();
            this.getHistory().add(deletePressed);

        } catch (NodeNotExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the node you want to delete does not exist.");

            alert.showAndWait();
        }
        catch (TreeNotBalancedException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Exception");
            alert.setHeaderText(null);
            alert.setContentText("Looks like the inserted node will invade the balance property of tree..");
            alert.showAndWait();
        }
        this.getTfNodeDelete().clear();
    }


}
