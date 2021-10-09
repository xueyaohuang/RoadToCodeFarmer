class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[0].length - 1; j++) {
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m - 1; i++) {
            int temp = matrix[i][0];
            for (int j = 1; i + j < m && j < n; j++) {
                if (matrix[i + j][j] != temp) {
                    return false;
                }
            }
        }
        for (int j = 1; j < n - 1; j++) {
            int temp = matrix[0][j];
            for (int i = 1; i < m && i + j < n; i++) {
                if (matrix[i][i + j] != temp) {
                    return false;
                }
            }
        }
        return true;
    }
}
