import drawer.AwtDrawer;
import drawer.DrawingApi;
import drawer.FxDrawer;
import model.Graph;
import model.ListGraph;
import model.MatrixGraph;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        DrawingApi api = (Objects.equals(args[0], "JavaFX")) ? new FxDrawer() : new AwtDrawer();
        Graph graph = (Objects.equals(args[1], "list"))
                ? new ListGraph(api, Path.of(args[2]))
                : new MatrixGraph(api, Path.of(args[2]));
        graph.draw();
    }
}
