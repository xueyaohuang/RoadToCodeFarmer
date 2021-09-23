// https://leetcode.com/problems/egg-drop-with-2-eggs-and-n-floors/discuss/1246123/Java-dp-solution-for-n-floors-and-x-eggs-(if-you-could-not-come-up-with-the-math-solution)
class Solution {
    public int twoEggDrop(int n) {
        int eggs = 2;
        int[][] dp = new int[n + 1][eggs + 1];
        return nEggDrop(n, eggs, dp);
    }
    
    private int nEggDrop(int n, int eggs, int[][] dp) {
        // 注意要检查n<=1而不是n==1
        if (n <= 1 || eggs == 1) {
            return n;
        }
        if (dp[n][eggs] > 0) {
            return dp[n][eggs];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, 1 + Math.max(nEggDrop(i - 1, eggs - 1,  dp), nEggDrop(n - i, eggs, dp)));
        }
        // 注意记住当前最小值
        dp[n][eggs] = min;
        return min;
    }
}

// 两个蛋
class Solution {
    public int twoEggDrop(int n) {
        int[] dp = new int[n + 1];
        return EggDrop(n, dp);
    }
    
    private int EggDrop(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] > 0) {
            return dp[n];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            min = Math.min(min, 1 + Math.max(i - 1, EggDrop(n - i, dp)));
        }
        // 注意记住当前最小值
        dp[n] = min;
        return min;
    }
}
