// suppose n is the number of empty grid, time complexity is O(9^n)
class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][][] cell = new boolean[3][3][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    cell[i / 3][j / 3][num] = true;
                }
            }
        }
        canSolve(board, row, col, cell, 0);
    }
    
    private boolean canSolve(char[][] board, boolean[][] row, boolean[][] col, boolean[][][] cell, int count) {
        if (count > 80) {
            return true;
        }
        int i = count / 9;
        int j = count % 9;
        if (board[i][j] !=  '.') {
            return canSolve(board, row, col, cell, count + 1);
        }
        for (char c = '1'; c <= '9'; c++) {
            int num = c - '0';
            if (row[i][num] || col[j][num] || cell[i / 3][j / 3][num]) {
                continue;
            }
            board[i][j] = c;
            row[i][num] = true;
            col[j][num] = true;
            cell[i / 3][j / 3][num] = true;
            if (canSolve(board, row, col, cell, count + 1)) {
                return true;
            }
            board[i][j] = '.';
            row[i][num] = false;
            col[j][num] = false;
            cell[i / 3][j / 3][num] = false;
        }
        return false;
    }
}

// suppose n is the number of empty grid, time complexity is O(9^n)
class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null) {
            return;
        }
        canSolve(board);
    }
    
    private boolean canSolve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValidFill(board, i, j, c)) {
                            board[i][j] = c;
                            if (canSolve(board)) {
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValidFill(char[][] board, int x, int y, char c) {
        for (int j = 0; j < board[0].length; j++) {
            if (board[x][j] == c) {
                return false;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i][y] == c) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[x / 3 * 3 + i][y / 3 * 3 + j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}


class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][][] block = new boolean[3][3][10];
        
        // init 
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int cur = board[i][j] - '0';
                    row[i][cur] = true;
                    col[j][cur] = true;
                    block[i / 3][j / 3][cur] = true;    
                }
            }
        }
        
        helper(board, 0, row, col, block);
    }
    
    static boolean helper(char[][] board, int count, boolean[][] row, boolean[][] col, boolean[][][] block) {
        if (count > 80) { // 大于80，至少81个，已经填满了，所以return true
            return true;
        }
        int x = count / 9;
        int y = count % 9;
        if (board[x][y] != '.') {
            return helper(board, count + 1, row, col, block);
        }
        for (int i = 1; i <= 9; i++) {
            if (row[x][i] || col[y][i] || block[x / 3][y / 3][i]) {
                continue;
            }
            char c = (char) (i + '0');
            board[x][y] = c;
            row[x][i] = true;
            col[y][i] = true;
            block[x / 3][y / 3][i] = true;
            if (helper(board, count + 1, row, col, block)) {
                return true;
            }
            board[x][y] = '.';
            row[x][i] = false;
            col[y][i] = false;
            block[x / 3][y / 3][i] = false;
        }
        return false;
    }
}
