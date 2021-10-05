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

class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && isUpperLeft(board, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean isUpperLeft(char[][] board, int i, int j) {
        return (i == 0 || board[i - 1][j] == '.') && (j == 0 || board[i][j - 1] == '.');
    }
}

// dfs, 没有用到形状是1*k这个info
class Solution {
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && !visited[i][j]) {
                    count++;
                    dfs(board, i, j, visited);
                }
            }
        }
        return count;
    }
    
    private void dfs(char[][] board, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.' || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        dfs(board, i + 1, j, visited);
        dfs(board, i - 1, j, visited);
        dfs(board, i, j + 1, visited);
        dfs(board, i, j - 1, visited);
    }
}
