class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int left = 0, right = 0, top = 0, bottom = 0, count = 0;
        int m = matrix.length;
        int n = matrix[0].length; 
        while (count < m * n) {
            for (int j = left; j < n - right; j++) {
                list.add(matrix[top][j]);
            }
            top++;
            count += n - right - left;
            for (int i = top; i < m - bottom; i++) {
                list.add(matrix[i][n - 1 - right]);
            }
            right++;
            count += m - bottom - top;
            if (top + bottom < m) {
                for (int j = n - 1 - right; j >= left; j--) {
                    list.add(matrix[m - 1 - bottom][j]);
                }
                bottom++;
                count += n - right - left;
            }
            if (left + right < n) {
                for (int i = m -1 - bottom; i >= top; i--) {
                    list.add(matrix[i][left]);
                }
                left++;
                count += m - bottom - top;
            }
        }
     return list;   
    }
}
