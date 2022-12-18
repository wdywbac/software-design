package drawer;

import model.Point;

public interface DrawingApi {
    long getDrawingAreaWidth();
    long getDrawingAreaHeight();
    Point getCenter();
    void drawCircle(Point center, Long radius);
    void drawLine(Point from, Point to);
    void draw();
}
