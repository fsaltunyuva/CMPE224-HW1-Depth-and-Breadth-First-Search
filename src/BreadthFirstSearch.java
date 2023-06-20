//-----------------------------------------------------
// Title: Breadth First Search
// Author: Furkan Safa Altunyuva
// Section: 4
// Assignment: 1
// Description: This class uses the modified breadth-first logic from the Sedgewick's Algorithms book.
//-----------------------------------------------------

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public BreadthFirstSearch(Graph G, int s) {
        marked = new boolean[G.getV() + 1]; //I am adding 1 to the count because outputs start counting from 1, not 0.
        edgeTo = new int[G.getV() + 1]; //I am adding 1 to the count because outputs start counting from 1, not 0.
        this.s = s;
        bfs(G, s);
    }

    private void bfs(Graph G, int s) { //The same algorithm in the Sedgewick's Algorithms book.
        Queue<Integer> queue = new LinkedList<>(); //offer --> enqueue poll -->dequeue
        marked[s] = true; // Mark the source
        queue.offer(s); // Put it on the queue.

        while (!queue.isEmpty()) {
            int v = queue.poll(); // Remove next vertex from the queue.
            for (int w : G.adj(v))
                if (!marked[w]) // For every unmarked adjacent vertex,
                {
                    edgeTo[w] = v; // save last edge on shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.offer(w); // and add it to the queue.
                }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }


}
