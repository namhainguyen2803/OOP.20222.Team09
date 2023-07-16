package src.screen.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.treedatastructure.BinaryTree;

import java.io.IOException;
import java.util.Optional;

public class mainWindowController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnGenericTree;

    @FXML
    private Button btnBinaryTree;

    @FXML
    private Button btnBalancedTree;

    @FXML
    private Button btnBalancedBinaryTree;

    private Stage mainStage;


    @FXML
    void btnGenericTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        GenericTreeController genericController = new GenericTreeController(mainStage, "Generic Tree Visualizer");
        loader.setController(genericController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Generic Tree");
        this.mainStage.setScene(scene);
//        this.mainStage.setFullScreen(true);
        this.mainStage.show();
    }

    @FXML
    void btnBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        BinaryTreeController binaryController = new BinaryTreeController(mainStage, "Binary Tree Visualizer");
        loader.setController(binaryController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Binary Tree");
//        this.mainStage.setFullScreen(true);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }
    @FXML
    void btnBalancedTreePressed(ActionEvent event) throws IOException {
        // Create a TextInputDialog to get the MAX_DIFF_DISTANCE value
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(null);
        dialog.setHeaderText("Enter the maximum difference in distance\n from root of the leaf nodes");
        dialog.setContentText("MAX_DIFF_DISTANCE:");
        dialog.setGraphic(null);

        // Show the dialog and wait for the user's input
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            // Parse the user's input to an integer
            try {
                int maxDiffDistance = Integer.parseInt(result.get());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/GenericTree.fxml"));
                mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                GenericTreeController genericController = new GenericTreeController(mainStage, "Balanced Tree Visualizer");
                loader.setController(genericController);
                Scene scene = new Scene(loader.load(), 1024, 768);
                this.mainStage.setTitle("Balanced Tree");
                // Set the MAX_DIFF_DISTANCE value in the genericController
                this.mainStage.setTitle("Balanced Tree");
                this.mainStage.setScene(scene);
                this.mainStage.show();
            } catch (NumberFormatException e) {
                // Show an error message if the user didn't enter a valid integer
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter a valid integer.", ButtonType.OK);
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Handle the case where the user cancels the input
            // You can add any appropriate action here if needed
        }
    }

    @FXML
    void btnBalancedBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/screen/fxml/GenericTree.fxml"));
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        GenericTreeController genericController = new GenericTreeController(mainStage, "Balanced Binary Tree Visualizer");
        loader.setController(genericController);
        Scene scene = new Scene(loader.load(), 1024, 768);
        this.mainStage.setTitle("Balanced Binary Tree");
//        this.mainStage.setFullScreen(true);
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnExitPressed(ActionEvent event) {
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        mainStage.close();
    }
}