package com.sunsum.dsa.graph;

class Edge {
    public int src;
    public int nbr;
    public int weight;

    public Edge(int src, int nbr, int weight) {
        this.src = src;
        this.nbr = nbr;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "" + src + "," + nbr + "," + weight;
    }
}
