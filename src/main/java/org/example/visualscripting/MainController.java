package org.example.visualscripting;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onInputBlockClick() {
        welcomeText.setText("Input Block Clicked!");
    }

    @FXML
    protected void onPrintBlockClick() {
        welcomeText.setText("Print Block Clicked!");
    }
}