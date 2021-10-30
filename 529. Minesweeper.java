/*
This is a typical Search problem, either by using DFS or BFS. Search rules:

If click on a mine ('M'), mark it as 'X', stop further search.
If click on an empty cell ('E'), depends on how many surrounding mine:
2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
*/
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        int row = click[0], col = click[1];
        
        // click revealed square, just return
        if (board[row][col] == 'B' || board[row][col] == 'X') {
            return board;
        }
        
        // click revealed square, just return
        if (board[row][col] >= '1' && board[row][col] <= '8') {
            return board;
        }
        
        
        // click on 'M', mark as 'X' and return
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }
        
        // click on 'E'
        int count = 0;
        for (int i = Math.max(0, row - 1); i <= Math.min(m - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(n - 1, col + 1); j++) {
                if (board[i][j] == 'M') {
                    count++;
                }
            }
        }
        
        if (count > 0) {
            board[row][col] = (char)(count + '0');
            // You should only recursively reveal adjacent cells if the current cell is to reveal 'B',
            // so don't recursively reveal adjacent cells here, just return.
            return board;
        } else {
            board[row][col] = 'B';
            for (int i = Math.max(0, row - 1); i <= Math.min(m - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(n - 1, col + 1); j++) {
                    if (i == row && j == col) {
                        continue;
                    }
                    updateBoard(board, new int[]{i, j});
                }
            }
        }
        return board;
    }
}

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }
        
        int row = click[0], col = click[1];
        int m = board.length, n = board[0].length;
        
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        // click on 'E'
        int count = 0;
        for (int i = Math.max(0, row - 1); i <= Math.min(m - 1, row + 1); i++) {
            for (int j = Math.max(0, col - 1); j <= Math.min(n - 1, col + 1); j++) {
                if (board[i][j] == 'M') {
                    count++;
                }
            }
        }
        if (count > 0) {
            board[row][col] = (char)(count + '0');
            return board; // You should only recursively reveal adjacent cells if the current cell is to reveal 'B'.
        } else {
            board[row][col] = 'B';
            for (int i = Math.max(0, row - 1); i <= Math.min(m - 1, row + 1); i++) {
                for (int j = Math.max(0, col - 1); j <= Math.min(n - 1, col + 1); j++) {
                    if (i == row && j == col) {
                        continue;
                    } 
                    if (board[i][j] == 'E') {
                        updateBoard(board, new int[]{i, j});
                    }
                }
            }
        }
        return board;
    }
}
