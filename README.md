# 🇮🇳 Smart Map Navigator: National Highway Edition

A high-performance navigation engine implemented in Java that models the Indian National Highway network. This project utilizes advanced graph theory and pathfinding algorithms to determine the most efficient routes between major metropolitan hubs.

## 🚀 Key Features
* **National-Scale Graph**: Models a complex network of 15+ mega-cities (Delhi, Mumbai, Bangalore, Chennai, Kolkata, etc.) and their interconnections.
* **Interactive CLI**: Real-time user interface for querying routes between any two major hubs in the system.
* **Path Reconstruction**: Backtracks through the graph to provide the exact sequence of cities for the optimized route.
* **Error Handling**: Robust input validation to handle non-existent nodes and disconnected subgraphs.

## 🛠️ Technical DSA Architecture
This project serves as a practical showcase of core Computer Science fundamentals:

* **Graph Representation**: Implemented as an **Adjacency List** using a `HashMap<String, CityNode>`. This ensures $O(1)$ average-case lookup time for any city in the network.
* **Dijkstra’s Algorithm**: The core engine uses a "Greedy" approach to ensure mathematical optimality for the shortest path in a weighted graph.
* **PriorityQueue (Min-Heap)**: Search complexity is optimized to $O((E + V) \log V)$, where $E$ is roads and $V$ is cities.
* **Object-Oriented Design**: Clean separation between Data Models (`CityNode`, `Edge`) and the logic controller (`Graph`).



## 💻 How to Run
1. **Ensure Java is installed**: Run `java -version` in your terminal.
2. **Compile the Source**:
   ```bash
   javac MapNavigator.java
## Execute:

1.Bash
2.java MapNavigator

## Sample Output:
==============================================
      INDIA NATIONAL HIGHWAY NAVIGATOR        
==============================================
Available Hubs: [Delhi, Mumbai, Bangalore, Chennai, Kolkata, Lucknow, Jaipur, etc.]
----------------------------------------------
Enter Starting City: Lucknow
Enter Destination City: Delhi

--- ROUTE OPTIMIZATION COMPLETE ---
Total Travel Distance: 550 km
Optimized Path: Lucknow -> Delhi
-----------------------------------

## Future Scalability:
1.File I/O Integration: Transitioning from hardcoded data to dynamic loading from .csv or .json map files.

2.A Search Implementation*: Incorporating geographic coordinates (latitude/longitude) as heuristics to further optimize search time.

3.GUI Development: Plans for a visual map interface using JavaFX or Swing.
