class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}

// O(1) space.
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int n = cost.length;
        int twoStep = 0; // 两个台阶前的最小
        int oneStep = 0; // 一个台阶前的最小
        for (int i = 2; i <= n; i++) {
            int cur = Math.min(cost[i - 1] + oneStep, cost[i - 2] + twoStep); // 到当前台阶的最小
            twoStep = oneStep;
            oneStep = cur;
        }
        return oneStep;
    }
}
