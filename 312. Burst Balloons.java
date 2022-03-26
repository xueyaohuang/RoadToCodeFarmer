// dp[i][j]: coins obtained from bursting all the balloons between index i and j (not including i or j)
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
        for (int j = 2; j < n; j++) { // j = 区间end - 区间start，最小单元是1个气球，所以j从2开始
            for (int i = 0; i + j < n; i++) { // i是区间开始位置
                int temp = balloons[i] * balloons[i + j];
                for (int k = i + 1; k < i + j; k++) { //最后burst的位置（注意，不是最开始戳破的位置）
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
        for (int j = 2; j < n; j++) { // j = 区间end - 区间start，最小单元是1个气球，所以j从2开始
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
