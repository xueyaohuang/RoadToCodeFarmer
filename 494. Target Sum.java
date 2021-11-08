/*
sum(P) - sum(N) = target
sum(P) + sum(N) = sum(nums)
we have: 2 * sum(P) = target + sum(nums)

So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2

Note that the above formula has proved that target + sum(nums) must be even
*/

class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        // target >= -sum, so (sum + target) / 2 must be non negative
        if (target > sum || target < -sum || (sum + target) % 2 != 0) {
            return 0;
        }
        return subsetSum(nums, (sum + target) / 2);
    }
    
    private int subsetSum(int[] nums, int target) {
        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][target];
    }
}

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

/*
this is a classic knapsack problem
in knapsack, we decide whether we choose this element or not
in this question, we decide whether we add this element or minus it

So start with a two dimensional array dp[i][j] which means the number of ways for first i-th element to reach a sum j

we can easily observe that dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i],

Another part which is quite confusing is return value, here we return dp[sum+S], why is that?
because dp's range starts from -sum --> 0 --> +sum
so we need to add sum first, then the total starts from 0, then we add S

Actually most of Sum problems can be treated as knapsack problem, hope it helps
*/

public int findTargetSumWays(int[] nums, int S) {
      
      int sum = 0;
      for(int n: nums){
        sum += n;
      }
      if (S < -sum || S > sum) { return 0;}
        
      int[][] dp = new int[nums.length + 1][ 2 * sum + 1];
      dp[0][0 + sum] = 1; // 0 + sum means 0, 0 means -sum,  check below graph
      for(int i = 1; i <= nums.length; i++){
        for(int j = 0; j < 2 * sum + 1; j++){
          
          if(j + nums[i - 1] < 2  * sum + 1) dp[i][j] += dp[i - 1][j + nums[i - 1]];
          if(j - nums[i - 1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
        }
      }
      return dp[nums.length][sum + S];
    }

