// 三重dp，类似于扎气球
class Solution {
    public int minCost(int n, int[] cuts) {
        int[] points = new int[cuts.length + 2];
        points[0] = 0;
        points[1] = n;
        for (int i = 2; i < points.length; i++) {
            points[i] = cuts[i - 2];
        }
        Arrays.sort(points);
        int[][] dp = new int[points.length][points.length];
        // i is start
        // j is end
        // k is the point in between
        // if i == j, there is no cost, so j can start from i + 1
        for (int i = points.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    int cur = dp[i][k] + dp[k][j] + points[j] - points[i];
                    dp[i][j] = Math.min(dp[i][j] == 0 ? Integer.MAX_VALUE : dp[i][j], cur);
                }
            }
        }
        return dp[0][dp.length - 1];
    }
}
