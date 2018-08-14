class TicTacToe {

    private int[] rowCount;
    private int[] colCount;
    private int len;
    private int dia;
    private int antiDia;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        rowCount = new int[n];
        colCount = new int[n];
        len = n;
        dia = 0;
        antiDia = 0;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int addOne = player == 1 ? 1 : -1;
        rowCount[row] += addOne;
        colCount[col] += addOne;
        if (row == col) {
        	dia += addOne;
        }
        if (row + col == len - 1) {
            antiDia += addOne;
        }
        if (Math.abs(rowCount[row]) == len || Math.abs(colCount[col]) == len || Math.abs(dia) == len || Math.abs(antiDia) == len) {
        	return player;
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */

class TicTacToe {

    private int[][] board;
    private int len;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        len = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        int countRow = 0;
        int countCol = 0;
        int countDia1 = 0;
        int countDia2 = 0;
        for (int i = 0; i < len; i++) {
        	if (board[i][col] == player) {
        		countRow++;
        	}
        }
        if (countRow == len) {
        	return player;
        }

        for (int j = 0; j < len; j++) {
        	if (board[row][j] == player) {
        		countCol++;
        	}
        }
        if (countCol == len) {
        	return player;
        }

        if (row == col) {
        	for (int i = 0; i < len; i++) {
        		if (board[i][i] == player) {
        		    countDia1++;
        	    }
        	}
        }
        if (countDia1 == len) {
        	return player;
        }
        
        if (row + col == len - 1) {
            for (int i = 0; i < len; i++) {
                if (board[i][len -1 - i] == player) {
                    countDia2++;
                }
            }
        }
        if (countDia2 == len) {
        	return player;
        }
        
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
