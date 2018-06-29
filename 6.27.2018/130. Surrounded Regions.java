class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length <= 2) {
            return;
        }
        
        int row = board.length;
        int col = board[0].length;
        
        for (int i = 0; i < col; i++) {
            if (board[0][i] == 'O') {
                merge(board, 0, i);
            }
            if (board[row - 1][i] == 'O') {
                merge(board, row - 1, i);
            }
        }
        
        for (int i = 0; i < row; i++) {
            if (board[i][0] == 'O') {
                merge(board, i, 0);
            }
            if (board[i][col - 1] == 'O') {
                merge(board, i, col - 1);
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    public void merge(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '1';
        }
        if (i > 0 && board[i - 1][j] == 'O') {
            merge(board, i - 1, j);
        }
        if (i < board.length - 2 && board[i + 1][j] == 'O') {
            merge(board, i + 1, j);
        }
        if (j > 0 && board[i][j - 1] == 'O') {
            merge(board, i, j - 1);
        }
        if (j < board[0].length - 2 && board[i][j + 1] == 'O') {
            merge(board, i, j + 1);
        }
    }
}
