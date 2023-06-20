//-----------------------------------------------------
// Title: Main class for the first question
// Author: Furkan Safa Altunyuva
// Section: 4
// Assignment: 1
// Description: This class uses the modifed graph structure from the Sedgewick's Algorithms book.
//-----------------------------------------------------

import java.util.ArrayList;

public class Graph {
    private final int V;
    private ArrayList<Integer>[] adj; //An array that stores ArrayLists in it

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList[V + 1]; // (V+1) To prevent IndexOutOfBoundsException because inputs starts counting from 1 not 0.
        for (int v = 0; v < V + 1; v++) //(V+1) To prevent IndexOutOfBoundsException because inputs starts counting from 1 not 0.
            adj[v] = new ArrayList<Integer>();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int getV() {
        return V;
    }

    public ArrayList<Integer>[] getAdj() {
        return adj;
    }

    public void deleteAllEdgesOfVertex(int v) { //Clears all the elements in the adjacency list of v and removes all instances of v in other lists.
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].contains(v)) {
                adj[i].remove(Integer.valueOf(v));
            }
        }

        adj[v].clear();
    }

    public void deleteEdge(int v, int w) { //Deletes the edge between two given vertices
        adj[v].remove(Integer.valueOf(w));
        adj[w].remove(Integer.valueOf(v));
    }
}