
/*
动态生成Ugly Number序列，一开始只有1，下一个数字是用已有的数字分别乘以2，3，5，然后取最小的那个
idx2表示下一个可以和2相乘的数字的idx，同理idx3，idx5

k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to 1. Then we test:

k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
*/
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int idx2 = 0, idx3 = 0, idx5 = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(dp[idx2] * 2, Math.min(dp[idx3] * 3, dp[idx5] * 5));
            if (dp[i] == dp[idx2] * 2) {
                idx2++;
            }
            if (dp[i] == dp[idx3] * 3) {
                idx3++;
            }
            if (dp[i] == dp[idx5] * 5) {
                idx5++;
            }
        }
        return dp[n - 1];
    }
}
