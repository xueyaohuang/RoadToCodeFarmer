class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int a = row -1;
        int b = 0;
        
        while(a >= 0 && b < col){
            if (matrix[a][b] == target){
                return true;
            }
            else if (matrix[a][b] > target){
                a--;
            }
            else {
                b++;
            }
        }
        
        return false;
    }
}
