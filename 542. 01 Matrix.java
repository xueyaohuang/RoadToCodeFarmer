// BFS 的queue里最开始放什么有讲究，比如这个题如果放1的cell，需要对每个1的cell run BFS，如果放0的cell就不需要。
// Time: O(M * N), where M is number of rows, N is number of columns in the matrix. Space: O(M * N), space for the queue.
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // queue一开始放进0 cell的坐标，这样不用对每一个1 cell分别进行BFS
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }  else {
                    // mark non zero cell as unprocessed
                    mat[i][j] = -1;
                }
            }
        }
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            for (int[] dir : dirs) {
                int x = position[0] + dir[0];
                int y = position[1] + dir[1];
                // 对每个非零cell只需更新一次，由于是BFS，更新后一定是最短距离
                if (x >= 0 && x < m && y >= 0 && y < n && mat[x][y] == -1) {
                    mat[x][y] = mat[position[0]][position[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return mat;
    }
}

// 也可以用DP， https://leetcode.com/problems/01-matrix/discuss/1369741/C%2B%2BJavaPython-BFS-DP-solutions-with-Picture-Clean-and-Concise-O(1)-Space
class Solution { // 5 ms, faster than 99.66%
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length, INF = m + n; // The distance of cells is up to (M+N)
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (mat[r][c] == 0) continue;
                int top = INF, left = INF;
                if (r - 1 >= 0) top = mat[r - 1][c];
                if (c - 1 >= 0) left = mat[r][c - 1];
                mat[r][c] = Math.min(top, left) + 1;
            }
        }
        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                if (mat[r][c] == 0) continue;
                int bottom = INF, right = INF;
                if (r + 1 < m) bottom = mat[r + 1][c];
                if (c + 1 < n) right = mat[r][c + 1];
                mat[r][c] = Math.min(mat[r][c], Math.min(bottom, right) + 1);
            }
        }
        return mat;
    }
}
