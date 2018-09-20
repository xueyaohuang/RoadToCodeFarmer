class Solution {
    public int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    encodeIsland(grid, i, j, sb, 's'); // s代表start
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    
    private void encodeIsland(int[][] grid, int i, int j, StringBuilder sb, char direction) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        sb.append(direction);
        encodeIsland(grid, i - 1, j, sb, 'u');
        encodeIsland(grid, i + 1, j, sb, 'd');
        encodeIsland(grid, i, j - 1, sb, 'l');
        encodeIsland(grid, i, j + 1, sb, 'r');
        sb.append('e');
    }
}
