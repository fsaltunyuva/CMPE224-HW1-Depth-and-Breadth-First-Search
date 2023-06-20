//-----------------------------------------------------
// Title: Main class for the second question
// Author: Furkan Safa Altunyuva
// Section: 4
// Assignment: 1
// Description: This class gets inputs, creates the graph, and computes the the intended path.
//-----------------------------------------------------

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class MainQ2 {
    public static void main(String[] args) {
        int n = 0; //Islands (Vertices)
        int m = 0; //Connections between islands (Edges)

        Scanner scanner = new Scanner(System.in);
        String first_line = scanner.nextLine(); //Getting the input of first line as a String

        String[] first_line_chars = first_line.split(" "); //Splitting the given values in a String array

        for (int i = 0; i < first_line_chars.length; i++) {//Assigning the first line values to n and m according to their positions
            switch (i) {
                case 0:
                    n = Integer.parseInt(first_line_chars[i]);
                    break;
                case 1:
                    m = Integer.parseInt(first_line_chars[i]);
                    break;
            }
        }

        Graph graph = new Graph(n); //Creating a graph with n edges

        for (int i = 0; i < m; i++) { //Reading the given edges and adding them to graph
            int e1, e2 = 0; //e1 to e2 path
            String line = scanner.nextLine();
            String[] line_chars = line.split(" ");
            e1 = Integer.parseInt(line_chars[0]);
            e2 = Integer.parseInt(line_chars[1]);
            graph.addEdge(e1, e2);
        }

        String main_path = scanner.nextLine(); //Getting the last line input as String
        String[] main_path_chars = main_path.split(" "); //Splitting the last line values in a String array

        int from = Integer.parseInt(main_path_chars[0]); //From which value
        int include = Integer.parseInt(main_path_chars[1]); //Include which value

        //PATH FINDING LOGIC

        //Find the shortest path from the "from" value to "include" value by using Breadth First Search
        //Delete the vertices from the graph which was in the between of the shortest path from the value "from" to "include" (Because we should not use the same path while returning)
        //Then, find the shortest path from the "include" value to "from" value by using Breadth First Search (Return the starting location from the shortest path)

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, from); //Using the dfs object to search the graph

        Iterable<Integer> first_path_iterable = bfs.pathTo(include); //Find the shortest path from the "from" value to "include" value
        ArrayList<Integer> first_path_list = new ArrayList<>(); //I must transfer the values from the "first_path_iterable" to ArrayList because of casting exceptions
        ArrayList<Integer> vertices_to_be_deleted = new ArrayList<>(); //An array list that holds the vertices to be deleted

        for (Integer i : first_path_iterable) { //Transferring values from the "first_path_iterable" to "first_path_list"
            first_path_list.add(i);
        }

        for (int i = 0; i < first_path_list.size(); i++) { //Iterate over the first path list
            if (i == 0 || i == first_path_list.size() - 1) { //If it is the first or the last element in the list, do nothing
                continue;
            } else { //If it is not the first or the last element in the list, add these values to the list "vertices_to_be_deleted"
                vertices_to_be_deleted.add(first_path_list.get(i));
            }
        }

        if (vertices_to_be_deleted.size() == 0) { //If the found shortest path is from->include, only delete that connection, not the whole vertex's connections. (Because we need that vertex when computing)
            graph.deleteEdge(from, include);
        }
        else { //Else, delete all the connections of that vertex because we will not use them when returning to the start location
            for (Integer i : vertices_to_be_deleted) {
                graph.deleteAllEdgesOfVertex(i);
            }
        }

        BreadthFirstSearch bfs2 = new BreadthFirstSearch(graph, include); //Do another search after deleting some connections or vertices (This time used "include" value to start from)

        Iterable<Integer> second_path = bfs2.pathTo(from); //Find the shortest path to return the starting location. (From "include" value to "from" value)

        LinkedHashSet<Integer> set = new LinkedHashSet<>(); //Create a Linked Hash Set to prevent repeated values in the collection

        for (Integer i : first_path_list) { //Add all values from the first path to the set
            set.add(i);
        }
        for (Integer i : second_path) { //Add all values from the second path to the set
            set.add(i);
        }

        ArrayList<Integer> result_path = new ArrayList<>(set); //Add all set values to a new ArrayList to sort them easily

        Collections.sort(result_path); //Sort the result path
        System.out.println(result_path); //Print the path


    }
}
