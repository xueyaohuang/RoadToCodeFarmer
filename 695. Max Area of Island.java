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
