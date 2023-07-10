package com.example.treedatastructure;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("genericTree.fxml"));
        genericTreeController genericController = new genericTreeController();
        loader.setController(genericController);
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 500, 500);
        this.mainStage.setTitle("Generic Tree");
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("binaryTree.fxml"));
        genericTreeController genericController = new genericTreeController();
        loader.setController(genericController);
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 500, 500);
        this.mainStage.setTitle("Binary Tree");
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnBalancedTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("balancedTree.fxml"));
        genericTreeController genericController = new genericTreeController();
        loader.setController(genericController);
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 500, 500);
        this.mainStage.setTitle("Balanced Tree");
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnBalancedBinaryTreePressed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("balancedBinaryTree.fxml"));
        genericTreeController genericController = new genericTreeController();
        loader.setController(genericController);
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load(), 500, 500);
        this.mainStage.setTitle("Balanced Binary Tree");
        this.mainStage.setScene(scene);
        this.mainStage.show();
    }

    @FXML
    void btnExitPressed(ActionEvent event) {
        mainStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        mainStage.close();
    }
}