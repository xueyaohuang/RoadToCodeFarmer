class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int row = A.length;
        int col = B[0].length;
        int n = A[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < n; k++) {
                    if (A[i][k] != 0 && B[k][j] != 0) {
                        res[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return res;
    }
}

class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int row = A.length;
        int col = B[0].length;
        int n = A[0].length;
        int[][] res = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < col; j++) {
                        if (B[k][j] != 0) {
                            res[i][j] += A[i][k] * B[k][j];
                        }
                    }
                }
            }
        }
        return res;
    }
}
