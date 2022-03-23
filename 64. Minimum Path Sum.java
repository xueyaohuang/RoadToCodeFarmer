class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}

// no extra space. Modify grid.
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 1; i < col; i++) {
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
    }
}

// O(n) extra space
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[n];
        res[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            res[j] = grid[0][j] + res[j - 1];
        }
        for (int i = 1; i < m; i++) {
            res[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                res[j] = grid[i][j] + Math.min(res[j], res[j - 1]);
            }
        }
        return res[n - 1];
    }
}
