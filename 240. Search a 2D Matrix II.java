class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        // 从左下角开始，也可以从右上角开始
        int a = row -1;
        int b = 0;
        
        while(a >= 0 && b < col){
            if (matrix[a][b] == target){
                return true;
            }
            else if (matrix[a][b] > target){
                a--;  // 不需要b--,因为刚从左边一列较大一个值（比如x）移过来，如过要找的数是y，y肯定比x大，所以不需要b--
            }
            else {
                b++;
            }
        }
        
        return false;
    }
}
