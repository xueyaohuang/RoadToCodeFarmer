// dfs detect cycle in undirected graph
// We do a DFS traversal of the given graph. For every visited vertex ‘v’, if there is an
// adjacent ‘u’ such that u is already visited and u is not parent of v, then there is a
// cycle in graph. If we don’t find such an adjacent for any vertex, we say that there is
// no cycle. The assumption of this approach is that there are no parallel edges between any two vertices.
class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n <= 0) {
            return false;
        }
        if (edges == null || edges.length != n - 1) {
            return false;
        }
        
        int[] visited = new int[n];
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adjList[edge[0]].add(edge[1]);
            adjList[edge[1]].add(edge[0]);
        } 
        
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                if (hasCycle(i, adjList, visited, -1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean hasCycle(int i, List<Integer>[] adjList, int[] visited, int parent) {
        if (visited[i] == 2) {
            return false;
        }
        visited[i] = 1;
        for (int j : adjList[i]) {
            if (visited[j] != 1) {
                if (hasCycle(j, adjList, visited, i)) {
                    return true;
                }
            } else if (j != parent) { // visited[j] == 1时还必须满足j != parent
                return true;
            }
        }
        visited[i] = 2;
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
