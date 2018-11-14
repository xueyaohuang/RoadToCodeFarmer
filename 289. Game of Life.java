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
