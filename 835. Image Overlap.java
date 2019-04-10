class Solution {
    // Regard B as kernal. Calculate the convolution.
    // The size of convolution result matrix is (2n-1)*(2n-1)
    // find the max value in convolution result matrix
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        int[][] convolution = new int[2 * n - 1][2 * n - 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    for (int x = 0; x < n; x++) {
                        for (int y = 0; y < n; y++) {
                            if (B[x][y] == 1) {
                                convolution[i - x + n - 1][j - y + n - 1]  += 1;
                            }
                        }
                    }
                }
            }
        }
        int res = 0;
        for (int[] con : convolution) {
            for (int i : con) {
                res = Math.max(res, i);
            }
        }
        return res;
    }
}
