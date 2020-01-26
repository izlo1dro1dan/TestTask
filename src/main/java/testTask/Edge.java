package testTask;

import java.util.Comparator;

public class Edge  implements Comparable<Edge> {

    private final Node source;
    private final Node destination;
    private int weight;

    public Edge next;

    public Edge(Node source, Node destination, int weight) {

        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge(Node source, Node destination) {
        this.source = source;
        this.destination = destination;
    }

    public Node getDestination() {
        return destination;
    }

    public Node getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination+ " "+ weight;
    }

//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + source.hashCode();
//        result = prime * result + destination.hashCode();
//        return result;
//    }

    @Override
    public int compareTo(Edge o) {
        return Comparator.comparing(Edge::getSource)
                .thenComparing(Edge::getDestination)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (source != other.source && destination != other.destination)
            return false;
        if (source == other.source && destination == other.destination)
            return true;
        return true;
    }
    public Edge next(Edge edge) {
        this.next = edge;
        return this;
    }
}
