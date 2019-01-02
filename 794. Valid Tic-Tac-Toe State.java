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

// invalid states:
// 1. numx < numo || numx - numo > 1
// 2. numx == numo && xwin
// 3. numx > numo && owin
// 4. xwin && owin
class Solution {
    public boolean validTicTacToe(String[] board) {
        if (board == null || board.length == 0) {
            return false;
        }
        int numx = 0, numo = 0;
        boolean xwin = false, owin = false;
        for (String row : board) {
            int countx = 0, counto = 0;
            for (int i = 0; i < 3; i++) {
                if (row.charAt(i) == 'X') {
                    countx++;a
                } else if (row.charAt(i) == 'O') {
                    counto++;
                }
            }
            numx += countx;
            numo += counto;
            if (countx == 3) {
                xwin = true;
            }
            if (counto == 3) {
                owin = true;
            }
        }

        if (!xwin) {
            if (board[0].charAt(0) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(2) == 'X') {
                xwin = true;
            }
            if (board[0].charAt(2) == 'X' && board[1].charAt(1) == 'X' && board[2].charAt(0) == 'X') {
                xwin = true;
            }
        }
        if (!owin) {
            if (board[0].charAt(0) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(2) == 'O') {
                owin = true;
            }
            if (board[0].charAt(2) == 'O' && board[1].charAt(1) == 'O' && board[2].charAt(0) == 'O') {
                owin = true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int countx = 0, counto = 0;
            for (int j = 0; j < 3; j++) {
                if (board[j].charAt(i) == 'X') {
                    countx++;
                } else if (board[j].charAt(i) == 'O') {
                    counto++;
                }
            }
            if (countx == 3) {
                xwin = true;
            }
            if (counto == 3) {
                owin = true;
            }
        }
        if (numx < numo || numx - numo > 1) {
            return false;
        }
        if (numx == numo && xwin) {
            return false;
        }
        if (numx > numo && owin) {
            return false;
        }
        if (xwin && owin) {
            return false;
        }

        return true;
    }
}
