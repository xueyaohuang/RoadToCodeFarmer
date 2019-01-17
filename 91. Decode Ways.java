// 类似上楼梯
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1; // s是valid，第一位必定不是0
        // dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int a = Integer.valueOf(s.substring(i - 1, i));
            int b = Integer.valueOf(s.substring(i - 2, i));
            if (a != 0) {
                dp[i] += dp[i - 1];
            }
            if (b >= 10 && b <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
