class Solution {
    public int shortestBridge(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> boundries = new LinkedList<>();
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean found = false;
        // 1. dfs to find an island, mark it in `visited`
        for (int i = 0; i < m; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, boundries, dirs, i, j);
                    found = true;
                    break;
                }
            }
        }
        int steps = 1;
        // 2. bfs to expand this island
        while (!boundries.isEmpty()) {
            int size = boundries.size();
            for (int i = 0; i < size; i++) {
                int[] pos = boundries.poll();
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                        continue;
                    }
                    if (grid[x][y] == 1) {
                        return steps;
                    } else {
                        visited[x][y] = true;
                        boundries.offer(new int[]{x, y});
                    }
                }
            }
            steps++;
        }
        return steps;
    }
    
    private void dfs(int[][] grid, boolean[][] visited, Queue<int[]> boundries, int[][] dirs, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            // 找开始搭桥的点，因为肯定是从边界开始搭桥
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 0 && !visited[x][y]) {
                boundries.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
        for (int[] dir : dirs) {
            dfs(grid, visited, boundries, dirs, i + dir[0], j + dir[1]);
        }
    }
}
