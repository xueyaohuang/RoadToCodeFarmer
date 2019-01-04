/*
This is a typical Search problem, either by using DFS or BFS. Search rules:

If click on a mine ('M'), mark it as 'X', stop further search.
If click on an empty cell ('E'), depends on how many surrounding mine:
2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
*/

/*
If click on a mine ('M'), mark it as 'X', stop further search.
If click on an empty cell ('E'), depends on how many surrounding mine:
2.1 Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
2.2 No surrounding mine, mark it as 'B', continue search its 8 neighbors.
*/
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
        for (int i = Math.max(0, row -1); i <= Math.min(m - 1, row + 1); i++) {
            for (int j = Math.max(0, col -1); j <= Math.min(n - 1, col + 1); j++) {
                if (board[i][j] == 'M') {
                    count++;
                }
            }
        }
        if (count > 0) {
            board[row][col] = (char)(count + '0');
            return board;
        } else {
            board[row][col] = 'B';
            for (int i = Math.max(0, row -1); i <= Math.min(m - 1, row + 1); i++) {
                for (int j = Math.max(0, col -1); j <= Math.min(n - 1, col + 1); j++) {
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
