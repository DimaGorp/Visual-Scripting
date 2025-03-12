package org.example.visualscripting;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private VBox sidebar; // Боковая панель с кнопками

    @FXML
    private AnchorPane workspace; // Рабочее поле

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка drag-and-drop для всех кнопок в боковой панели
        for (var node : sidebar.getChildren()) {
            if (node instanceof Button button) {
                String blockType = button.getText().replace(" Block", ""); // Извлекаем тип блока из текста кнопки
                setupDragAndDrop(button, blockType);
            }
        }

        // Настройка рабочего поля для принятия перетаскиваемых элементов
        workspace.setOnDragOver(event -> {
            if (event.getGestureSource() != workspace && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        workspace.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                String blockType = db.getString();
                createBlock(blockType, event.getX(), event.getY()); // Создаем блок в месте drop
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    // Метод для настройки перетаскивания кнопки
    private void setupDragAndDrop(Button button, String blockType) {
        button.setOnDragDetected(event -> {
            Dragboard db = button.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(blockType); // Передаем тип блока
            db.setContent(content);
            event.consume();
        });
    }

    // Метод для создания нового блока в рабочем поле
    private void createBlock(String blockType, double x, double y) {
        Rectangle block = new Rectangle(100, 50); // Прямоугольник для визуального отображения блока
        block.setFill(Color.LIGHTBLUE);
        block.setStroke(Color.BLACK);
        block.setLayoutX(x - 50); // Центрируем блок по X
        block.setLayoutY(y - 25); // Центрируем блок по Y

        Label label = new Label(blockType); // Метка с типом блока
        label.setLayoutX(x - 40); // Смещаем метку для центрирования текста
        label.setLayoutY(y - 15);

        workspace.getChildren().addAll(block, label); // Добавляем блок и метку в рабочее поле
    }
}