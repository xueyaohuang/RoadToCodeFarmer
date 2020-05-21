class Solution {
    public int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j]++;
                    if (i > 0 && j > 0) {
                        if (matrix[i][j - 1] == 1 && matrix[i - 1][j] == 1 && matrix[i - 1][j - 1] == 1) {
                            dp[i][j] += Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                        }
                    }
                }
                count += dp[i][j];
            }
        }
        return count;
    }
}
