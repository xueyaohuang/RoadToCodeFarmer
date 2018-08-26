class Solution {
    private byte[] memo;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) {
            return true;
        }
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
        if (sum < desiredTotal) {
            return false;
        }
        memo = new byte[1 << maxChoosableInteger];
        return canFirstWin(0, maxChoosableInteger, desiredTotal);
    }
    private boolean canFirstWin(int state, int max, int total) {
        if (total <= 0) { // in the last step, the player already reach desiredTotal
            return false;
        }
        if (memo[state] != 0) { // already has the result
            return memo[state] == 1;
        }
        for (int i = max - 1; i >= 0; i--) { // try every choosable integer
            if ((state & (1 << i)) == 0) { // if not used
                if (!canFirstWin(state | (1 << i), max, total - i - 1)) {
                    memo[state] = 1;
                    return true;
                }
            }
        }
        memo[state] = -1;
        return false;
    }
}
// state is a maxChoosableInteger bit integer, representing whether a number in range 1 to n is used or not.
//(state & (1 << i)) == 0 is true if the number is not used, state | (1 << i) will set the number to be used
