class Solution {
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        int diff = k * k - k; // at i, i - 1 and i - 2 has different color
        int same = k; // at i, i - 1 and i - 2 has same color. So at i, total (diff + same) ways of painting
        for (int i = 3; i <= n; i++) {
            int temp = same;
            same = diff;
            diff = (diff + temp) * (k - 1);
        }
        return diff + same;
    }
}
