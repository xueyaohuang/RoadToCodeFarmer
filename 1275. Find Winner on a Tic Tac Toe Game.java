/*
We should keep code reusability in mind. 
If you just think of tic-tac toe as a 3x3 grid you are doing it wrong. 
The interviewer would ask you to build a tic-tac-toe for 3x3 grid, you write you code and check for all conditions and it works great.
Then the interviewer takes his turn to play :) He'd tell you that the requirements have changed and now the game is a 4x4 grid instead, or 100x100.
How would you do it?

So we should come up with tic-tac-toe that works for n x n grid in the first palce.
*/
// Use 1 for play A, -1 for player B
class Solution {
    public String tictactoe(int[][] moves) {
        int n = 3;
        int[] rows = new int[n];
        int[] cols = new int[n];
        int dia1 = 0;
        int dia2 = 0;
        int curPlayer = 1;
        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            rows[row] += curPlayer;
            cols[col] += curPlayer;
            if (row == col) {
                dia1 += curPlayer;
            }
            if (row + col == n - 1) {
                dia2 += curPlayer;
            }
            if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(dia1) == n || Math.abs(dia2) == n) {
                return curPlayer == 1 ? "A" : "B";
            }
            curPlayer = -curPlayer;
        }
        return moves.length < n * n ? "Pending" : "Draw";
    }
}

class Solution {
    public String tictactoe(int[][] moves) {
        int[] row = new int[3];
        int[] col = new int[3];
        int dia = 0;
        int antiDia = 0;
        for (int i = 0; i < moves.length; i++) {
            int num = i % 2 == 0 ? 1 : -1;
            int r = moves[i][0];
            int c = moves[i][1];
            row[r] += num;
            col[c] += num;
            if (r == c) {
                dia += num;
            }
            if (r + c == 2) {
                antiDia += num;
            }
            if (Math.abs(row[r]) == 3 || Math.abs(col[c]) == 3 || Math.abs(dia) == 3 || Math.abs(antiDia) == 3) {
                return i % 2 == 0 ? "A" : "B";
            }
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }
}
