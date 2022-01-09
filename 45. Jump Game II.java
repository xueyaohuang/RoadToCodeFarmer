// The main idea is based on greedy. Let's say the range of the current jump is
// [curBegin, curEnd], curFarthest is the farthest point that all points in 
// [curBegin, curEnd] can reach. Once the current point reaches curEnd, then 
// trigger another jump, and set the new curEnd with curFarthest, then keep the
// above steps, as the following:

// This is an implicit bfs solution. i == curEnd means you visited all the items 
// on the current level. Incrementing jumps++ is like incrementing the level you
// are on. And curEnd = curFarthest is like getting the queue size (level size) 
// for the next level you are traversing.
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int maxCanReach = 0;
        int jump = 0;
        int curEnd = 0;
        for (int i = 0; i < n - 1; i++) {
            maxCanReach = Math.max(maxCanReach, i + nums[i]);
            if (i == curEnd) {
                curEnd = maxCanReach;
                jump++;
            }
            if (curEnd >= n - 1) {
                return jump;
            }
        }
        return jump;
    }
}

// dp
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= nums[i] && i + j < n; j++) {
                dp[i + j] = Math.min(dp[i] + 1, dp[i + j]);
            }
            if (dp[n - 1] != Integer.MAX_VALUE) {
                return dp[n - 1];
            }
        }
        return dp[n - 1];
    }
}
