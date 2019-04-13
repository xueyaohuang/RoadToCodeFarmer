class Solution {
    public int minFallingPathSum(int[][] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[0][i] = A[0][i];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int left = Math.max(0, j - 1);
                int right = Math.min(len - 1, j + 1);
                dp[i][j] = A[i][j] + Math.min(Math.min(dp[i - 1][left], dp[i - 1][right]), dp[i - 1][j]);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            res = Math.min(res, dp[len - 1][i]);
        }
        return res;
    }
}
