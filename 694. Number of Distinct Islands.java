class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    StringBuilder sb = new StringBuilder();
                    encodeIsland(grid, i, j, sb, visited, 's');
                    set.add(sb.toString());
                }
            }
        }
        
        return set.size();
    }
    
    private void encodeIsland(int[][] grid, int i, int j, StringBuilder sb, boolean[][] visited, char direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        sb.append(direction);
        encodeIsland(grid, i - 1, j, sb, visited, 'u');
        encodeIsland(grid, i + 1, j, sb, visited, 'd');
        encodeIsland(grid, i, j - 1, sb, visited, 'l');
        encodeIsland(grid, i, j + 1, sb, visited, 'r');
        sb.append('e');
    }
}
