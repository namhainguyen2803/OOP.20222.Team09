package src.screen.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MaxDiffDistancePopupController {

    @FXML
    private TextField tfMaxDiffDistance;

    private Stage stage;
    private int maxDiffDistance;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getMaxDiffDistance() {
        return maxDiffDistance;
    }

    @FXML
    private void onOkButtonPressed() {
        try {
            maxDiffDistance = Integer.parseInt(tfMaxDiffDistance.getText());
            stage.close();
        } catch (NumberFormatException e) {}
    }
}
