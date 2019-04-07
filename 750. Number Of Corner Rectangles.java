class Solution {
    public int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m - 1; i++) {
            for (int j = i + 1; j < m; j++) {
                int count = 0;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) {
                        count++;
                    }
                }
                if (count > 1) {
                    res += (count - 1) * count / 2;
                }
            }
        }
        return res;
    }
}
