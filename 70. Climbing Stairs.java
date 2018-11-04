class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int[] ways = new int[n];
        ways[0] = 1;
        ways[1] = 2;
        for (int i = 2; i < n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
        }
        return ways[n - 1];
    }
}

class Solution {
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int twoStepBefore = 1;
        int oneStepBefore = 2;
        int res = 0;
        for (int i = 3; i <= n; i++) {
            res = oneStepBefore + twoStepBefore;
            twoStepBefore = oneStepBefore;
            oneStepBefore = res;
        }
        return res;
    }
}
