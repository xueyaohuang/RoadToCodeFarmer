class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum < S || -sum > S || (sum + S) % 2 == 1) {
            return 0;
        }
        int target = (sum + S) / 2;
        return subSetSum(nums, target);
    }
    
    private int subSetSum(int[] nums, int target) {
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
