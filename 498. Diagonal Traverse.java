class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        for (int j = 0; j < n; j++) {
            if (j % 2 == 0) {
                LinkedList<Integer> temp = new LinkedList<>();
                for (int i = 0; i < Math.min(j + 1, m); i++) {
                    temp.addFirst(matrix[i][j - i]);
                }
                res.addAll(temp);
            } else {
                for (int i = 0; i < Math.min(j + 1, m); i++) {
                    res.add(matrix[i][j - i]);
                }
            }
        }
        
        for (int j = 1; j < m; j++) {
            if ((n + j - 1) % 2 == 0) {
                LinkedList<Integer> temp = new LinkedList<>();
                for (int i = j; i < Math.min(m, j + n); i++) {
                    temp.addFirst(matrix[i][j + n - 1 - i]);
                }
                res.addAll(temp);
            } else {
                for (int i = j; i < Math.min(m, j + n); i++) {
                    res.add(matrix[i][j + n - 1 - i]);
                }
            }
        }
        
        for (int i = 0; i < m * n; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}

// best！
class Solution {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = 0;
        int[] res = new int[m * n];
        for (int i = 0; i < res.length; i++) {
            res[i] = matrix[row][col];
            if ((row + col) % 2 == 0) { // going up
                // 必须先检查col == n - 1，因为有可能col == n - 1和row == 0同时满足（右上角），这时必须row
                // 若先检查row == 0，会col++，数组越界
                if (col == n - 1) { 
                    row++;
                } else if (row == 0) {
                    col++;
                } else {
                    row--;
                    col++;
                }
            } else { // going down
                // 同理，必须先检查row == m - 1
                if (row == m - 1) {
                    col++;
                } else if (col == 0) {
                    row++;
                } else {
                    row++;
                    col--;
                }
            }
        }
        return res;
    }
}
    
    
    
