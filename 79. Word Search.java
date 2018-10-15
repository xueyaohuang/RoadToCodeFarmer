class Solution {
    private boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (hereExisted(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hereExisted(char[][] board, String word, int row, int col, int index) {
        if (index >= word.length()) {
            return true;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] == true || board[row][col] != word.charAt(index)) {
            return false;
        }
        visited[row][col] = true;
        if (hereExisted(board, word, row + 1, col, index + 1) ||
            hereExisted(board, word, row - 1, col, index + 1) ||
            hereExisted(board, word, row, col + 1, index + 1) ||
            hereExisted(board, word, row, col - 1, index + 1)) {
            return true;
        }
        // 没有return true，表示这个char没有用，set visited[i][j] = false
        visited[row][col] = false;
        return false;
    }
}
