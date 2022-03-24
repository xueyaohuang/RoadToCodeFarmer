// 0/1 knapsacks
// https://leetcode.com/problems/last-stone-weight-ii/discuss/294888/JavaC%2B%2BPython-Easy-Knapsacks-DP
/**
This question eaquals to partition an array into 2 subsets whose difference is minimal
(1) S1 + S2  = S
(2) S1 - S2 = diff  

==> -> diff = S - 2 * S2  ==> minimize diff equals to  maximize S2 

Now we should find the maximum of S2 , range from 0 to S / 2, using dp can solve this

dp[i][j]   = {true if some subset from 1st to i'th has a sum equal to sum j, false otherwise}
i ranges from  {0..n}
j ranges from  {0..sum/2}
    

same as 494. Target Sum and 416. Partition Equal Subset Sum

*/
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int n = stones.length;
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }
        int target = sum / 2;
        boolean[][] dp = new boolean[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= stones[i - 1]) {
                    dp[i][j] |= dp[i - 1][j - stones[i - 1]];
                }
            }
        }
        for (int i = target; i >= 0; i--) {
            if (dp[n][i]) {
                return sum - 2 * i;
            }
        }
        return sum;
    }
}
