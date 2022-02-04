class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] < target) {
                start = mid + 1;
            }  else {
                end = mid;
            }
        }
        return matrix[start / n][start % n] == target;
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }
        return false;
    }
}
