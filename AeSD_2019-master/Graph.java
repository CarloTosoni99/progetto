

import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
class Graph 
{ 

    // A class to represent a subset for union-find 
    class subset 
    { 
        int parent, rank; 
    }; 
  
    int V, E;   
    Edge edge[]; 
  
    // Creates a graph with V vertices and E edges 
    Graph(int v, int e) 
    { 
        V = v; 
        E = e; 
        edge = new Edge[E]; 
        for (int i=0; i<e; ++i) 
            edge[i] = new Edge(); 
    } 
  
    // function find and Union, for Union-find structure
    int find(subset subsets[], int i) 
    { 
        // find root and make root as parent of i (path compression) 
        if (subsets[i].parent != i) 
            subsets[i].parent = find(subsets, subsets[i].parent); 
  
        return subsets[i].parent; 
    } 
  
    void Union(subset subsets[], int x, int y) 
    { 
        int xroot = find(subsets, x); 
        int yroot = find(subsets, y); 

        if (subsets[xroot].rank < subsets[yroot].rank) 
            subsets[xroot].parent = yroot; 
        else if (subsets[xroot].rank > subsets[yroot].rank) 
            subsets[yroot].parent = xroot; 
  
        else
        { 
            subsets[yroot].parent = xroot; 
            subsets[xroot].rank++; 
        } 
    } 
  
    // The main function to create MST
    Edge[] KruskalMST() 
    { 
        Edge result[] = new Edge[V];
        int e = 0;  
        int i = 0;  
        for (i=0; i<V; ++i) 
            result[i] = new Edge(); 
  
    
        Arrays.sort(edge); 
  
        subset subsets[] = new subset[V]; 
        for(i=0; i<V; ++i) 
            subsets[i]=new subset(); 
  
        for (int v = 0; v < V; ++v) 
        { 
            subsets[v].parent = v; 
            subsets[v].rank = 0; 
        } 
  
        i = 0; 

        while (e < V - 1) 
        { 

            Edge next_edge = new Edge(); 
            next_edge = edge[i++]; 
  
            int x = find(subsets, next_edge.src); 
            int y = find(subsets, next_edge.dest); 
  

            if (x != y) 
            { 
                result[e++] = next_edge; 
                Union(subsets, x, y); 
            } 

        } 
  
    
        /* if someone need it, there is also the possibility to print the array of edges, we used it to check the correct working of the method
        System.out.println("Stampo gli archi "); 
        for (i = 0; i < e; ++i) 
            System.out.println(result[i].src+" -- " +  
                   result[i].dest+" == " + result[i].weight); */
        return result;
        
    }
}