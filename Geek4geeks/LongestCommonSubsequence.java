public class LongestCommonSubsequence {

    public static int longestCommonSubsequenceLength(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        int[][] dp = new int[lens + 1][lent + 1];
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[lens][lent];
    }

    public static String longestCommonSubsequence(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        int[][] dp = new int[lens + 1][lent + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        int i = lens;
        int j = lent;
        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                sb.append(s.charAt(i - 1));
                i--;
                j--;
            } else {
                if (dp[i][j - 1] > dp[i - 1][j]) {
                    j--;
                } else {
                    i--;
                }
            }
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        String s = "AGGTAB"; 
        String t = "GXTXAYB"; 
        int len = longestCommonSubsequenceLength(s, t);
        String res = longestCommonSubsequence(s, t);
        System.out.println(len);
        System.out.println(res);
    }
}
