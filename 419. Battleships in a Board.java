// 两种方法都是只数最左上角
// 把不能加的排除，剩下的加，快一些。
class Solution {
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (i > 0 && board[i - 1][j] == 'X') {
                    continue;
                }
                if (j > 0 && board[i][j - 1] == 'X') {
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}

// 数哪些能加，麻烦一点
class Solution {
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            if (i == 0 && board[i][0] == 'X') {
                count++;
            }
            if (i > 0 && board[i][0] == 'X' && board[i - 1][0] == '.') {
                count++;
            }
        }
        for (int j = 1; j < board[0].length; j++) {
            if (board[0][j] == 'X' && board[0][j - 1] == '.') {
                count++;
            }
        }
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 'X' && board[i][j - 1] == '.' && board[i - 1][j] == '.') {
                    count++;
                }
            }
        }
        return count;
    }
}
