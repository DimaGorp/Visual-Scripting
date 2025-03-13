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

import org.example.visualscripting.blocks.*;
import org.example.visualscripting.JavaCodeGenerator.JavaCodeGenerator;
import org.example.visualscripting.controllers.BlockController;

public class MainController implements Initializable {

    @FXML
    private VBox sidebar; // Боковая панель с кнопками

    @FXML
    private AnchorPane workspace; // Рабочее поле

    private final BlockController blockController = new BlockController();
    private final JavaCodeGenerator JCG = new JavaCodeGenerator();
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Настройка drag-and-drop для всех кнопок в боковой панели
        for (var node : sidebar.getChildren()) {
            if (node instanceof Button button) {
                String buttonText = button.getText();

                if (buttonText.equals("Save All")) {
                    button.setOnAction(event -> saveAll());
                } else if (buttonText.equals("Load All")) {
                    button.setOnAction(event -> loadAll());
                } else if (buttonText.equals("Translate into code")) {
                    button.setOnAction(event -> translateCode());
                } else {
                    String blockType = buttonText.replace(" Block", ""); 
                    setupDragAndDrop(button, blockType);
                }
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
        Block newBlock = createBlockInstance(blockType);
        if (newBlock != null) {
            blockController.addBlock(newBlock);
        }
    }
     private Block createBlockInstance(String blockType) {
        System.out.println(blockType);
        return switch (blockType) {
            case "Print" -> new PrintValueBlock();
            case "If" -> new IfValueBlock("equel",2);
            case "Input" -> new InputValueBlock();
            case "Assignment" -> new ValueToValueBlock();
            case "Constant Assignment" -> new ValueToCValueBlock(2);
            case "Condition" -> new LogicalEqualBlock();
            default -> null; // Handle unknown block types
        };
    }
    private void saveAll() {
        System.out.println("Saving all blocks...");
        // TODO: Implement saving logic
    }
    
    private void loadAll() {
        System.out.println("Loading all blocks...");
        // TODO: Implement loading logic
    }
    
    private void translateCode() {
        System.out.println("Translating to code...");
        JCG.generateAllBlockLogic(blockController.getBlocks());
    }
}