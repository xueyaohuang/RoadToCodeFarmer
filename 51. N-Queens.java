/*
任意两个皇后不能位于同一行，同一列，同一斜线
The number of columns is n, the number of 45° diagonals is 2 * n - 1, 
the number of 135° diagonals is also 2 * n - 1. When reach [row, col],
the column No. is col, the 45° diagonal No. is row + col and the 135° 
diagonal No. is n - 1 + col - row. We can use three arrays to indicate
if the column or the diagonal had a queen before, if not, we can put
a queen in this position and continue.
*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        boolean[] used = new boolean[n * 5 - 2];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtracking(res, used, board, 0, n);
        return res;
    }
    
    private void backtracking(List<List<String>> res, boolean[] used, char[][] board, int row, int n) {
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (char[] ca : board) {
                temp.add(new String(ca));
            }
            res.add(temp);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            // when reach [row, col], the subscript of column is col,
            // the subscript of 45° diagonal is n + row + col 
            // the subscript of 135° diagonal is n + (2 * n - 1) + (n - 1 + col - row).
            if (!used[col] && !used[n + row + col] && !used[4 * n - 2 + col - row]) {
                used[col] = true;
                used[n + row + col] = true;
                used[4 * n - 2 + col - row] = true;
                
                board[row][col] = 'Q';
                backtracking(res, used, board, row + 1, n);
                
                board[row][col] = '.';
                used[col] = false;
                used[n + row + col] = false;
                used[4 * n - 2 + col - row] = false;
            }
        }
    }
}
