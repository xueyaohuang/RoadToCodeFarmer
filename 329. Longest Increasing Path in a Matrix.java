// DP + DFS
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE, dp));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int prev, int[][] dp) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int res = 1;
        res += maxOfFour(dfs(matrix, i + 1, j, matrix[i][j], dp),
                        dfs(matrix, i - 1, j, matrix[i][j], dp),
                        dfs(matrix, i, j + 1, matrix[i][j], dp),
                        dfs(matrix, i, j - 1, matrix[i][j], dp));
        dp[i][j] = res;
        return res;
    }
    
    private int maxOfFour(int a, int b, int c, int d) {
        return Math.max(Math.max(a, b), Math.max(c, d));
    }
}

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        // 这里的dp相当于其他dfs里的visited
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, i, j, dp, Integer.MIN_VALUE));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] dp, int prev) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int max = 1;
        max = Math.max(max, dfs(matrix, i + 1, j, dp, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, i - 1, j, dp, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, i, j + 1, dp, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, i, j - 1, dp, matrix[i][j]) + 1);
        dp[i][j] = max;
        return max;
    }
}

// DFS no memory, could be brute force
class Solution {
    
    private int cur;
    private int curMax;
    
    public int longestIncreasingPath(int[][] matrix) {
        int max = 0;
        boolean[][] visited;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                cur = 0;
                curMax = 0;
                visited = new boolean[matrix.length][matrix[0].length];
                dfs(matrix, i, j, visited, Integer.MIN_VALUE);
                max = Math.max(max, curMax);
            }
        }
        return max;
    }
    
    private void dfs(int[][] matrix, int i, int j, boolean[][] visited, int prev) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || visited[i][j] || matrix[i][j] <= prev) {
            return;
        }
        visited[i][j] = true;
        cur++;
        curMax = Math.max(curMax, cur);
        dfs(matrix, i + 1, j, visited, matrix[i][j]);
        dfs(matrix, i - 1, j, visited, matrix[i][j]);
        dfs(matrix, i, j + 1, visited, matrix[i][j]);
        dfs(matrix, i, j - 1, visited, matrix[i][j]);
        visited[i][j] = false;
        cur--;
        return;
    }
}
