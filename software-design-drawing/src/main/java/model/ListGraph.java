package model;

import drawer.DrawingApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ListGraph extends Graph {
    private final List<Integer>[] graph;

    public ListGraph(DrawingApi drawingApi, Path path) throws IOException {
        super(drawingApi);
        this.graph = getGraph(path);
    }

    private List<Integer>[] getGraph(Path path) throws IOException {
        List<String> matrix = Files.readAllLines(path);
        int size = Integer.parseInt(matrix.get(0));
        ArrayList<Integer>[] res = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            res[i] = new ArrayList<>();
        }
        for (int i = 1; i < matrix.size(); i++) {
            String[] line = matrix.get(i).split(" ");
            int from = Integer.parseInt(line[0]);
            int to = Integer.parseInt(line[1]);
            res[from].add(to);
        }
        return res;
    }

    @Override
    public void drawGraph() {
        for (int a = 0; a < graph.length; a++) {
            drawVertex(a);
            for (int b : graph[a]) {
                drawEdge(a, b);
            }
        }
    }

    @Override
    public int getSize() {
        return graph.length;
    }
}
