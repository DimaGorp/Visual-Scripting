package org.example.visualscripting;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;


public class MainController {
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {

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