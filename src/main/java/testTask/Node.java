package testTask;


import testTask.Exceptions.NotExistRouteException;

import java.util.Comparator;

public class Node implements Comparable<Node>{
    private String name;
    public boolean visited;
    public Node(String name) { this.name = name; }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {return name; }

    @Override
    public int hashCode() {
        if(this.name == null) return 0;
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Node o) {
        return Comparator.comparing(Node::getName)
                .compare(this, o);
    }
}
