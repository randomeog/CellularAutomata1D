package sample;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Draw extends Task {

    private Rule rule;
    private GraphicsContext graphicContext;
    private Board simulation;
    private int x;
    private int y;
    private int xDislocation;

    public Draw() {
        simulation = new Board();
    }



    @Override
    protected Object call() throws Exception {
        boolean[][] board = simulation.simulation(rule, x, y);
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (isCancelled()) {
                    break;
                }
                if (board[i][j] == true) {
                    paintPixel(j, i, graphicContext);
                }
            }
        }
        return 0;
    }

    private void paintPixel(int i, int j, GraphicsContext graphicContext) {
        graphicContext.setFill(Color.BLACK);
        graphicContext.fillRect(5 * (xDislocation + i), 5 * j, 5, 5);
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    public void setxDislocation(int xDislocation) {
        this.xDislocation = xDislocation;
    }
    public void setGc(GraphicsContext graphicContext) {
        this.graphicContext = graphicContext;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}