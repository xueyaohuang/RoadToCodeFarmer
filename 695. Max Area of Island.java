class Solution {
    int curArea = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    curArea = 0;
                    dfs(grid, i, j, visited);
                    res = Math.max(res, curArea);
                }
            }
        }
        return res;
    }
    
    private void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        curArea++;
        dfs(grid, i + 1, j, visited);
        dfs(grid, i - 1, j, visited);
        dfs(grid, i, j + 1, visited);
        dfs(grid, i, j - 1, visited);
    }
}

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, dfs(grid, i, j, visited, 0));
                }
            }
        }
        return res;
    }
    
    private int dfs(int[][] grid, int i, int j, boolean[][] visited, int area) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return area;
        }
        area++;
        visited[i][j] = true;
        area = dfs(grid, i - 1, j, visited, area);
        area = dfs(grid, i + 1, j, visited, area);
        area = dfs(grid, i, j - 1, visited, area);
        area = dfs(grid, i, j + 1, visited, area);
        return area;
    }
}

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    res = Math.max(res, bfs(grid, i, j, visited));
                }
            }
        }
        return res;
    }
    
    private int bfs(int[][] grid, int i, int j, boolean[][] visited) {
        int area = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i * n + j);
        visited[i][j] = true;
        
        while (!queue.isEmpty()) {
            int idx = queue.poll();
            area++;
            int x = idx / n;
            int y = idx % n;            
            if (isValid(grid, x - 1, y, visited)) {
                queue.offer((x - 1) * n + y);
                visited[x - 1][y] = true;
            }
            if (isValid(grid, x + 1, y, visited)) {
                queue.offer((x + 1) * n + y);
                visited[x + 1][y] = true;
            }
            if (isValid(grid, x, y - 1, visited)) {
                queue.offer(x * n + y - 1);
                visited[x][y - 1] = true;
            }
            if (isValid(grid, x, y + 1, visited)) {
                queue.offer(x * n + y + 1);
                visited[x][y + 1] = true;
            }
        }
        return area;
    }
    
    private boolean isValid(int[][] grid, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return false;
        }
        return true;
    }
}
