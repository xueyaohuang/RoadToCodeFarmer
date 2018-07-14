class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i >= 1; i--) {
                if (i == j - 1) {
                    dp[i][j] = i;
                }
                else {
                    int max = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        int min = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                        max = Math.min(max, min);
                    }
                    dp[i][j] = max;
                }              
            }     
        }
        return dp[1][n];
    }
}
