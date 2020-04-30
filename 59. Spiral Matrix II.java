class Solution {
    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int i = 1;
        int[][] res = new int[n][n];
        while (i <= n * n) {
            for (int col = left; col <= right; col++) {
                res[top][col] = i++;
            }
            top++;
            for (int row = top; row <= bottom; row++) {
                res[row][right] = i++;
            }
            right--;
            for (int col = right; col >= left; col--) {
                res[bottom][col] = i++;
            }
            bottom--;
            for (int row = bottom; row >= top; row--) {
                res[row][left] = i++;
            }
            left++;
        }
        return res;
    }
}
