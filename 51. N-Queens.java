/*
任意两个皇后不能位于同一行，同一列，同一斜线
we can go row by row, and in each position, we need to check if the column, the 45° diagonal and the 135° diagonal had a queen before.
if not, we can put a queen in this position and continue.
use 3 boolean arrays, 由于是一行一行走的，所以需要列，45° diagonal and the 135° diagonal的visited array
*/

class Solution {
    public List<List<String>> solveNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] dia = new boolean[2 * n - 1];
        boolean[] antiDia = new boolean[2 * n - 1];
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        List<List<String>> res = new ArrayList<>();
        backtracking(board, res, col, dia, antiDia, 0, n);
        return res;
    }
    
    private void backtracking(char[][] board, List<List<String>> res, boolean[] col, boolean[] dia, boolean[] antiDia, int row, int n) {
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (char[] ca : board) {
                temp.add(String.valueOf(ca));
            }
            res.add(temp);
            return;
        }
        // i is the column index
        for (int i = 0; i < n; i++) {
            if (col[i] || dia[row + i] || antiDia[row - i + n - 1]) {
                continue;
            }
            // place
            board[row][i] = 'Q';
            col[i] = true;
            dia[row + i] = true;
            antiDia[row - i + n - 1] = true;
            // go to next level
            backtracking(board, res, col, dia, antiDia, row + 1, n);
            // go back
            board[row][i] = '.';
            col[i] = false;
            dia[row + i] = false;
            antiDia[row - i + n - 1] = false;
        }
    }
}
