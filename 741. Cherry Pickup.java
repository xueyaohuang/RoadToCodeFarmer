/*
Special credit to:
https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-741-cherry-pickup/

Assupmtion:
    Go from (0, 0) -> (n-1, n-1) -> (0, 0) can be opt to two men go from (0, 0) -> (n-1, n-1) together, but when they go into
the same cell, the cur state can only be added 1 (use once)
Using DP to solve the problem:
    1.  dp[x1][y1][x2] to represent the largest ans we can get when first guy (marked as A) at(x1, y2) and second guy(marked as B) at (x2, x1 + y1 - x2)
        * because we can only go right and down, so we have x1 + y1 = x2 + y2
    2.  Induction: every time we calculate the maximum of :
        * dp[x1 - 1][y1][x2] : A down, B right
        * dp[x1][y1 - 1][x2] : A right, B right
        * dp[x1 - 1][y1][x2 - 1]: A down, B down
        * dp[x1][y1 - 1][x2 - 1]: A right, B down
        if the Max of these values is negative, then we don't have a path to this point
        else we have: dp[x1][y1][x2] = Max + grid[x1 - 1][y1 - 1] + grid[x2 - 1][y2 - 1](if x1 != x2 && y1 != y2) else we
        only add once.
    3.  Base case;
        we use dp[][][]from 1 - n, so we have:
            dp[1][1][1] = 1 and all other values are MIN_VALUE
    4.  Ans:
        dp[n][n][n]
    5.  Direction:
        from top left -> bottom right
    6.  Time:
        O(n^3)
        Space:
        O(n^3)
*/

class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        // 有些位置无解，一开始全部赋值是最小值
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
            }
        }
        dp[1][1][1] = grid[0][0];
        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = 1; x2 <= n; x2++) {
                    int y2 = x1 + y1 - x2;
                    if (dp[x1][y1][x2] > 0 || y2 < 1 || y2 > n || grid[x1 - 1][y1 - 1] == -1 || grid[x2 - 1][y2 - 1] == -1) {
                        continue;
                        // have already detected || out of boundary || cannot access 
                    }
                    int cur = Math.max(Math.max(dp[x1 - 1][y1][x2], dp[x1 - 1][y1][x2 - 1]), Math.max(dp[x1][y1 - 1][x2], dp[x1][y1 - 1][x2 - 1]));
                    if (cur < 0) {
                        // 当前问题无解
                        continue;
                    }
                    dp[x1][y1][x2] = cur + grid[x1 - 1][y1 - 1];
                    if (x1 != x2) {
                        dp[x1][y1][x2] += grid[x2 - 1][y2 - 1];
                    }
                }
            }
        }
        return dp[n][n][n] <= 0 ? 0 : dp[n][n][n];
    }
}
