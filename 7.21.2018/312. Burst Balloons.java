class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length + 2;
        int[] balloons = new int[n];
        int[][] dp = new int[n][n];
        balloons[0] = 1;
        balloons[n - 1] = 1;
        for (int i = 1; i < n - 1; i++) {
            balloons[i] = nums[i - 1];   
        }
        for (int j = 2; j < n; j++) {
            for (int i = 0; i + j < n; i++) {
                int temp = balloons[i] * balloons[i + j];
                for (int k = i + 1; k < i + j; k++) {
                    dp[i][i + j] = Math.max(dp[i][i + j], dp[i][k] + dp[k][i + j] + temp * balloons[k]);
                    
                }
            }
        }
        return dp[0][n - 1];
    }
}

// use helper function is faster
class Solution {
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length + 2;
        int[] balloons = new int[n];
        int[][] dp = new int[n][n];
        balloons[0] = 1;
        balloons[n - 1] = 1;
        for (int i = 1; i < n - 1; i++) {
            balloons[i] = nums[i - 1];   
        }
        for (int j = 2; j < n; j++) { // 区间长度
            for (int i = 0; i + j < n; i++) { // 区间开始位置
                optimize(balloons, dp, i, i + j);
            }
        }
        return dp[0][n - 1];
    }
    private void optimize(int[] balloons, int[][] dp, int start, int end) {
        int temp = balloons[start] * balloons[end];
        for (int k = start + 1; k < end; k++) { //最后burst的位置（注意，不是最开始戳破的位置）
            dp[start][end] = Math.max(dp[start][end], dp[start][k] + dp[k][end] + temp * balloons[k]);

        }
    }
}
