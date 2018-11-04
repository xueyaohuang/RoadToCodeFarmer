class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (existHere(board, word, i, j, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean existHere(char[][] board, String word, int i, int j, int idx, boolean[][] visited) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(idx)) {
            return false;
        }
        if (idx == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        if (existHere(board, word, i + 1, j, idx + 1, visited) ||
            existHere(board, word, i - 1, j, idx + 1, visited) ||
            existHere(board, word, i, j + 1, idx + 1, visited) ||
            existHere(board, word, i, j - 1, idx + 1, visited)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }
}
