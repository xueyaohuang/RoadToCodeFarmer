public class Solution {
    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        // write your code here
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int[][] hl = new int[m][n];
        int[][] hr = new int[m][n];
        int[][] vu = new int[m][n];
        int[][] vd = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j - 1] == 'W') {
                    hl[i][j] = 0;
                } else {
                    hl[i][j] = hl[i][j - 1];
                    if (grid[i][j - 1] == 'E') {
                        hl[i][j]++;
                    }
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (j == n - 1 || grid[i][j + 1] == 'W') {
                    hr[i][j] = 0;
                } else {
                    hr[i][j] = hr[i][j + 1];
                    if (grid[i][j + 1] == 'E') {
                        hr[i][j]++;
                    }
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (i == 0 || grid[i - 1][j] == 'W') {
                    vu[i][j] = 0;
                } else {
                    vu[i][j] = vu[i - 1][j];
                    if (grid[i - 1][j] == 'E') {
                        vu[i][j]++;
                    }
                }
            }
            for (int i = m - 1; i >= 0; i--) {
                if (i == m - 1 || grid[i + 1][j] == 'W') {
                    vd[i][j] = 0;
                } else {
                    vd[i][j] = vd[i + 1][j];
                    if (grid[i + 1][j] == 'E') {
                        vd[i][j]++;
                    }
                }
            }
        }
        
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    res = Math.max(res, hl[i][j] + hr[i][j] + vu[i][j] + vd[i][j]);
                }
            }
        }
        return res;
    }
}

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        int rowCount = 0;
        int[] colCount = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || grid[i][j-1] == 'W') {
                    rowCount = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            rowCount++;
                        }
                    }
                }
                if (i == 0 || grid[i-1][j] == 'W') {
                    colCount[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            colCount[j]++;
                        }
                    }
                }
                if (grid[i][j] == '0') {
                    max = Math.max(max, rowCount + colCount[j]);
                }
            }
        }
        return max;
    }
}
