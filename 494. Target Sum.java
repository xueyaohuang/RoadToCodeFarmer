class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        // 都用加号也不够和都用减号也不够
        if (sum < S || -sum > S || (sum + S) % 2 == 1) {
            return 0;
        }
        return subsetSum(nums, (sum + S) / 2);
    }
    
    private int subsetSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                // 如果这一sum包括nums[i],那么
                // j = j - nums[i] + nums[i]
                // dp[j] += dp[j - nums[i]]
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
