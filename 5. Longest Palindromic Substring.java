// dp, in a bottom up manner
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        // s.substring(i, j + 1)是否是回文
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        
        // i is the length of the substring
        for (int i = 2; i <= len; i++) {
            // j is the start of the substring
            for (int j = 0; i + j - 1 < len; j++) {
                if (s.charAt(j) == s.charAt(i + j - 1) && (dp[j + 1][i + j - 2] || i < 3)) {
                    dp[j][i + j - 1] = true;
                }
                if (dp[j][i + j - 1] && maxLen < i) {
                    maxLen = i;
                    start = j;
                    end = i + j - 1;
                }
                
            }
        }
        return s.substring(start, end + 1);
    }
}

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int len = s.length();
        // s.substring(i, j + 1)是否是回文
        boolean[][] dp = new boolean[len][len];
        int maxLen = 1;
        int start = 0;
        int end = 0;
        
        // j is the end of the substring
        for (int j = 0; j < len; j++) {
            // i is the start of the substring
            for (int i = 0; i <= j; i++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i < 2)) {
                    dp[i][j] = true;
                }
                if (dp[i][j] && maxLen < j - i + 1) {
                    maxLen = j - i + 1;
                    start = i;
                    end = j;
                }
            }
        }
        return s.substring(start, end + 1);
    }
}
