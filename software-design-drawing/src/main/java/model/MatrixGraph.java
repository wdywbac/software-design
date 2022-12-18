package model;

import drawer.DrawingApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class MatrixGraph extends Graph {
    private final int[][] graph;

    public MatrixGraph(DrawingApi drawingApi, Path path) throws IOException {
        super(drawingApi);
        this.graph = getGraph(path);
    }

    private int[][] getGraph(Path path) throws IOException {
        List<String> matrix = Files.readAllLines(path);
        int size = Integer.parseInt(matrix.get(0));
        int[][] res = new int[size][size];
        for (int i = 1; i <= size; i++) {
            int[] values = Arrays.stream(matrix.get(i).split(" "))
                    .flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
                    .toArray();
            System.arraycopy(values, 0, res[i - 1], 0, size);
        }
        return res;
    }

    @Override
    public void drawGraph() {
        for (int a = 0; a < graph.length; a++) {
            drawVertex(a);
            for (int b = 0; b < graph.length; b++) {
                if (graph[a][b] == 1) {
                    drawEdge(a, b);
                }
            }
        }
    }

    @Override
    public int getSize() {
        return graph.length;
    }
}
