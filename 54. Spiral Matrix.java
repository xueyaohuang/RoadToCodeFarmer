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
