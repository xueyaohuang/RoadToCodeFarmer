// time O(n^3), space O(n^2)
// dp[i][j]: 在i到j之间猜对需要最少的钱。
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i >= 1; i--) {
                if (i == j - 1) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        // 不知道target是在k的左边还是右边，所以为了保证能猜出来，要取较大的那个
                        int min = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                        dp[i][j] = Math.min(dp[i][j], min);
                    }
                }
            }
        }
        return dp[1][n];
    }
}
