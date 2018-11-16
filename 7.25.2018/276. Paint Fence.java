class Solution {
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }
        // same[i]: 第1个post的color跟第i-1个post的color一样
        int[] same = new int[n];
        // different[i]: 第1个post的color跟第i-1个post的color不一样
        int[] different = new int[n];
        same[0] = k;
        same[1] = k;
        different[0] = k;
        different[1] = k * (k - 1);
        
        for (int i = 2; i < n; i++) {
            // i和i-1一样，那么i-1必须和i-2不一样
            same[i] = different[i - 1];
            // i和i-1不一样，那么i-1和i-2可以一样，也可以不一样。i和i-1不一样，多益第i个的选择就有k-1种
            different[i] = (k - 1) * (same[i - 1] + different[i - 1]);
        }
        return same[n - 1] + different[n - 1];
    }
}

// optimize sapce complexity
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
