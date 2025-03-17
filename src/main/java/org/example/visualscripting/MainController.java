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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

public class MainController implements Initializable {

    @FXML
    private VBox sidebar;

    @FXML
    private AnchorPane workspace;

    // Параметры отрисовки
    private final double baseX = 50;
    private final double baseY = 50;
    private final double gap = 100;
    private final double blockWidth = 100;
    private final double blockHeight = 50;

    // Структура потока: каждый узел – блок с типом, ссылкой на следующий в главной цепочке и (для If) на ветку true.
    private static class FlowNode {
        String type;
        FlowNode next;
        FlowNode trueChild; // для If-блока (истинная ветка)
        FlowNode(String type) {
            this.type = type;
        }
    }

    // Корень потока
    private FlowNode root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализируем поток: Start -> End
        root = new FlowNode("Start");
        FlowNode endNode = new FlowNode("End");
        root.next = endNode;

        // Настройка drag-and-drop для кнопок боковой панели.
        // Все кнопки в боковой панели предназначены для вставки в главную цепочку (если не перетаскиваются на drop-зону у If).
        for (var node : sidebar.getChildren()) {
            if (node instanceof Button button) {
                String buttonText = button.getText();
                String blockType = buttonText.replace(" Block", "");
                // При перетаскивании передаем тип блока
                button.setOnDragDetected(event -> {
                    Dragboard db = button.startDragAndDrop(TransferMode.COPY);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(blockType);
                    db.setContent(content);
                    event.consume();
                });
            }
        }

