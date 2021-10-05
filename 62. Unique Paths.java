class Solution {
    public int uniquePaths(int m, int n) {
        int[][] ways = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    ways[i][j] = 1;
                } else {
                    ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
                }
            }
        }
        return ways[m - 1][n - 1];
    }
}

class Solution {
    public int uniquePaths(int m, int n) {
        int[] ways = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    ways[j] = 1;
                } else {
                    ways[j] = ways[j] + ways[j - 1]; // ways[j] += ways[j - 1];
                }
            }
        }
        return ways[n - 1];
    }
}

// C(m+n, n)
class Solution {
    public int uniquePaths(int m, int n) {
        int steps = m + n - 2;
        int k = Math.min(m, n)  - 1;
        double res = 1;
        for (int i = 1; i <= k; i++) {
            res *= steps - k + i;
            res /= i;
        }
        return (int)Math.ceil(res);
    }
}
