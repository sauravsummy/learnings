package com.sunsum.dsa.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GraphProblem {

    public static boolean isGraphConnected(GraphImpl graph){
        boolean visited[] = new boolean[graph.vertices];
        int count = 0;
        for(int i=0;i<graph.vertices;i++){
            if(!visited[i]){
                drawPath(graph,i,visited);
                count++;
            }
        }
        return count == 1;
    }

    public static void drawPath(GraphImpl graph, int src, boolean[] visited){
        visited[src]= true;
        for(Edge edge: graph.adjacency[src]){
            if(!visited[edge.nbr]){
                drawPath(graph,edge.nbr,visited);
            }
        }
    }
    /*
    * M*N matrix having 0 and 1 only, represents water and iceland where all 1 represents water
    * find the number of iceland
    * example
    * 0 0 0 1 1 1 1
    * 0 0 0 1 1 1 1
    * 1 1 1 0 1 1 1
    * 1 1 1 0 1 1 1
    * 1 1 1 0 0 1 1
    * 1 1 1 1 1 1 0
    * 1 1 1 1 1 0 0
    *
    * here no of iceland is three(3)
    *
    * */

    public static int countNoOfIceland(int[][]graph){
        int count = 0;
        boolean[][] visited = new boolean[graph.length][graph.length];
        for(int i = 0; i< graph.length; i++ ){
            for(int j = 0;j < graph[i].length; j++){
                if(!visited[i][j] && graph[i][j] == 0){
                    drawPath(graph, i, j,visited);
                    count++;
                }
            }
        }

        return count;
    }

    public static void drawPath(int[][]graph, int r, int c, boolean[][]visited){
        if( r < 0 || r >= graph.length || c < 0 || c >= graph.length || visited[r][c] || graph[r][c] == 1 ){
            return;
        }

        visited[r][c] = true;
        drawPath(graph,r-1,c,visited);
        drawPath(graph,r,c-1,visited);
        drawPath(graph,r,c+1,visited);
        drawPath(graph,r+1,c,visited);

    }

    /*
    * {* 0 0 0 0 0}
    * {0 0 0 * 0 0}
    * {* s 0 0 0 0}
    * {0 0 0 * 0 0}
    * {0 0 0 0 0 d}
    * */
    public static void printAllPathFromSourceToDis(char arr[][],int r ,int c,String psf ,boolean[][] visited,List<String >list){
        if(r < 0 || r >= arr.length || c < 0 || c>=arr[0].length || visited[r][c] || arr[r][c] == '*'){
            return;
        }
        visited[r][c] = true;
        if(arr[r][c] == 'd'){
            list.add(psf);

        }

        printAllPathFromSourceToDis(arr,r-1,c,psf+"u",visited,list);
        printAllPathFromSourceToDis(arr,r,c-1,psf+"l",visited,list);
        printAllPathFromSourceToDis(arr,r,c+1,psf+"r",visited,list);
        printAllPathFromSourceToDis(arr,r+1,c,psf+"d",visited,list);
        visited[r][c] = false;

    }

    public static void main(String[] args) {
        List<List<Integer>> inputEdges = List.of(List.of(0,1,10),
                List.of(2,3,10),List.of(2,4,10),List.of(3,4,10),List.of(5,6,10));
        GraphImpl graph1 = new GraphImpl(7,inputEdges);
        System.out.println(isGraphConnected(graph1));

        List<List<Integer>> inputEdges2 = List.of(List.of(0,1,10),List.of(0,2,10),List.of(2,5,10),
                List.of(2,3,10),List.of(2,4,10),List.of(3,4,10),List.of(5,6,10));
        GraphImpl graph2 = new GraphImpl(7,inputEdges2);
        System.out.println(isGraphConnected(graph2));
        String iceLandInput = """
                0 0 0 1 1 1 1
                0 0 0 1 1 1 1
                1 1 1 0 1 1 1
                1 1 1 0 1 1 1
                1 1 1 0 0 1 1
                1 1 1 1 1 1 0
                1 1 1 1 1 0 0
                """;
        String [] rows = iceLandInput.split("\n");
        int[][] graph = new int[rows.length][rows.length];

        for (int i=0 ; i< rows.length;i++){
            String[] parts = rows[i].split(" ");
            for(int j = 0; j<parts.length;j++){
                graph[i][j] = Integer.valueOf(parts[j]);
            }
        }
        System.out.println(countNoOfIceland(graph));
        char [][] arr = new char[][]{
                {'*', '0', '0', '0', '0', '0'},
                {'0', '*', '0', '*', '0', '0'},
                {'*', 's', '*', '0', '0', '0'},
                {'*', '0', '*', '*', '0', '0'},
                {'0', '0', '0', '0', '0', 'd'}
        };
        boolean [][]visited = new boolean[arr.length][arr[0].length];
        List<String > list = new ArrayList<>();
        printAllPathFromSourceToDis(arr,2,1,"",visited,list);
        System.out.println(list);

    }


}
