class Solution {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<int[]> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, i, 0, Integer.MIN_VALUE);
            dfs(matrix, atlantic, i, n - 1, Integer.MIN_VALUE);
        }
        
        for (int j = 0; j < n; j++) {
            dfs(matrix, pacific, 0, j, Integer.MIN_VALUE);
            dfs(matrix, atlantic, m - 1, j, Integer.MIN_VALUE);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }  
            }
        }
        
        return res;
    }
    
    private void dfs(int[][] matrix, boolean[][] canReach, int i, int j, int height) {
        if (invalid(matrix, canReach, i, j, height)) {
            return;
        }
        canReach[i][j] = true;
        dfs(matrix, canReach, i - 1, j, matrix[i][j]);
        dfs(matrix, canReach, i + 1, j, matrix[i][j]);
        dfs(matrix, canReach, i, j - 1, matrix[i][j]);
        dfs(matrix, canReach, i, j + 1, matrix[i][j]);
    }
    
    private boolean invalid(int[][] matrix, boolean[][] canReach, int i, int j, int height) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || canReach[i][j] || height > matrix[i][j]) {
            return true;
        }
        return false;
    }
}
