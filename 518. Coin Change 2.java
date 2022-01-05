/*
dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
State transition:

1. not using the ith coin, only using the first i-1 coins to make up amount j, then we have dp[i-1][j] ways.
2. using the ith coin(至少用一个), since we can use unlimited same coin, we need to know how many way to make up amount
j - coins[i] by using first i coins(including ith), which is dp[i][j-coins[i]].
Initialization: dp[i][0] = 1

why dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0); Here's my thought:
dp[i - 1][j]: means the number of combinations if we compeletly don't use the ith coin
dp[i][j - coins[i - 1]]: we must use at least one of the ith coin, so we expell the ith coin from j (Exclusive, opposite to the upper condition)
*/
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= coins[i - 1]) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
                 
            }
        }
        return dp[coins.length][amount];
    }
}

// Now we can see that dp[i][j] only rely on dp[i-1][j] and dp[i][j-coins[i]], 
// then we can optimize the space by only using one-dimension array.

class Solution {
    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        if (coins == null || coins.length == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        int len = coins.length;
        dp[0] = 1;
        
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    dp[j] += dp[j - coins[i - 1]];
                }
                 
            }
        }
        return dp[amount];
    }
}
