class Solution {
    
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        //Note: n should be <= 32 as int is 32-bit in Java; else it will 1 << 33+ equals 0.
        int n = maxChoosableInteger;
        int sum = n * (n + 1) / 2;
        if (sum < desiredTotal) {
            return false;
        }
        Boolean[] dp = new Boolean[1 << n];
        return canIWin(0, n, desiredTotal, dp);
    }

    private boolean canIWin(int state, int n, int remain, Boolean[] dp) {
        if (remain <= 0) {
            //dp[state] = false;
            return false;   //Base case:
        }

        if (dp[state] == null) {
            dp[state] = false;
            int mask = 1;
            for (int i = n; i > 0; i--) { //Key Point: take from the tail
                int future = state | mask;
                if (future != state && !canIWin(future, n, remain - i, dp)) {
                    dp[state] = true;   //update current status = true
                    break;
                }
                mask <<= 1;
            }
        }
        return dp[state];
    }
}
