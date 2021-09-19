/*
The idea is to find the longest palindromic subsequence(lps) of the given string.
if original string - lps <= k, the string is k-palindrome.
*/
class Solution {
    public boolean isValidPalindrome(String s, int k) {
        if (s.length() <= k) {
            return true;
        }
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int j = 0; j < len; j++) {
            dp[j][j] = 1;
            for (int i = j - 1; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return len - dp[0][len - 1] <= k;
    }
}
// Another wany to get longest palindromic subsequence: LCS of the given string & its reverse will be the longest palindromic sequence.
