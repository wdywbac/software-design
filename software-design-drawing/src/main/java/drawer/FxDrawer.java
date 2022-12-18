package drawer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FxDrawer implements DrawingApi {
    private final long areaWidth = 500;
    private final long areaHeight = 500;
    private final List<Consumer<GraphicsContext>> shapes = new ArrayList<>();
    private final Canvas canvas = new Canvas(areaWidth, areaHeight);

    @Override
    public long getDrawingAreaWidth() {
        return areaWidth;
    }

    @Override
    public long getDrawingAreaHeight() {
        return areaHeight;
    }

    @Override
    public Point getCenter() {
        return new Point((int) (areaWidth / 2), (int) (areaHeight / 2));
    }

    @Override
    public void drawCircle(Point center, Long radius) {
        shapes.add(gc -> gc.fillOval(center.getX() - radius,
                center.getY() - radius,
                2.0 * radius,
                2.0 * radius));
    }

    @Override
    public void drawLine(Point from, Point to) {
        shapes.add(gc -> gc.strokeLine(from.getX(), from.getY(), to.getX(), to.getY()));
    }

    @Override
    public void draw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        shapes.forEach(shape -> shape.accept(gc));
        Drawer drawer = new Drawer();
        Platform.startup(() -> {
            Stage stage = new Stage();
            drawer.start(stage);
        });
    }

    private class Drawer extends Application {
        @Override
        public void start(Stage stage) {
            Group group = new Group();
            group.getChildren().add(canvas);
            stage.setScene(new Scene(group, areaWidth, areaHeight));
            stage.show();
        }
    }
}
