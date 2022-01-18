class Solution {
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        // 只记录停下来的地方有没有visit过，路过但没停的地方不记录，因为可以从不同的方向再来
        boolean[][] visited = new boolean[m][n];
        return canReach(maze, start, destination, visited, m, n);
    }
    
    private boolean canReach(int[][] maze, int[] start, int[] destination, boolean[][] visited, int m, int n) {
        if (visited[start[0]][start[1]]) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        visited[start[0]][start[1]] = true;
        for (int[] dir : directions) {
            int x = start[0], y = start[1];
            // 一直走直到能停为止
            while (x + dir[0] >= 0 && x + dir[0] < m && y + dir[1] >= 0 && y + dir[1] < n && maze[x + dir[0]][y + dir[1]]  == 0) {
                x += dir[0];
                y += dir[1];
            }
            if (canReach(maze, new int[]{x, y}, destination, visited, m, n)) {
                return true;
            }
        }
        return false;
    }
}
