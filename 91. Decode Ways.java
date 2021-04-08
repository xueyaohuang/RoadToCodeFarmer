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

class Solution {
    public int numDecodings(String s) {
        int twoBefore = 1;
        int oneBefore = 1;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int one = 0;
            int two = 0;
            if (c == '0') {
                if (i == 0 || s.charAt(i - 1) == '0' || s.charAt(i - 1) > '2') {
                    return 0;
                }
                two = 1;
            } else {
                one = 1;
                if (i > 0 && s.charAt(i - 1) != '0') {
                    int num = Integer.valueOf(s.substring(i - 1, i + 1));
                    if (num <= 26) {
                        two = 1;
                    }
                }
            }
            res = (one > 0 ? oneBefore : 0) + (two > 0 ? twoBefore  : 0);
            twoBefore = oneBefore;
            oneBefore = res;
        }
        return res;
    }
}
