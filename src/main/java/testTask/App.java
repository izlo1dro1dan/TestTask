/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package testTask;

import testTask.Exceptions.NotExistRouteException;
import testTask.Utils.Reader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class App {
    static List<Node> nodes;
    static List<Edge> edges;
    static DirectGraph dijkstra;

    public static void main(String[] args) {

        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
        String line = new Reader().reader();
        Graph graph = new Graph(nodes, edges);
        dijkstra = new DirectGraph(graph);
        for(String node : line.split(",")){
            String sourceName = node.substring(0,1);
            String destName = node.substring(1,2);
            int weight = Integer.parseInt(node.substring(2,3));
            Node source = new Node(sourceName);
            nodes.add(source);
            Node dest = new Node(destName);
            nodes.add(dest);
            Edge edge = new Edge(source,dest,weight);
            edges.add(edge);
            dijkstra.routeTable.put(source,edge);
        }
        edges.forEach(System.out::println);

    }

}
