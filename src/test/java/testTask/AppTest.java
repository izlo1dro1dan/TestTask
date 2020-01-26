package testTask;

import org.junit.BeforeClass;
import org.junit.Test;
import testTask.Exceptions.NotExistRouteException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class AppTest {

    static List<Node> nodes;
    static List<Edge> edges;
    static DirectGraph dijkstra;

    @BeforeClass
    public static void setUpBeforeClass(){
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();

        Node A = new Node("A");
        nodes.add(A);
        Node B = new Node("B");
        nodes.add(B);
        Node C = new Node("C");
        nodes.add(A);

        Node D = new Node("D");
        nodes.add(D);
        Node E = new Node("E");
        nodes.add(E);

        addLane(A, B, 5);
        addLane(B, C, 4);
        addLane(C, D, 8);
        addLane(D, C, 8);
        addLane(D, E, 6);
        addLane(A, D, 5);
        addLane(C, E, 2);
        addLane(E, B, 3);
        addLane(A, E, 7);
        Graph graph = new Graph(nodes, edges);
        dijkstra = new DirectGraph(graph);

        dijkstra.routeTable.put(A, new Edge(A, B, 5).next(new Edge(A, D, 5).next(new Edge(A, E, 7))));
        dijkstra.routeTable.put(B, new Edge(B, C, 4));
        dijkstra.routeTable.put(C, new Edge(C, D, 8).next(new Edge(C, E, 2)));
        dijkstra.routeTable.put(D, new Edge(D, C, 8).next(new Edge(D, E, 6)));
        dijkstra.routeTable.put(E, new Edge(E, B, 3));
    }

    @Test
    public void testOneFive(){
        try{
            List<Node> list = new LinkedList<>();
            list.add(new Node("A"));
            list.add(new Node("B"));
            list.add(new Node("C"));
            System.out.println("Calc distance " + dijkstra.calcDistance(list));
        }catch (NotExistRouteException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSixSeven(){
        try {
            System.out.println("Max stops " + dijkstra.findRoutes(new Node("C"),new Node("C"),0,3));
        }catch (NotExistRouteException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testEightNine() {

        try {
            System.out.println("Shortest route " + dijkstra.shortestRoute(new Node("B"), new Node("B")));
        } catch (NotExistRouteException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testTen() {
        try{

            System.out.println("Numbers of different routes "+ dijkstra.numRoutesWithin(new Node("C"),new Node("C"),30));
        }catch (NotExistRouteException e){
            System.out.println(e.getMessage());
        }
    }

    private static void addLane(Node source, Node dest, int duration) {
        Edge lane = new Edge(source, dest, duration );
        edges.add(lane);
    }
}