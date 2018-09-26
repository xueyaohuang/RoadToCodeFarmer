class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return edges[0];
    }
    
    class UnionFind {
        int[] parent;
        int[] rank;
        
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
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
}
