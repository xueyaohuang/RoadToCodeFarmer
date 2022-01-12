/*
So essentially, the subproblem is

Suppose you are at position X of the array
What is the maximum possible sum to this point?
so you go back k steps

you choose the maximum from the following combinations:

dp_sum[X - 1] + max(A[X])*1                            // current partition length is 1
dp_sum[X - 2] + max(A[X-1], A[X])*2                    // current partition length is 2
dp_sum[X - 3] + max(A[X-2], A[X-1], A[X])*3            // current partition length is 3
.....                                                  .....
dp_sum[X - k] + max(A[X-(k-1)] ..... A[X])*k           // current partition length is k
*/
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int maxOfK = 0;
            int curMax = 0;
            for (int j = 1; j <= k && i - j >= 0; j++) {
                maxOfK = Math.max(maxOfK, arr[i - j]);
                curMax = Math.max(curMax, dp[i - j] + maxOfK * j);
            }
            dp[i] = curMax;
        }
        return dp[n];
    }
}
