// 背包问题的变化
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = getSum(nums);
        if (sum % 2 != 0) {
            return false;
        }
        sum /= 2;
        int len = nums.length;
        boolean[][] canPartition = new boolean[len + 1][sum + 1];
        for (int i = 0; i <= len; i++) {
            canPartition[i][0] = true;
        }
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= sum; j++) {
                canPartition[i][j] = canPartition[i - 1][j]; // 注意这一步不能少
                if (j >= nums[i - 1]) {
                    // 不用nums[i - 1]和用nums[i - 1]
                    canPartition[i][j] = canPartition[i - 1][j] || canPartition[i - 1][j - nums[i - 1]];
                }
            }
            // 检查提前退出
            if (canPartition[i][sum]) {
                return true;
            }
        }
        return canPartition[len][sum];
    }
    
    private int getSum(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum;
    }
}

// 优化空间复杂度，内层的for loop需要反转
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
            if (dp[sum]) {
                return true;
            }
        }
        return dp[sum];
    }
}

// inner for loop must be reversed, the key point is otherwise dp[j - i] will be used more than once.
