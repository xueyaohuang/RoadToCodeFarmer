class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] cache = new int[matrix.length][matrix[0].length];
        int max = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, dfs(matrix, cache, i, j, Integer.MAX_VALUE));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int[][]cache, int i, int j, int pre) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] >= pre) {
            return 0;
        }
        if (cache[i][j] > 0) {
            return cache[i][j];
        }
        int max = 1;
        max = Math.max(max, dfs(matrix, cache, i + 1, j, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, cache, i - 1, j, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, cache, i, j + 1, matrix[i][j]) + 1);
        max = Math.max(max, dfs(matrix, cache, i, j - 1, matrix[i][j]) + 1);
        cache[i][j] = max;
        return max;
    }
}

class Solution {
    int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int max = 1;
        int[][] cache = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                max = Math.max(max, dfs(matrix, i, j, cache));
            }
        }
        return max;
    }
    
    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        int max = 1;
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] <= matrix[i][j]) {
                continue;
            }
            int len = 1 + dfs(matrix, x, y, cache);
            max = Math.max(max, len);
        }
        cache[i][j] = max;
        return max;
    }
}
