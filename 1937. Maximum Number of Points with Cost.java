/*
暴力dp是O(MN^2)，想办法优化到O(MN)
优化方法和 LC 1014 Best Sightseeing Pair思想类似
https://leetcode.com/problems/maximum-number-of-points-with-cost/discuss/1344893/Similar-to-931.-Minimum-Falling-Path-Sum
*/
class Solution {
    public long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long res = 0;
        long[][] dp = new long[m][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = points[0][i];
        }
        for (int i = 1; i < m; i++) {
            // left[j] means the max dp[i-1][k] + j - k, where k <= j
            long[] left = getMaxFromLeft(dp[i - 1]);
            // right[j] means the max dp[i-1][k] + k - j, where k >= j
            long[] right = getMaxFromRight(dp[i - 1]);
            for (int j = 0; j < n; j++) {
                dp[i][j] = Math.max(left[j], right[j]) + + points[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[m - 1][i]);
        }
        return res;
    }
    
    // read LC 1014 Best Sightseeing Pair before look at this helper method
    private long[] getMaxFromLeft(long[] nums) {
        long max = nums[0];
        long[] res = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = Math.max(nums[i], max);
            max = Math.max(nums[i], max) - 1;
        }
        return res;
    }
    
    // read LC 1014 Best Sightseeing Pair before look at this helper method
    private long[] getMaxFromRight(long[] nums) {
        long max = nums[nums.length - 1];
        long[] res = new long[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = Math.max(nums[i], max);
            max = Math.max(nums[i], max) - 1;
        }
        return res;
    }
}
