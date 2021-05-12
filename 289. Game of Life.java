/*
   current  next
0  die      die
1  live     die
2  die      live
3  live     live
*/
class Solution {
    public void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = tempState(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((board[i][j] & 2) > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
    
    private int tempState(int[][] board, int x, int y) {
        int currentState = board[x][y];
        int count = 0;
        int res = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
                    continue;
                }
                if (i == x && j == y) {
                    continue;
                }
                if ((board[i][j] & 1) == 1) {
                    count++;
                }
            }
        }
        if (currentState == 1) {
            if (count < 2) {
                res = 1;
            } else if (count > 3) {
                res =  1;
            } else {
                res = 3;
            }
        }
        if (currentState == 0) {
            if (count == 3) {
                res = 2;
            }
        }
        return res;
    }
}

class Solution {
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = numOfLives(board, i, j);
                // 下一时刻的情况用board[i][j] >>= 1，如果下一状态是0，不用变，高位的0自然移到下一状态
                // 只用考虑下一状态是1的情况，分下面2个if来表示
                if (board[i][j] == 1 && (lives == 2 || lives == 3)) {
                    // board[i][j]改为2bits，高位代表下一状态，低位代表当前状态
                    board[i][j] = 3;
                }             
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }       
    }   
    
    private int numOfLives(int[][] board, int i, int j) {
        int lives = 0;
        int m = board.length, n = board[0].length;
        int xStart = Math.max(0, i - 1), xEnd = Math.min(m -1, i + 1);
        int yStart = Math.max(0, j - 1), yEnd = Math.min(n - 1, j + 1);
        for (int x = xStart; x <= xEnd; x++) {
            for (int y = yStart; y <= yEnd; y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1; // 不算自己的死活
        return lives;
    }
}
