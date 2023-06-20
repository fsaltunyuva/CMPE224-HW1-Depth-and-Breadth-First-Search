//-----------------------------------------------------
// Title: Main class for the first question
// Author: Furkan Safa Altunyuva
// Section: 4
// Assignment: 1
// Description: This class gets inputs, creates the graph, and computes the time result.
//-----------------------------------------------------

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class MainQ1 {
    public static void main(String[] args) {
        int n = 0; //Cities (Vertices)
        int m = 0; //Connections between cities (Edges)
        int t = 0; //Time to change states
        int c = 0; //Travel time

        Scanner scanner = new Scanner(System.in);
        String first_line = scanner.nextLine(); //Getting the input of first line as a String

        String[] first_line_chars = first_line.split(" "); //Splitting the given values in a String array

        for (int i = 0; i < first_line_chars.length; i++) { //Assigning the first line values to n, m, t, and c according to their positions
            switch (i) {
                case 0:
                    n = Integer.parseInt(first_line_chars[i]);
                    break;
                case 1:
                    m = Integer.parseInt(first_line_chars[i]);
                    break;
                case 2:
                    t = Integer.parseInt(first_line_chars[i]);
                    break;
                case 3:
                    c = Integer.parseInt(first_line_chars[i]);
                    break;
            }
        }

        Graph graph = new Graph(n); //Creating a graph with n edges

        for (int i = 0; i < m; i++) { //Reading the given edges from input and adding them to graph
            int e1, e2 = 0; //e1 to e2 path
            String line = scanner.nextLine();
            String[] line_chars = line.split(" ");
            e1 = Integer.parseInt(line_chars[0]);
            e2 = Integer.parseInt(line_chars[1]);
            graph.addEdge(e1, e2);
        }

        String main_path = scanner.nextLine(); //Getting the last line input as String
        String[] main_path_chars = main_path.split(" "); //Splitting the last line values in a String array

        int from = Integer.parseInt(main_path_chars[0]); //From which value (Starting city vertex)
        int to = Integer.parseInt(main_path_chars[1]); //To which value (Ending city vertex)

        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, from); //Using breadth first search, using "from" value as source

        Stack<Integer> result_path = (Stack<Integer>) bfs.pathTo(to); //Using the pathTo method in BreadthFirstSearch class to get the shortest path
        System.out.println(result_path.size()); //Printing how many edges in the path (How many cities to land on)

        Collections.reverse(result_path); //Reversing the stack to get the correct order
        Collections.sort(result_path); //Sorting them to guarantee the output (In some cases, the reverse stack may not be sorted)

        System.out.println(result_path); //Printing the path

        //Computing the Time Logic

        int plane_landing_count = result_path.size() - 1; //A plane will land x-1 times if it goes between x cities
        //The time logic will stop according to plane_landing_count (If plane landed y-1 times in a path that includes y cities, flight ends)

        boolean airport_status = true; //true -> running || false -> loading
        boolean plane_status = true; //true -> flying || false -> landed

        int real_time = 0; //Simulation of time in minute
        int instantaneous_landing_count = 0; //Instant landing count of the plane in the while loop
        int take_off_time = 0; //The take of minute of the plane in the while loop

        while (true) {
            if (instantaneous_landing_count == plane_landing_count)
                break; //If plane landed (city count - 1) times, the flight is ended.

            real_time++; //Increase the time in every run of the while-loop

            if (real_time % t == 0) { //If the real time is multiple of change time, change the status of airports
                airport_status = !airport_status;
            }
            if (real_time == take_off_time + c) { //Land the plane if the time is, take off time + travel time
                plane_status = false;
                instantaneous_landing_count++;
            }
            if (!plane_status && airport_status) { //If plane landed and airport is running, take off again
                plane_status = true;
                take_off_time = real_time;
            }

        }

        System.out.println(real_time); //Print the time taken in the flight

    }

}