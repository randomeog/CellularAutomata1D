package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private Draw task;
    private GraphicsContext graphicsContext;

    private static int CANVAS_HEIGHT;
    private static int CANVAS_WIDTH;
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;




    private void fillChoiceBox(){
        choiceBox.getItems().add(30);
        choiceBox.getItems().add(60);
        choiceBox.getItems().add(90);
        choiceBox.getItems().add(120);
        choiceBox.getItems().add(225);
    }

    private void prepareButtons() {
        startButton.setOnAction(t -> {
            int chosenRule = (int) choiceBox.getValue();
            Rule rule = new Rule(chosenRule);
            int x = getWidth();
            int y = getHeight();
            try {
                runDrawingTask(rule, x, y);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stopButton.setOnAction(t -> {
            task.cancel();
            clearCanvas(graphicsContext);
            clearTextFields();
        });
    }

    private void clearCanvas(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
    }

    private void clearTextFields() {
        heightTextField.setText("");
        widthTextField.setText("");
    }

    private void runDrawingTask(Rule rule, int x, int y) throws InterruptedException {
        graphicsContext = canvas.getGraphicsContext2D();
        clearCanvas(graphicsContext);
        task = new Draw();
        task.setGc(graphicsContext);
        task.setRule(rule);
        task.setx(x);
        task.sety(y);
        task.setxDislocation((CANVAS_WIDTH - x) / 2);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }

    private int getHeight() {
        String input = heightTextField.getText();
        if (!input.isEmpty()) {
            int height = Integer.parseInt(input);
            if (height <= 0)
                return 1;
            if (height <= CANVAS_HEIGHT)
                return height;
        }
        return CANVAS_HEIGHT;
    }

    private int getWidth() {
        String input = widthTextField.getText();
        if (!input.isEmpty()) {
            int width = Integer.parseInt(input);
            if (width <= 0)
                return 1;
            if (width <= CANVAS_WIDTH)
                return width;
        }
        return CANVAS_WIDTH;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CANVAS_HEIGHT = (int) canvas.getHeight() / 5;
        CANVAS_WIDTH = (int) canvas.getWidth() / 5;
        fillChoiceBox();
        prepareButtons();
    }
}
