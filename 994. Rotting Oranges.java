class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int freshOrange = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshOrange++;
                } else if (grid[i][j] == 2) {
                    queue.offer(i * n + j);
                }
            }
        }
        int rottenOrange = 0;
        int minute = 0;
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            // System.out.println(size);
            for (int k = 0; k < size; k++) {
                int position = queue.poll();
                int i = position / n;
                int j = position % n;
                for (int[] dir : directions) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
                        continue;
                    }
                    grid[x][y] = 2;
                    rottenOrange++;
                    queue.offer(x * n + y);
                }
            }
            // 注意增加minute的条件，最后一轮处理完后没有新的坏橘子了（queue是empty的），就不能增加minute
            if (!queue.isEmpty()) {
                minute++;
            }
        }
        if (rottenOrange == freshOrange) {
            return minute;
        }
        return -1;
    }
}

class Solution {
    int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }
        int m = grid.length;
        int n = grid[0].length;
        int fresh = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(i * n + j);
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            if (fresh > 0) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    int x = cur / n;
                    int y = cur % n;
                    for (int[] dir : dirs) {
                        int nextX = x + dir[0];
                        int nextY = y + dir[1];
                        if (hasFreshOrange(grid, nextX, nextY)) {
                            queue.offer(nextX * n + nextY);
                            fresh--;
                        }
                    }
                }
                time++;
            }
            if (fresh == 0) {
                break;
            }
        }
        return fresh == 0 ? time : -1;
    }
    
    private boolean hasFreshOrange(int[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
            grid[i][j] = 2;
            return true;
        }
        return false;
    }
}
