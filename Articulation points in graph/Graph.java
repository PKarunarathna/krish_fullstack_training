import java.io.*;
import java.util.*;
import java.util.LinkedList;
public class Graph 
{ 
    private int vertex;   // No. of vertices 
  
    // Array  of lists for Adjacency List Representation 
    private LinkedList<Integer> adj_list[]; 
    int time = 0; 
    static final int NO_PARENT = -1; 
  
    // Constructor 
    Graph(int v) 
    { 
        vertex = v; 
        adj_list = new LinkedList[v]; 
        for (int i=0; i<v; ++i) 
            adj_list[i] = new LinkedList(); 
    } 
  
    //Function to add an edge into the graph 
    void add_an_edge(int v, int w) 
    { 
        adj_list[v].add(w);  // Add w to v's list. 
        adj_list[w].add(v);    //Add v to w's list 
    } 
  
    // A recursive function that find articulation points using DFS 
    // current_vertex --> The vertex to be visited next 
    // visited[] --> keeps tract of visited vertices 
    // discovery_time[] --> Stores discovery times of visited vertices 
    // parent_node[] --> Stores parent vertices in DFS tree 
    // articulation_points[] --> Store articulation points 
    void helper_function(int current_vertex, boolean isvisited[], int discovery_time[], int minimum_time[], int parent_node[], boolean articulation_points[]) 
    { 
  
        // Count of children in DFS Tree 
        int children = 0; 
  
        // Mark the current node as visited 
        isvisited[current_vertex] = true; 
  
        // Initialize discovery time and minimum time.
        discovery_time[current_vertex] = minimum_time[current_vertex] = ++time; 
  
        // Go through all vertices aadjacent to this 
        Iterator<Integer> i = adj_list[current_vertex].iterator(); 
        while (i.hasNext()) 
        { 
            int adj_vertex = i.next();  // v is adjacent vertex of current_vertex
  
            // If adj_vertex is not visited yet, then make it a child of current_vertex in graph and call recursion for it.
            if (!isvisited[adj_vertex]) 
            { 
                children++; 
                parent_node[adj_vertex] = current_vertex; 
                helper_function(adj_vertex, isvisited, discovery_time, minimum_time, parent_node, articulation_points); 
  
                // Check if the subtree rooted with adj_vertex has a connection to one of the ancestors of current_vertex. 
                minimum_time[current_vertex]  = Math.min(minimum_time[current_vertex], minimum_time[adj_vertex]); 
  
                // current_vertex is an articulation point if any of following is satisfied. 
  
                // if current_vertex is root of DFS tree and has two or more chilren. 
                if (parent_node[current_vertex] == NO_PARENT && children > 1) 
                    articulation_points[current_vertex] = true; 
  
                // if current_vertex is not root and minimum_time value of one of its adj_vertex is more than discovery value of current_vertex. 
                if (parent_node[current_vertex] != NO_PARENT && minimum_time[adj_vertex] >= discovery_time[current_vertex]) 
                    articulation_points[current_vertex] = true; 
            } 
  
            // Update minimum_time value of current_vertex for parent function calls. 
            else if (adj_vertex != parent_node[current_vertex]) 
                minimum_time[current_vertex]  = Math.min(minimum_time[current_vertex], discovery_time[adj_vertex]); 
        } 
    } 
  
    // The function to find articulation points by depth-first search.
    void findArticulationPoints() 
    { 
        // Marking vertices as unmarked and initialising required arrays.
        boolean isvisited[] = new boolean[vertex]; 
        int discovery_time[] = new int[vertex]; // array for discovery time of each vertex. 
        int minimum_time[] = new int[vertex]; // array for minimum time of each node. 
        int parent_node[] = new int[vertex]; // array for storing parent of each vertex.
        boolean articulation_points[] = new boolean[vertex]; // To store articulation points.
  
        // Initialize parent_node array, isvisited and articulation_points arrays. 
        for (int i = 0; i < vertex; i++) 
        { 
            parent_node[i] = NO_PARENT; 
            isvisited[i] = false; 
            articulation_points[i] = false; 
        } 
  
        // Call the recursive helper function to find articulation points in graph for every vertex iteratively. 
        for (int i = 0; i < vertex; i++){
            if (isvisited[i] == false)
            { // call helper function.
             helper_function(i, isvisited, discovery_time, minimum_time, parent_node, articulation_points);
            }}
  
        // after recursive calls, print articulation points, if any.
        for (int i = 0; i < vertex; i++){
            if (articulation_points[i] == true) 
                System.out.print(i+" ");
        }
        
        for(int i = 0; i < vertex; i++){
            if (articulation_points[i] == true) return;
            
        }
        System.out.println("Graph has no articulation point");
    }
  
    //main method
    public static void main(String args[]) 
    { 
        //creating graphs and calling function to find articulation points.
        Graph graph_1 = new Graph(6); 
        graph_1.add_an_edge(1,0); 
        graph_1.add_an_edge(0,5); 
        graph_1.add_an_edge(1,3); 
        graph_1.add_an_edge(1,2); 
        graph_1.add_an_edge(2,3);
        graph_1.add_an_edge(2,4);
        graph_1.add_an_edge(3,4);
        System.out.println("Articulation points in Graph 1 are ");
        graph_1.findArticulationPoints();
        System.out.println();
        
        Graph graph_2 = new Graph(5); 
        graph_2.add_an_edge(0,1); 
        graph_2.add_an_edge(0,3); 
        graph_2.add_an_edge(0,4); 
        graph_2.add_an_edge(1,3); 
        graph_2.add_an_edge(3,4); 
        graph_2.add_an_edge(1,2); 
        graph_2.add_an_edge(3,2);
        System.out.println("Articulation points in Graph 2 are ");
        graph_2.findArticulationPoints(); 
        System.out.println();
      
    } 
}