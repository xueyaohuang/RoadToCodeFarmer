class Solution {
    
    // start from building -- calculate distance to all empty land
    // iterate over every cell in the corresponding matrix to find a cell with smallest value
    
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        // distances[i][j]代表empty cell到所有building的距离之和
        int[][] distances = new int[m][n];
        // canReach[i][j]代表empty cell能到达的building的个数
        int[][] canReach = new int[m][n];
        int buildingCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 如果当前building有不能达到的building，返回-1.
                    // 因为如果有一个empty cell可以到达所有building，那么所有building之间也一定是联通的（至少通过这个empty cell是联通的）
                    if (!canReach(i, j, distances, canReach, grid)) {
                        return -1;
                    }
                    buildingCount++;
                }
            }
        }
        int totDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReach[i][j] == buildingCount) {
                    totDist = Math.min(totDist, distances[i][j]);
                }
            }
        }
        return totDist == Integer.MAX_VALUE ? -1 : totDist;
    }
    
    private boolean isValid(int i, int j, boolean[][] visited, int[][] grid) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !visited[i][j] && grid[i][j] != 2;
    }
    
    private boolean canReach(int i, int j, int[][] distances, int[][] canReach, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        visited[i][j] = true;
        int dist = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] cur = queue.poll();
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (isValid(x, y, visited, grid)) {
                        if (grid[x][y] == 0) {
                            distances[x][y] += dist;
                            canReach[x][y]++;
                            queue.offer(new int[]{x, y});
                        }
                        visited[x][y] = true;
                    }
                }
            }
            dist++;
        }
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (grid[x][y] == 1 && !visited[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }
}
