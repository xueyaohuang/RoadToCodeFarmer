class Solution {
    // 如果从0开始BFS，要从所有的0都试一遍找最大
    // 换个思路，从1开始，只用BFS一次
    // We start from all the lands and start exploring the water layer by layer
    // until all the water are explored.
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        if (queue.size() == 0 || queue.size() == n * n) {
            return -1;
        }
        int distance = 0;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                for (int[] dir : directions) {
                    int x = position[0] + dir[0];
                    int y = position[1] + dir[1];
                    if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 1 || visited[x][y]) {
                        continue;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            distance++;
        }
        // 最后一轮distance不需要+1了，因为已经到了最远的
        return distance - 1;
    }
}
