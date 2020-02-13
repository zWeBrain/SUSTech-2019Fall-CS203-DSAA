package lab9;

import java.util.List;
import java.util.Map;

public class Graph {
    private List<Vertex> vertexList;
    private Map<Vertex, List<Edge>> edgeListMap;

    static class Edge {
        private Vertex u;
        private Vertex v;
        private int w;

        public Edge(Vertex u, Vertex v, int w) {
            super();
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class Vertex {
        private final static int infinite_dis = Integer.MAX_VALUE;

        private int index;
        private int dyeingState;
        private int disFrom1st; 
        private Vertex nearestFather;

        Vertex(int i) {
            this.dyeingState = 0;
            this.disFrom1st = infinite_dis;
            this.nearestFather = null;
            this.index = i;
        }
        
    }


}
