package model;

import drawer.DrawingApi;

public abstract class Graph {
    private final DrawingApi drawingApi;
    public Graph(DrawingApi drawingApi) {
        this.drawingApi = drawingApi;
    }

    public abstract void drawGraph();
    public abstract int getSize();

    public void draw() {
        drawGraph();
        drawingApi.draw();
    }

    void drawVertex(int i) {
        drawingApi.drawCircle(getVertexPosition(i), 10L);
    }

    void drawEdge(int a, int b) {
        drawingApi.drawLine(getVertexPosition(a), getVertexPosition(b));
    }

    private Point getVertexPosition(int i) {
        double t = 2 * Math.PI * i / getSize();
        long radius = Math.max(drawingApi.getDrawingAreaWidth(), drawingApi.getDrawingAreaHeight()) / 3;
        Point center = drawingApi.getCenter();
        return new Point((int) (center.getX() + radius * Math.cos(t)),
                (int) (center.getY() + radius * Math.sin(t)));
    }

}
