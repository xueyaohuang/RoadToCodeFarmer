class Solution {
    int count;
    public int totalNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] dia = new boolean[2 * n - 1];
        boolean[] antiDia = new boolean[2 * n - 1];
        backtracking(col, dia, antiDia, 0, n);
        return count;
    }
    
    private void backtracking(boolean[] col, boolean[] dia, boolean[] antiDia, int row, int n) {
        if (row == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (col[i] || dia[row + i] || antiDia[n - 1 - i + row]) {
                continue;
            }
            col[i] = true;
            dia[row + i] = true;
            antiDia[n - 1 - i + row] = true;
            backtracking(col, dia, antiDia, row + 1, n);
            col[i] = false;
            dia[row + i] = false;
            antiDia[n - 1 - i + row] = false;
        }
    }
}
