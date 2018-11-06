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

// not modify the input
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    floodFill(grid, i, j, visited);
                }
            }
        }
        return count;
    }
    
    private void floodFill(char[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0' || visited[i][j] == true) {
            return;
        }
        visited[i][j] = true;
        floodFill(grid, i + 1, j, visited);
        floodFill(grid, i - 1, j, visited);
        floodFill(grid, i, j + 1, visited);
        floodFill(grid, i, j - 1, visited);
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

// if u can ot modify the grid, use a set to record which point is visited
// if the matrix is too large, dfs may cause stack overflow, use bfs. the size of the queue in bfs is at most as large as the diagonal
// dfs space O(n^2), bfs space O(n)
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
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void bfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        int m = grid.length;
        int n = grid[0].length;
        int position = i * n + j;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(position);
        
        while (!queue.isEmpty()) {
            int curPos = queue.poll();
            int x = curPos / n;
            int y = curPos % n;
            
            if (validPosition(grid, x + 1, y)) {
                grid[x + 1][y] = '0';
                queue.offer((x + 1) * n + y);
            }
            
            if (validPosition(grid, x - 1, y)) {
                grid[x - 1][y] = '0';
                queue.offer((x - 1) * n + y);
            }
            
            if (validPosition(grid, x, y + 1)) {
                grid[x][y + 1] = '0';
                queue.offer(x * n + y + 1);
            }
            
            if (validPosition(grid, x, y - 1)) {
                grid[x][y - 1] = '0';
                queue.offer(x * n + y - 1);
            }
        }
        
    }
    
    private boolean validPosition(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return false;
        }
        return true;
    }
}

// not modify input, bfs
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    bfsFill(grid, i, j, visited);
                }
            }
        }
        return count;
    }
    
    private void bfsFill(char[][] grid, int i, int j, boolean[][] visited) {
        // if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0' || visited[i][j] == true) {
        //     return;
        // }
        int m = grid.length;
        int n = grid[0].length;
        visited[i][j] = true;
        Queue<Integer> queue = new LinkedList<>();
        int idx = i * n + j;
        queue.offer(idx);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int x = cur / n;
            int y = cur % n;

            if (x + 1 < m && grid[x + 1][y] == '1' && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                queue.offer((x + 1) * n + y);
            }
            if (x - 1 >= 0 && grid[x - 1][y] == '1' && !visited[x - 1][y]) {
                visited[x - 1][y] = true;
                queue.offer((x - 1) * n + y);
            }
            if (y + 1 < n && grid[x][y + 1] == '1' && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                queue.offer(x * n + y + 1);
            }
            if (y - 1 >= 0 && grid[x][y - 1] == '1' && !visited[x][y - 1]) {
                visited[x][y - 1] = true;
                queue.offer(x * n + y - 1);
            }
        }
    }
}
