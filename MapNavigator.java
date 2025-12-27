import java.util.*;

/**
 * SMART MAP NAVIGATOR - National Hub Edition
 * A professional implementation of Dijkstra's Algorithm for Google Portfolio.
 */
public class MapNavigator {
    public static void main(String[] args) {
        Graph map = new Graph();

        // 1. Setup the National Highway Network (15+ Mega-Cities)
        // NORTH INDIA
        map.addRoad("Delhi", "Jaipur", 280);
        map.addRoad("Delhi", "Chandigarh", 243);
        map.addRoad("Delhi", "Lucknow", 550);
        map.addRoad("Delhi", "Nagpur", 1000);

        // WEST INDIA
        map.addRoad("Jaipur", "Ahmedabad", 670);
        map.addRoad("Ahmedabad", "Mumbai", 530);
        map.addRoad("Mumbai", "Pune", 150);
        map.addRoad("Mumbai", "Nagpur", 800);

        // SOUTH INDIA
        map.addRoad("Mumbai", "Bangalore", 980);
        map.addRoad("Pune", "Hyderabad", 560);
        map.addRoad("Bangalore", "Chennai", 350);
        map.addRoad("Hyderabad", "Chennai", 630);
        map.addRoad("Bangalore", "Hyderabad", 570);
        map.addRoad("Nagpur", "Hyderabad", 500);

        // EAST & CENTRAL INDIA
        map.addRoad("Lucknow", "Kolkata", 980);
        map.addRoad("Kolkata", "Bhubaneswar", 440);
        map.addRoad("Bhubaneswar", "Chennai", 1200);
        map.addRoad("Kolkata", "Nagpur", 1100);

        // 2. Interactive User Interface
        Scanner scanner = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("      INDIA NATIONAL HIGHWAY NAVIGATOR        ");
        System.out.println("==============================================");
        System.out.println("Available Hubs: " + map.cities.keySet());
        System.out.println("----------------------------------------------");

        try {
            System.out.print("Enter Starting City: ");
            String start = scanner.nextLine().trim();

            System.out.print("Enter Destination City: ");
            String end = scanner.nextLine().trim();

            // 3. Execute Pathfinding
            map.findShortestPath(start, end);
        } catch (Exception e) {
            System.out.println("Error: Please enter valid city names.");
        } finally {
            scanner.close();
        }
    }
}

/**
 * Represents a road connection between two nodes.
 */
class Edge {
    String targetCity;
    int distance;

    public Edge(String targetCity, int distance) {
        this.targetCity = targetCity;
        this.distance = distance;
    }
}

/**
 * Represents a City (Node). 
 * Implements Comparable for the PriorityQueue (Min-Heap).
 */
class CityNode implements Comparable<CityNode> {
    String name;
    List<Edge> edges;
    int minDistance = Integer.MAX_VALUE;
    CityNode parent; // For path backtracking

    public CityNode(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
    }

    @Override
    public int compareTo(CityNode other) {
        return Integer.compare(this.minDistance, other.minDistance);
    }
}

/**
 * Manages the graph structure and pathfinding logic.
 */
class Graph {
    Map<String, CityNode> cities = new HashMap<>();

    public void addCity(String name) {
        cities.putIfAbsent(name, new CityNode(name));
    }

    public void addRoad(String source, String destination, int distance) {
        addCity(source);
        addCity(destination);
        // Bidirectional edges representing two-way highways
        cities.get(source).edges.add(new Edge(destination, distance));
        cities.get(destination).edges.add(new Edge(source, distance));
    }

    public void findShortestPath(String startName, String endName) {
        if (!cities.containsKey(startName) || !cities.containsKey(endName)) {
            System.out.println("CRITICAL ERROR: City not found in the national database.");
            return;
        }

        // Reset state for search
        for (CityNode n : cities.values()) {
            n.minDistance = Integer.MAX_VALUE;
            n.parent = null;
        }
        
        CityNode startNode = cities.get(startName);
        startNode.minDistance = 0;

        // Dijkstra's Algorithm with Min-Heap Optimization
        PriorityQueue<CityNode> pq = new PriorityQueue<>();
        pq.add(startNode);

        while (!pq.isEmpty()) {
            CityNode current = pq.poll();

            for (Edge edge : current.edges) {
                CityNode neighbor = cities.get(edge.targetCity);
                int totalDist = current.minDistance + edge.distance;

                if (totalDist < neighbor.minDistance) {
                    pq.remove(neighbor); 
                    neighbor.minDistance = totalDist;
                    neighbor.parent = current;
                    pq.add(neighbor);
                }
            }
        }

        displayResult(startName, endName);
    }

    private void displayResult(String startName, String endName) {
        CityNode endNode = cities.get(endName);
        if (endNode.minDistance == Integer.MAX_VALUE) {
            System.out.println("No highway connectivity found between these regions.");
        } else {
            System.out.println("\n--- ROUTE OPTIMIZATION COMPLETE ---");
            System.out.println("Total Travel Distance: " + endNode.minDistance + " km");
            
            // Reconstruct path
            List<String> path = new ArrayList<>();
            for (CityNode at = endNode; at != null; at = at.parent) {
                path.add(at.name);
            }
            Collections.reverse(path);
            
            System.out.println("Optimized Path: " + String.join(" -> ", path));
            System.out.println("-----------------------------------");
        }
    }
}