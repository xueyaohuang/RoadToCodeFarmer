class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int i : nums) {
            for (int j = sum; j >= i; j--) {
                dp[j] = dp[j] || dp[j - i];
            }
        }
        if (dp[sum]) {
            return true;
        }
        return dp[sum];
    }
}

// inner for loop must be reversed, the key point is otherwise dp[j - i] will be used more than once.
