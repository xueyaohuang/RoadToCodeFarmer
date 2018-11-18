// 有4种情况是invalid的
// 1. numX < numO || numX - numO > 1
// 2. xwin && owin
// 3. xwin && (numX == numO)
// 4. owin && (numX > numO)
class Solution {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length != 3) {
            return false;
        }
        int numX = 0, numO = 0;
        int diaX = 0, diaO = 0;
        int antiDiaX = 0, antiDiaO = 0;
        boolean rowX = false, rowO = false;
        boolean colX = false, colO = false;
        boolean xwin = false, owin = false;
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'X') {
                    numX++;
                }
                if (board[i].charAt(j) == 'O') {
                    numO++;
                }
            }
            if (board[i].charAt(i) == 'X') {
                diaX++;
            }
            if (board[i].charAt(i) == 'O') {
                diaO++;
            }
            if (board[i].charAt(board.length - 1 - i) == 'X') {
                antiDiaX++;
            }
            if (board[i].charAt(board.length - 1 - i) == 'O') {
                antiDiaO++;
            }
            if (board[i].equals("XXX")) {
                rowX = true;
            }
            if (board[i].equals("OOO")) {
                rowO = true;
            }
            if (board[0].charAt(i) == 'X' && board[1].charAt(i) == 'X' && board[2].charAt(i) == 'X') {
                colX = true;
            }
            if (board[0].charAt(i) == 'O' && board[1].charAt(i) == 'O' && board[2].charAt(i) == 'O') {
                colO = true;
            }            
        }
        
        if (numX < numO || numX - numO > 1) {
            return false;
        }
        
        xwin = rowX || colX || diaX == 3 || antiDiaX == 3;
        owin = rowO || colO || diaO == 3 || antiDiaO == 3;
        
        if (xwin && owin) {
            return false;
        }
        
        if (xwin && (numX == numO)) {
            return false;
        }
        
        if (owin && (numX > numO)) {
            return false;
        }

        return true;
    }
}
