class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] != 0 || grid[m - 1][n - 1] != 0) {
            return -1;
        }
        if (m == 1 && n == 1 && grid[0][0] == 0) {
            return 1;
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                for (int[] dir : dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    if (x == m - 1 && y == n - 1) {
                        return count + 1;
                    }
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 1 || visited[x][y]) {
                        continue;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            count++;
        }
        return -1;
    }
}
