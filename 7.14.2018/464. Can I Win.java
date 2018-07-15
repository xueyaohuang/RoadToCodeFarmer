class Solution {
    private byte[] memo;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int M = maxChoosableInteger, T = desiredTotal;
        int sum = M * (M + 1) / 2;
        if (sum < T) {
            return false;
        }
        if (T <= 0) {
            return true;
        }
        memo = new byte[1 << M];
        return canWin(M, T, 0);
    }
    
    private boolean canWin(int M, int T, int state) {
        if (T <= 0) {
            return false;
        }
        if (memo[state] != 0) {
            return memo[state] == 1;
        }
        for (int i = 0; i < M; i++) {
            if ((state & (1 << i)) > 0) continue;
            if (!canWin(M, T - (i + 1), state | (1 << i))) {
                memo[state] = 1;
                return true;
            }
        }
        memo[state] = -1;
        return false;
    }
}
