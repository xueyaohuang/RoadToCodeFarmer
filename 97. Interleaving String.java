class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3 == null) {
            return false;
        }
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) {
            return false;
        }
        // dp[i][j]表示s3[:i+j-1]是否是s1[:i],s2[:j]的interleave
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        
        // 像这样dp table多出一维的情况，初始化时不仅要考虑dp[0][0],还要考虑dp[i][0],dp[0][i]
        dp[0][0] = true;
        for (int i = 1; i < dp.length; i++){
            dp[i][0] = dp[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }

        for (int i = 1; i < dp[0].length; i++){
            dp[0][i] = dp[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = true;
                }
                if (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[len1][len2];
    }
}
