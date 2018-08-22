class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        
        int len = matrix.length;
        int high = matrix[len - 1][len - 1];
        int low = matrix[0][0];
        
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = numNoMoreThan(matrix, mid);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;       
    }
    
    private int numNoMoreThan(int[][] matrix, int val) {
        
        int len = matrix.length;
        int i = len - 1;
        int j = 0;
        int res = 0;
        
        while (i >= 0 && j < len) {
            if (matrix[i][j] > val) {
                i--;
            } else {
                res += i + 1;
                j++;
            }
        }        
        return res;
    }
}
