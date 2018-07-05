class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        int sum = 1;
        while (sum <= amount) {
            int min = -1;
            for (int coin : coins) {
                if (sum >= coin && dp[sum - coin] != -1) {
                    int temp = dp[sum - coin] + 1;
                    min = min == -1 ? temp : Math.min(min, temp);
                }
            }
            dp[sum] = min;
            sum++;
        }
        return dp[amount];
    }
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        
        if (coins == null || coins.length == 0) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }
        
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        
        int min = Integer.MAX_VALUE;
        for (int i : coins) {
            min = Math.min(min, i);
        }
        if (amount < min) {
            return -1;
        }
        dp[min] = 1;
        
        for (int i = min + 1; i <= amount; i++) {
            for (int j : coins) {
                if (i - j >= 0 && dp[i - j] != -1) {
                    dp[i] = dp[i] == -1 ? Integer.MAX_VALUE : dp[i];
                    dp[i] = Math.min(dp[i], 1 + dp[i - j]);
                }
            }
        }
        return dp[amount];
    }
}
