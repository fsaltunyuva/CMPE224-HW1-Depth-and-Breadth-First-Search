//-----------------------------------------------------
// Title: Depth First Search
// Author: Furkan Safa Altunyuva
// Section: 4
// Assignment: 1
// Description: This class uses the modified depth-first logic from the Sedgewick's Algorithms book and 2 methods to compute paths for the second question.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;

public class DepthFirstSearch {

    private static boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.getV() + 1]; //I am adding 1 to the count because outputs start counting from 1, not 0.
        edgeTo = new int[G.getV() + 1]; //I am adding 1 to the count because outputs start counting from 1, not 0.
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) { //The same algorithm in the Sedgewick's Algorithms book.
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
    }

}
