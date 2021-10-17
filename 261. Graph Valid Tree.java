// dfs detect cycle in undirected graph
// We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an
// adjacent ‘u’ such that u is already visited and u is not parent of v, then there is a
// cycle in graph. If we don’t find such an adjacent for any vertex, we say that there is
// no cycle. The assumption of this approach is that there are no parallel edges between any two vertices.
class Solution {
    // to be a valid tree:
    // 1. number of edges should be n - 1. 
    // 2. no cycle
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        // 0: not started
        // 1: visiting
        // 2: finished
        int[] visited = new int[n];
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (hasCycle(adj, visited, i, -1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean hasCycle(List<Integer>[] adj, int[] visited, int i, int parent) {
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        for (int j : adj[i]) {
            if (visited[j] == 0) {
                if (hasCycle(adj, visited, j, i)) {
                    return true;
                }
            } else if (j != parent) { // visited[j] == 1, 如果j没有finish并且不是i的parent，说明有cycle
                return true;
            }
        }
        return false;
    }
}

class Solution {
    
    class UnionFind {
        int[] parent;
        int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
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
        
        public boolean union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp == rootq) {
                return false;
            }
            
            if (rank[rootp] > rank[rootq]) {
                parent[rootq] = rootp;
            } else if (rank[rootp] < rank[rootq]) {
                parent[rootp] = rootq;
            } else {
                parent[rootq] = rootp;
                rank[rootp]++;
            }
            return true;
        }
    }
    
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0) {
            return false;
        }
        if (edges == null || edges.length != n - 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return false;
            }
        }
        return true;
    }
}
