// DP + DFS
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 1;
        // 这里的dp相当于其他dfs里的visited
        int[][] dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, maxEndHere(matrix, i, j, dp, Integer.MIN_VALUE));
            }
        }
        
        return max;
    }
    
    private int maxEndHere(int[][] matrix, int i, int j, int[][] dp, int prev) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= prev) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        int max = 1;
        max = Math.max(max, maxEndHere(matrix, i + 1, j, dp, matrix[i][j]) + 1);
        max = Math.max(max, maxEndHere(matrix, i - 1, j, dp, matrix[i][j]) + 1);
        max = Math.max(max, maxEndHere(matrix, i, j + 1, dp, matrix[i][j]) + 1);
        max = Math.max(max, maxEndHere(matrix, i, j - 1, dp, matrix[i][j]) + 1);
        dp[i][j] = max;
        return max;
    }
}
