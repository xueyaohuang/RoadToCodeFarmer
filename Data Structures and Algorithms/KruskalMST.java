import java.util.*;

class WeightedEdge {
    int v1;
    int v2;
    int weight;

    public WeightedEdge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }
}

class WeightedGraph {
    int numVertices;
    ArrayList<WeightedEdge> edges;

    public WeightedGraph(int numVertices) {
        this.numVertices = numVertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int v1, int v2, int weight) {
        WeightedEdge edge = new WeightedEdge(v1, v2, weight);
        edges.add(edge);
    }
    
    public void addEdge(WeightedEdge e) {
        edges.add(e);
    }
    
    public void printGraph(){
        for (int i = 0; i < edges.size() ; i++) {
            WeightedEdge edge = edges.get(i);
            System.out.println("Edge-" + i + " v1: " + edge.v1 + " v2: " + edge.v2 + " weight: " + edge.weight);
        }
    }
}

class UnionFind {
    private int[] parent;  // parent[i] = parent of i
    private int[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)

    public UnionFind(int n) {
        this.parent = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public boolean union(int p, int q) {  // return true if not connected， o/w false
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return false;
        }
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        return true;
    }
}

public class Kruskal {
    public WeightedGraph kruskalMST(WeightedGraph graph) {
        int n = graph.numVertices;
        int m = graph.edges.size();
        WeightedGraph mst = new WeightedGraph(n);
        List<WeightedEdge> graphEdges = new ArrayList<>(graph.edges);
        Collections.sort(graphEdges, (a, b) -> a.weight - b.weight);
        UnionFind uf = new UnionFind(n);
        int count = 0;
        
        for (int i = 0; i < m; i++) {
            WeightedEdge edge = graphEdges.get(i);
            if(!uf.isConnected(edge.v1, edge.v2)){
                mst.addEdge(edge);
                count++;
                uf.union(edge.v1, edge.v2);
            }
            if (count == n - 1) {
                break;
            }
        }
        return mst;
    }
    
    public static void main(String[] args) {
        
        Kruskal k = new Kruskal();
        
        WeightedGraph graph = new WeightedGraph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        
        WeightedGraph mst = k.kruskalMST(graph);
        System.out.println("Minimum Spanning Tree: ");
        mst.printGraph();
    }
}
