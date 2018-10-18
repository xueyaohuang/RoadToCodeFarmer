class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] used = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (used[board[i][j] - '0']) {
                        return false;
                    } else {
                        used[board[i][j] - '0'] = true;
                    }
                }
            }
        }
        
        for (int j = 0; j < 9; j++) {
            boolean[] used = new boolean[10];
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    if (used[board[i][j] - '0']) {
                        return false;
                    } else {
                        used[board[i][j] - '0'] = true;
                    }
                }
            }
        }
        
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                boolean[] used = new boolean[10];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[row + i][col + j] != '.') {
                            if (used[board[row + i][col + j] - '0']) {
                                return false;
                            } else {
                                used[board[row + i][col + j] - '0'] = true;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    if (! set.add("row" + i + num) || ! set.add("col" + j + num) || ! set.add("cell" + i / 3 + j / 3 + num)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

