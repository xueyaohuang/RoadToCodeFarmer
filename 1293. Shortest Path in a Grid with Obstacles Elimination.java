/*
Time: O(M * N * K), where M is number of rows, N is number of columns in the grid, K is number of obstacles we can eliminate.
We conduct a BFS traversal in the grid. In the worst case, we will visit each cell in the grid. And for each cell, at most,
it will be visited KK times, with different quotas of obstacle elimination.
Space: O(M * N * K)
*/
class Solution {
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        if (k >= m + n - 2) {
            return m + n - 2;
        }
        boolean[][][] visited = new boolean[m][n][k + 1];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0][k] = true;
        queue.offer(new int[]{0, 0, k});
        int steps = 0;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int row = cur[0], col = cur[1], curK = cur[2];
                if (row ==  m - 1 && col == n - 1) {
                    return steps;
                }
                for (int[] dir : dirs) {
                    int x = row + dir[0];
                    int y = col + dir[1];
                    if (x < 0 || x >= m ||  y < 0 || y >= n) {
                        continue;
                    }
                    int newK = curK - grid[x][y];
                    if (newK >= 0 && !visited[x][y][newK]) {
                        queue.offer(new int[]{x, y, newK});
                        visited[x][y][newK] = true;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
