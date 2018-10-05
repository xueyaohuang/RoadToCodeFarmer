// 注意longest common substring 和 subsequence的区别，也就是连续和不连续的区别。

class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int lena = A.length;
        int lenb = B.length;
        int[][] dp = new int[lena + 1][lenb + 1];
        int maxLen = 0;
        
        for (int i = 1; i <= lena; i++) {
            for (int j = 1; j <= lenb; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen;
    }
}

// 如果是subsequence，那么就是

class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int lena = A.length;
        int lenb = B.length;
        int[][] dp = new int[lena + 1][lenb + 1];
        
        for (int i = 1; i <= lena; i++) {
            for (int j = 1; j <= lenb; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[lena][lenb];
    }
}
