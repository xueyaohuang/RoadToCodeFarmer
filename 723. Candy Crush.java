class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length, n = board[0].length;
        boolean hasCrush = false;
        // crush horizontally
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n - 2; j++) {
                int value = Math.abs(board[i][j]);
                if (value != 0 && value == Math.abs(board[i][j + 1]) && value == Math.abs(board[i][j + 2])) {
                    board[i][j] = board[i][j + 1] = board[i][j + 2] = -value;
                    hasCrush = true;
                }
            }
        }
        // crush vertically
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m - 2; i++) {
                int value = Math.abs(board[i][j]);
                if (value != 0 && value == Math.abs(board[i + 1][j]) && value == Math.abs(board[i + 2][j])) {
                    board[i][j] = board[i + 1][j] = board[i + 2][j] = -value;
                    hasCrush = true;
                }
            }
        }
        // drop down
        for (int j = 0; j < n; j++) {
            int bottom = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (board[i][j] > 0) {
                    board[bottom][j] = board[i][j];
                    bottom--;
                }
            }
            for (int i = bottom; i >= 0; i--) {
                board[i][j] = 0;
            }
        }
        // recurse
        return hasCrush ? candyCrush(board) : board;
    }
}
