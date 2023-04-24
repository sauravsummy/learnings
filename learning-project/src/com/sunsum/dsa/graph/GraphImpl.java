package com.sunsum.dsa.graph;

import java.util.*;

public class GraphImpl {

    public LinkedList<Edge>[] adjacency;
    public int vertices;

    public GraphImpl(int vertices, List<List<Integer>> edges){
        this.vertices = vertices;
        adjacency = new LinkedList[vertices];
        for(int i=0; i<vertices;i++){
            adjacency[i] = new LinkedList<Edge>();
        }
        for(int i=0; i< edges.size();i++){
            System.out.println(edges.get(i));
            int v1 = edges.get(i).get(0);
            int v2 = edges.get(i).get(1);
            int wt = edges.get(i).get(2);
            adjacency[v1].add(new Edge(v1,v2,wt));
            adjacency[v2].add(new Edge(v2,v1,wt));
        }
        //[[1,2,10],[1,3,10],[1,4,10],[1,5,10],[2,5,10],[3,6,10],[4,7,10]]
    }

    public boolean hasPath(int src, int dest, boolean[] visited){
        if(src == dest){
            return true;
        }
        visited[src] = true;
        List<Edge> nbrOfsrc = adjacency[src];
        for(int i =0 ; i< nbrOfsrc.size();i++){
            Edge nbr = nbrOfsrc.get(i);
            if(!visited[nbr.nbr]){
                boolean hasPath = hasPath(nbr.nbr,dest,visited);
                if(hasPath){
                    return true;
                }
            }
        }
        return false;
    }
    int shortestWeight = Integer.MAX_VALUE;
    int greatestWeight = Integer.MIN_VALUE;
    int floorValue = Integer.MIN_VALUE;
    int ceiling = Integer.MAX_VALUE;
    String shortestPath = "";
    String greatestPath = "";

    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    public void multiSolver(int src ,int dest,String pathSoFar, int weightSoFar,boolean[]visited, int criteria,int nthLargest){


        if(src == dest){
            if(weightSoFar < shortestWeight){
                shortestWeight = weightSoFar;
                shortestPath = pathSoFar+src;
            }

            if(weightSoFar > greatestWeight){
                greatestWeight = weightSoFar;
                greatestPath = pathSoFar+src;
            }

            if(criteria < weightSoFar && weightSoFar < ceiling ){
                ceiling = weightSoFar;
            }

            if(criteria > weightSoFar && weightSoFar > floorValue){
                floorValue = weightSoFar;
            }

            if(priorityQueue.size()!=nthLargest){
                priorityQueue.add(weightSoFar);
            }else if(priorityQueue.peek() < weightSoFar){
                priorityQueue.remove();
                priorityQueue.add(weightSoFar);
            }
            return;
        }

        visited[src] = true;
        List<Edge> srcNbr = adjacency[src];
        for(Edge edge: srcNbr){
            if(!visited[edge.nbr]){
                multiSolver(edge.nbr,dest,pathSoFar+edge.src,weightSoFar+edge.weight,visited,criteria,nthLargest);
            }
        }
        visited[src] = false;

    }

    public List<List<Integer>> getConnectedComponent(int vertices){
        boolean [] visited = new boolean[vertices];
        List<List<Integer>> connectedComps = new ArrayList<>();
        for(int i = 0; i < vertices; i++){
            if(!visited[i]){
                List<Integer> comp = new ArrayList<>();
                drawGraph(comp,visited,i);
                connectedComps.add(comp);
            }

        }
        return connectedComps;
    }

    public void drawGraph(List<Integer>component, boolean[]visited, int src){
        visited[src] = true;
        component.add(src);
        List<Edge> srcNbr = adjacency[src];
        for(Edge edge: srcNbr){
            if(!visited[edge.nbr]){
                drawGraph(component,visited,edge.nbr);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>>inputEdge = List.of(List.of(1,2,10),
                List.of(1,3,10),List.of(1,4,10),
                List.of(1,5,10),List.of(2,5,10),
                List.of(3,6,10),List.of(4,7,10));
        GraphImpl graph = new GraphImpl(8,inputEdge);
        boolean[] visited = new boolean[8];
        //System.out.println("Has path: "+ graph.hasPath(1,6,visited));

        List<List<Integer>>inputEdge2 = List.of(List.of(1,2,2),
                List.of(1,3,8),List.of(1,4,9),
                List.of(1,5,5),List.of(2,5,7),
                List.of(3,6,3),List.of(4,7,6),
                List.of(6,7,1) ,List.of(5,7,2)
        );
        //11, 16,8,12
        GraphImpl graph1 = new GraphImpl(8,inputEdge2);
        graph1.multiSolver(1,6,"",0,visited,10,3);
        System.out.println(graph1.shortestPath+"@"+graph1.shortestWeight);
        System.out.println(graph1.greatestPath+"@"+graph1.greatestWeight);
        System.out.println("floor value "+ graph1.floorValue);
        System.out.println("ceiling value "+graph1.ceiling);
        System.out.println("third largest weight is "+graph1.priorityQueue.peek());

        List<List<Integer>> inputEdge3 = List.of(
                List.of(0,1,10),List.of(2,3,10),List.of(4,5,10),
                List.of(4,6,10),List.of(5,6,10)
        );

        GraphImpl graph2 = new GraphImpl(7,inputEdge3);
        System.out.println(graph2.getConnectedComponent(7));

    }


}

