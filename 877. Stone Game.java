// Since there are even numbers, and sum is odd. You may decide to pick either all the odd or all the even terms,
// and the other person can only pick the other type of position. So if sum of all even terms is larger,
// you start with last one, otherwise, you start with the first one.

class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}


/*
dp[i][j] means the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
You can first pick piles[i] or piles[j].

If you pick piles[i], your result will be piles[i] - dp[i + 1][j]
If you pick piles[j], your result will be piles[j] - dp[i][j - 1]
So we get:
dp[i][j] = max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
We start from smaller subarray and then we use that to calculate bigger subarray.
*/
class Solution {
    public boolean stoneGame(int[] piles) {
        int len = piles.length;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = piles[i];
        }
        for (int l = 1; l < len; l++) { // 区间长度
            for (int i = 0; i + l < len; i++) {
                dp[i][i + l] = Math.max(piles[i] - dp[i + 1][i + l], piles[i + l] - dp[i][i + l - 1]);
            }
        }
        return dp[0][len - 1] > 0;
    }
}

