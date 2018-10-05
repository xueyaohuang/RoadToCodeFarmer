class Solution {
    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        int res = 0;
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
                res++;
                dfs(i, adjList, visited);
            }
        }
        return res;
    }
    
    private void dfs(int i, List<Integer>[] adjList, int[] visited) {
        if (visited[i] != 0) {
            return;
        }
        visited[i] = 1;
        for (int j : adjList[i]) {
            dfs(j, adjList, visited);
        }
        visited[i] = 2;
    }
}

class Solution {
    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        
        public UnionFind(int n) {
            count = n;
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
            count--;
            return true;
        }
        
        public int count() {
            return count;
        }
    }
    
    public int countComponents(int n, int[][] edges) {
        if (n <= 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.count();
    }
}
