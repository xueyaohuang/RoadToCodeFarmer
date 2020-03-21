class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row * col - 1;
        int mid;
        while (start < end){
            mid = start + (end - start) / 2;
            int i = mid / col;
            int j = mid % col;
            if (matrix[i][j] == target){
                return true;
            } else if (matrix[i][j] < target){
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return matrix[start / col][start % col] == target;
    }
}
