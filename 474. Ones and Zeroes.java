// Time Complexity: O(kl + kmn), where k is the length of input string array and l is the average length of a string within the array.
// space complexity can be optimized.
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int[] zerosOnes = countZerosOnes(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zerosOnes[0] && k >= zerosOnes[1]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zerosOnes[0]][k - zerosOnes[1]] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }
    
    private int[] countZerosOnes(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            if (c  == '0') {
                res[0]++;
            } else {
                res[1]++;
            }
        }
        return res;
    }
}

// memo[i][j] = the max number of strings that can be formed with i 0's and j 1's
// from the first few strings up to the current string s
// Catch: have to go from bottom right to top left
// Why? If a cell in the memo is updated(because s is selected),
// we should be adding 1 to memo[i][j] from the previous iteration (when we were not considering s)
// If we go from top left to bottom right, we would be using results from this iteration => overcounting

// optimize space complexity
// 但是j，k要从大到小--扫描，如果还是从小到大，会重复计数。
// 为什么从大到小？
// 之前的dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zerosOnes[0]][k - zerosOnes[1]] + 1);
// 到i的时候，实际上是用的第i - 1轮的值（dp[i - 1][j - zerosOnes[0]][k - zerosOnes[1]]）做更新的。
// 但是如果没有i这个维度，也是要用上一轮的dp[j - zerosOnes[0]][k - zerosOnes[1]]做更新
// 所以在第i轮更新中，要用第i-1轮的dp[j][k]值
// 所以dp[j][k]，j，k大的dp[j][k]要先更新，这样才能用上一轮的dp[j][k]（j，k较小）
// 反之如果先更新j，k小的dp[j][k]，那么当j，k ++变大后，用的dp[j][k]就是这一轮已经更新过的值了！！！！
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= len; i++) {
            int[] zerosOnes = countZerosOnes(strs[i - 1]);
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= zerosOnes[0] && k >= zerosOnes[1]) {
                        dp[j][k] = Math.max(dp[j][k], dp[j - zerosOnes[0]][k - zerosOnes[1]] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }
    
    private int[] countZerosOnes(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            if (c  == '0') {
                res[0]++;
            } else {
                res[1]++;
            }
        }
        return res;
    }
}
