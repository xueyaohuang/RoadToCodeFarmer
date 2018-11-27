public class LongestCommonSubstring {

    public static int longestCommonSubstringLength(String s, String t) {
        int res = 0;
        int lens = s.length();
        int lent = t.length();
        int[][] dp = new int[lens + 1][lent + 1];
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    public static String longestCommonSubstring(String s, String t) {
        int maxLen = 0;
        int end = 0;
        int lens = s.length();
        int lent = t.length();
        int[][] dp = new int[lens + 1][lent + 1];
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                if (maxLen < dp[i][j]) {
                    maxLen = dp[i][j];
                    end = i;
                }
            }
        }
        return maxLen == 0 ? "" : s.substring(end - maxLen + 1, end + 1);
    }
    public static void main(String[] args) {
        String s = "OldSite:GeeksforGeeks.org"; 
        String t = "NewSite:GeeksQuiz.com"; 
        int len = longestCommonSubstringLength(s, t);
        String res = longestCommonSubstring(s, t);
        System.out.println(len);
        System.out.println(res);
    }
}
