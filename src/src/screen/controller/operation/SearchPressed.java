package src.screen.controller.operation;


import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import src.exception.NodeExistedException;
import src.exception.NodeFullChildrenException;
import src.exception.NodeNotExistsException;
import src.screen.controller.GenericTreeController;
import src.treedatastructure.GenericTree;
import src.treedatastructure.Node;

import java.util.ArrayList;
import java.util.Collections;

public class SearchPressed implements UserAction {
    private int intNodeVal;
    private GenericTreeController genericTreeController;
    private GenericTree genericTree;
    private Pane scenePane;

    public SearchPressed(GenericTree genericTree, GenericTreeController genericTreeController, Pane scenePane, int nodeVal) {
        this.genericTree = genericTree;
        this.genericTreeController = genericTreeController;
        this.scenePane = scenePane;
        this.intNodeVal = nodeVal;
    }

    @Override
    public void run() {
        Node nodeObject = genericTree.searchNode(intNodeVal);
        ArrayList<Node> search_direction = genericTree.getPathToRoot(nodeObject);
        search_direction.add(genericTree.getRootNode());
        Collections.reverse(search_direction);
        drawAnimationsSearch(search_direction);
        System.out.println("Search operation.");
    }

    @Override
    public void undo() {
        // do nothing
        System.out.println("Search operation undo.");
    }

    public void drawAnimationsSearch(ArrayList<Node> search_direction) {
        ArrayList<Line> listLines = new ArrayList<Line>();
        ArrayList<StackPane> listStackPane = new ArrayList<StackPane>();
        SequentialTransition seq = this.genericTreeController.drawAnimations(search_direction, listLines, listStackPane);
        seq.setOnFinished(e -> showPopupWindowSearch(listLines, listStackPane));
        seq.play();
    }

    private void showPopupWindowSearch(ArrayList<Line> listLines, ArrayList<StackPane> listPane) {
        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
        pauseTransition.setOnFinished(event -> {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Complete Searching Operation");
                alert.setHeaderText(null);
                alert.setContentText("Node required searching has been found.");

                ButtonType okayButton = new ButtonType("Okay");
                alert.getButtonTypes().setAll(okayButton);

                alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == okayButton) {
                        scenePane.getChildren().removeAll(listLines);
                        scenePane.getChildren().removeAll(listPane);
                    }
                });
            });
        });

        pauseTransition.play();
    }
}
