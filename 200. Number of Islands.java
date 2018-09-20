class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    removeIsland(grid, i, j);
                }
            }
        }
        return count;
    }
    private void removeIsland(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        removeIsland(grid, i + 1, j);
        removeIsland(grid, i - 1, j);
        removeIsland(grid, i, j + 1);
        removeIsland(grid, i, j - 1);
        return;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m, n, grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int x = i * n + j;
                    int y;
                    if (i < m - 1 && grid[i + 1][j] == '1') {
                        y = (i + 1) * n + j;
                        uf.union(x, y);
                    }
                    if (i > 0 && grid[i - 1][j] == '1') {
                        y = (i - 1) * n + j;
                        uf.union(x, y);
                    }
                    if (j < n - 1 && grid[i][j + 1] == '1') {
                        y = i * n + j + 1;
                        uf.union(x, y);
                    }
                    if (j > 0 && grid[i][j - 1] == '1') {
                        y = i * n + j - 1;
                        uf.union(x, y);
                    }
                }
            }
        }
        return uf.count();
    }
    
    public class UnionFind {
        
        int count;
        int[] parent;
        int[] rank;
        
        public UnionFind(int m, int n, char[][] grid) {
            count = 0;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                }
            }
            for (int i = 0; i < m * n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }
        
        public void union(int p, int q) {
            int rootp = find(p);
            int rootq = find(q);
            if (rootp == rootq) {
                return;
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
        }
        
        public int count() {
            return count;
        }
    }
}