        // Рабочее поле – drop-цель для вставки в главную цепочку.
        workspace.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        workspace.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String blockType = db.getString();
                double dropY = event.getY();
                insertIntoMainBranch(blockType, dropY);
                updateFlow();
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });

        updateFlow();
    }

    // Вставка нового узла в главную цепочку по координате Y (drop в рабочее поле вне специальных зон).
    private void insertIntoMainBranch(String blockType, double dropY) {
        // Преобразуем главную цепочку в список для удобства.
        List<FlowNode> mainNodes = new ArrayList<>();
        FlowNode curr = root;
        while (curr != null) {
            mainNodes.add(curr);
            curr = curr.next;
        }
        // Определяем индекс вставки: не допускаем вставку до Start (индекс 0) и после End.
        int index = (int) Math.round((dropY - baseY) / gap);
        if (index < 1) index = 1;
        if (index > mainNodes.size() - 1) index = mainNodes.size() - 1;
        // Создаем новый узел и вставляем его.
        FlowNode newNode = new FlowNode(blockType);
        FlowNode prev = mainNodes.get(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    // Для drop-зоны (на полоске true у If) устанавливаем обработчики.
    private void setupDropZone(Rectangle dropZone, FlowNode ifNode) {
        dropZone.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                event.acceptTransferModes(TransferMode.COPY);
            }
            event.consume();
        });
        dropZone.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasString()) {
                String blockType = db.getString();
                // Устанавливаем узел истинной ветки для If-блока (при наличии нового блока заменяем старый, если он был)
                ifNode.trueChild = new FlowNode(blockType);
                updateFlow();
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
            event.consume();
        });
    }

    // Перерисовка рабочего поля по структуре потока.
    private void updateFlow() {
        workspace.getChildren().clear();
        // Собираем главную цепочку в список для отрисовки.
        List<FlowNode> mainNodes = new ArrayList<>();
        FlowNode curr = root;
        while (curr != null) {
            mainNodes.add(curr);
            curr = curr.next;
        }
        // Отрисовываем узлы главной цепочки.
        for (int i = 0; i < mainNodes.size(); i++) {
            FlowNode node = mainNodes.get(i);
            double y = baseY + i * gap;
            // Создаем прямоугольник для блока.
            Rectangle rect = new Rectangle(blockWidth, blockHeight);
            rect.setLayoutX(baseX);
            rect.setLayoutY(y);
            Color fill;
            if (node.type.equals("Start")) {
                fill = Color.LIGHTGREEN;
            } else if (node.type.equals("End")) {
                fill = Color.LIGHTCORAL;
            } else if (node.type.equals("If")) {
                fill = Color.LIGHTPINK;
            } else {
                fill = Color.LIGHTBLUE;
            }
            rect.setFill(fill);
            Label label = new Label(node.type);
            label.setLayoutX(baseX + 25);
            label.setLayoutY(y + 15);
            workspace.getChildren().addAll(rect, label);

            // Если текущий узел – If, обрабатываем истинную ветку.
            if (node.type.equals("If")) {
                double ifX = baseX;
                double ifY = y;
                // Зона для true branch (от левого нижнего угла If до начала промежутка следующего блока)
                double dropZoneX = ifX;
                double dropZoneY = ifY + blockHeight;
                double dropZoneWidth = 50;  // ширина зоны
                double dropZoneHeight = gap - blockHeight;
                if (node.trueChild != null) {
                    // Если есть истинная ветка, отрисовываем её с небольшим горизонтальным сдвигом (например, x = baseX + 150)
                    double trueX = baseX + 150;
                    double trueY = ifY + 25;
                    Rectangle trueRect = new Rectangle(blockWidth, blockHeight);
                    trueRect.setLayoutX(trueX);
                    trueRect.setLayoutY(trueY);
                    trueRect.setFill(Color.LIGHTBLUE);
                    Label trueLabel = new Label(node.trueChild.type);
                    trueLabel.setLayoutX(trueX + 25);
                    trueLabel.setLayoutY(trueY + 15);
                    workspace.getChildren().addAll(trueRect, trueLabel);
                    // Линия true: от If (нижний левый угол) к истинному блоку (верхний левый)
                    Line trueLine1 = new Line(ifX, ifY + blockHeight, trueX, trueY);
                    trueLine1.setStroke(Color.BLACK);
                    trueLine1.setStrokeWidth(2);
                    workspace.getChildren().add(trueLine1);
                    // Линия от истинного блока к следующему узлу главной цепочки (если он есть)
                    if (i < mainNodes.size() - 1) {
                        double nextY = baseY + (i + 1) * gap;
                        Line trueLine2 = new Line(trueX + blockWidth / 2, trueY + blockHeight, baseX + blockWidth / 2, nextY);
                        trueLine2.setStroke(Color.BLACK);
                        trueLine2.setStrokeWidth(2);
                        workspace.getChildren().add(trueLine2);
                    }
                    // Линия false: от If (нижний правый угол) напрямую к следующему узлу главной цепочки.
                    if (i < mainNodes.size() - 1) {
                        double nextY = baseY + (i + 1) * gap;
                        Line falseLine = new Line(ifX + blockWidth, ifY + blockHeight, baseX + blockWidth, nextY);
                        falseLine.setStroke(Color.BLACK);
                        falseLine.setStrokeWidth(2);
                        workspace.getChildren().add(falseLine);
                    }
                } else {
                    // Если истинная ветка отсутствует, отрисовываем прозрачную drop-зону на месте линии true.
                    Rectangle dropZone = new Rectangle(dropZoneWidth, dropZoneHeight);
                    dropZone.setLayoutX(dropZoneX);
                    dropZone.setLayoutY(dropZoneY);
                    // Полупрозрачный фон, чтобы визуально обозначить зону.
                    dropZone.setFill(Color.rgb(200, 200, 200, 0.3));
                    setupDropZone(dropZone, node);
                    workspace.getChildren().add(dropZone);
                    // Руководящая пунктирная линия для true ветки.
                    Line trueGuide = new Line(ifX, ifY + blockHeight, dropZoneX, dropZoneY);
                    trueGuide.getStrokeDashArray().addAll(5.0, 5.0);
                    trueGuide.setStroke(Color.GRAY);
                    workspace.getChildren().add(trueGuide);
                    // Линия false: от If (нижний правый угол) к следующему узлу главной цепочки.
                    if (i < mainNodes.size() - 1) {
                        double nextY = baseY + (i + 1) * gap;
                        Line falseLine = new Line(ifX + blockWidth, ifY + blockHeight, baseX + blockWidth, nextY);
                        falseLine.setStroke(Color.BLACK);
                        falseLine.setStrokeWidth(2);
                        workspace.getChildren().add(falseLine);
                    }
                }
            }
            // Если это не If, рисуем стандартную линию от нижнего центра текущего узла к верхнему центру следующего.
            if (i < mainNodes.size() - 1 && !mainNodes.get(i).type.equals("If")) {
                double startX = baseX + blockWidth / 2;
                double startY = y + blockHeight;
                double endX = baseX + blockWidth / 2;
                double endY = baseY + (i + 1) * gap;
                Line line = new Line(startX, startY, endX, endY);
                line.setStroke(Color.BLACK);
                line.setStrokeWidth(2);
                workspace.getChildren().add(line);
            }
        }
    }
}
