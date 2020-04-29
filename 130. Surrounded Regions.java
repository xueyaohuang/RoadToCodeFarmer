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

class Solution {
    boolean surrounded;
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        List<int[]> coord = new ArrayList<>();
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    surrounded = true;
                    isSurrounded(board, i, j, visited);
                    if (surrounded) {
                        coord.add(new int[]{i, j});
                    }
                }
            }
        }
        for (int[] c : coord) {
            eliminateO(board, c[0], c[1]);
        }
    }
    
    
    private void isSurrounded(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] == 'X') {
            return;
        }
        visited[i][j] = true;
        if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
            surrounded = false;
            return;
        }
        isSurrounded(board, i + 1, j, visited);
        isSurrounded(board, i - 1, j, visited);
        isSurrounded(board, i, j + 1, visited);
        isSurrounded(board, i, j - 1, visited);
    }
    
    private void eliminateO(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 'X') {
            return;
        }
        board[i][j] = 'X';
        eliminateO(board, i + 1, j);
        eliminateO(board, i - 1, j);
        eliminateO(board, i, j + 1);
        eliminateO(board, i, j - 1);
    }
}
