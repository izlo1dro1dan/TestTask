package testTask;

import java.util.List;

public class Graph {
    private final List<Node> Nodees;
    private final List<Edge> edges;

    public Graph(List<Node> Nodes, List<Edge> edges) {
        this.Nodees = Nodes;
        this.edges = edges;
    }

    public List<Node> getNodes() {
        return Nodees;
    }

    public List<Edge> getEdges() {
        return edges;
    }

}
