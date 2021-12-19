class Solution {
    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<Integer> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[] star = findStart(grid, m, n);
        queue.offer(star[0] * n + star[1]);
        visited[star[0]][star[1]] = true;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int path = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            path++;
            for (int k = 0; k < size; k++) {
                int position = queue.poll();
                int i = position / n;
                int j = position % n;
                for (int[] dir : dirs) {
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 'X' || visited[x][y]) {
                        continue;
                    }
                    if (grid[x][y] == '#') {
                        return path;
                    }
                    if (grid[x][y] == 'O') {
                        queue.offer(x * n + y);
                        visited[x][y] = true;
                    }
                }
            }
        }
        return -1;
    }
    
    private int[] findStart(char[][] grid, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
