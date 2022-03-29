class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}

class Solution {
    public int longestPalindromeSubseq(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int len  = s.length();
        int[][] dp = new int[len][len];
        // 从后往前
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
//         // 从前往后
//         for (int j = 0; j < len; j++) {
//             dp[j][j] = 1;
//             for (int i = j - 1; i >= 0; i--) {
//                 if (s.charAt(i) == s.charAt(j)) {
//                     dp[i][j] = dp[i + 1][j - 1] + 2;
//                 } else {
//                     dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
//                 }
//             }
//         }
        return dp[0][len - 1];
    }
}
