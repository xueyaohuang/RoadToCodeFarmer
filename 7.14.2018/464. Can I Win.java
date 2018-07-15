/*
After solving several "Game Playing" questions in leetcode, I find them to be pretty similar. Most of them can be solved
using the top-down DP approach, which "brute-forcely" simulates every possible state of the game. The key part for the
top-down dp strategy is that we need to avoid repeatedly solving sub-problems. Instead, we should use some strategy to
"remember" the outcome of sub-problems. Then when we see them again, we instantly know their result. 
*/

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
