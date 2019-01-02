class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = n - 1, top = 0, down = m - 1;
        
        while (res.size() < m * n) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;
            for (int i = top; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if (res.size() < m * n) {
                for (int j = right; j >= left; j--) {
                    res.add(matrix[down][j]);
                }
                down--;
            }
            if (res.size() < m * n) {
                for (int i = down; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++;
            }
        }
        return res;   
    }
}

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0;
        int col = 0;
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m * n; i++) {
            res.add(matrix[row][col]);
            visited[row][col] = true;
            // right
            if (count % 4 == 0) {
                if (col + 1 == n || visited[row][col + 1] == true) {
                    row++;
                    count++;
                } else {
                    col++;
                } 
            } else if (count % 4 == 1) {  // down
                if (row + 1 == m || visited[row + 1][col] == true) {
                    col--;
                    count++;
                } else {
                    row++;
                }
            } else if (count % 4 == 2) {  // left
                if (col == 0 || visited[row][col - 1] == true) {
                    row--;
                    count++;
                } else {
                    col--;
                }
            } else {  // up
                if (row == 0 || visited[row - 1][col] == true) {
                    col++;
                    count++;
                } else {
                    row--;
                }
            }
        }  
     return res;   
    }
}
