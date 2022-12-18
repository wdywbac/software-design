package drawer;

import model.Point;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class AwtDrawer extends Frame implements DrawingApi {
    private final long areaWidth = 500;
    private final long areaHeight = 500;
    private final ArrayList<Ellipse2D.Double> nodes = new ArrayList<>();
    private final ArrayList<Line2D.Double> edges = new ArrayList<>();

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
        nodes.add(new Ellipse2D.Double(center.getX() - radius,
                center.getY() - radius,
                2.0 * radius,
                2.0 * radius));
    }

    @Override
    public void drawLine(model.Point from, Point to) {
        edges.add(new Line2D.Double(from.getX(), from.getY(), to.getX(), to.getY()));
    }

    @Override
    public void draw() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        setSize((int) areaWidth, (int) areaHeight);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D ga = (Graphics2D) g;
        edges.forEach(ga::draw);
        nodes.forEach(ga::fill);
    }
}
