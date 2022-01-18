// with (i, j) as the bottom right, only looking for square with length of current max + 1 (next eligible max length)
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + mat[i][j];
                // look for max + 1
                if (i >= max && j >= max &&
                   sum[i + 1][j + 1] - sum[i + 1 - (max + 1)][j + 1] - sum[i + 1][j + 1 - (max + 1)] + sum[i + 1 - (max + 1)][j + 1 - (max + 1)]<= threshold) {
                    max++;
                }
            }
        }
        return max;
    }
}
