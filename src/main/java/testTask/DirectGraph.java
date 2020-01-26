package testTask;


import testTask.Exceptions.NotExistRouteException;

import java.util.*;

public class DirectGraph {

    public Hashtable<Node,Edge> routeTable = new Hashtable<Node,Edge>();
    private List<Node> nodes;
    private List<Edge> edges;
    private Set<Node> settledNodes;
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;
    private Node source;

    public DirectGraph(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    public void execute(Node source) {
        settledNodes = new HashSet<Node>();
        unSettledNodes = new HashSet<Node>();
        distance = new HashMap<Node, Integer>();
        predecessors = new HashMap<Node, Node>();
        distance.put(source, 0);
        this.source = source;
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            Node node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(Node node) {
        List<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }
    private int getDistance(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private Node getMinimum(Set<Node> Nodes) {
        Node minimum = null;
        for (Node node : Nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(minimum)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }
    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    public int getShortestDistance(Node destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    public LinkedList<Node> getPath(Node target) {

        LinkedList<Node> path = new LinkedList<Node>();

        Node step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    public int calcDistance(List<Node> list) throws NotExistRouteException {
        int distance = 0;
        int step = 0;
        if(list.size()==0 || list.size()==1){
            System.out.println("Too shot");
            return distance;
        }
        for(int i = 0; i<list.size()-1;i++){
            Node source = list.get(i);
            Node dest = list.get(i+1);
            for(Edge edge: edges){
                if(edge.getSource().equals(source) && edge.getDestination().equals(dest)){
                    distance +=edge.getWeight();
                    step++;
                }
            }
        }
        if(step<list.size()-1){
            throw new NotExistRouteException();
        }

        return distance;
    }
    public int findRoutes(Node start, Node end, int depth, int maxStops) throws NotExistRouteException{
        int routes = 0;
        if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            depth++;
            if(depth > maxStops)
                return 0;
            start.visited = true;
            Edge edge = this.routeTable.get(start);
            while(edge != null) {
                if(edge.getDestination().equals(end)) {
                    routes++;
                    edge = edge.next;
                    continue;
                }
                else if(!edge.getDestination().visited) {
                    routes += findRoutes(edge.getDestination(), end, depth, maxStops);
                    depth--;
                }
                edge = edge.next;
            }
        }
        else
            throw new NotExistRouteException();
        start.visited = false;
        return routes;
    }

    public int shortestRoute(Node source, Node dest) throws NotExistRouteException{
        return findShortestRoute(source,dest,0,0);
    }

    private int findShortestRoute(Node start, Node end, int weight, int shortestRoute) throws NotExistRouteException{
        if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            start.visited = true;
            Edge edge = this.routeTable.get(start);
            while(edge != null) {
                if(edge.getDestination() == end || !edge.getDestination().visited)
                    weight += edge.getWeight();

                if(edge.getDestination().equals(end)) {
                    if(shortestRoute == 0 || weight < shortestRoute)
                        shortestRoute = weight;
                    start.visited = false;
                    return shortestRoute;
                }
                else if(!edge.getDestination().visited) {
                    shortestRoute = findShortestRoute(edge.getDestination(), end, weight, shortestRoute);
                    weight -= edge.getWeight();
                }
                edge = edge.next;
            }
        }
        else
            throw new NotExistRouteException();
        start.visited = false;
        return shortestRoute;

    }

    public int numRoutesWithin(Node start, Node end, int maxDistance) throws NotExistRouteException {
        return findnumRoutesWithin(start, end, 0, maxDistance);
    }
    private int findnumRoutesWithin(Node start, Node end, int weight, int maxDistance) throws NotExistRouteException{
        int routes = 0;
        if(this.routeTable.containsKey(start) && this.routeTable.containsKey(end)) {
            Edge edge = this.routeTable.get(start);
            while(edge != null) {
                weight += edge.getWeight();
                if(weight <= maxDistance) {
                    if(edge.getDestination().equals(end)) {
                        routes++;
                        routes += findnumRoutesWithin(edge.getDestination(), end, weight, maxDistance);
                        edge = edge.next;
                        continue;
                    }
                    else {
                        routes += findnumRoutesWithin(edge.getDestination(), end, weight, maxDistance);
                        weight -= edge.getWeight();
                    }
                }
                else
                    weight -= edge.getWeight();

                edge = edge.next;
            }
        }
        else
            throw new NotExistRouteException();

        return routes;

    }
}
