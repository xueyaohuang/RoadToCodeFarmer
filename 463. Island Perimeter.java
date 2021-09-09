class Solution {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += 4 - getNumOfAdjcentIsland(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private int getNumOfAdjcentIsland(int[][] grid, int i, int j) {
        int count = 0;
        if (i > 0 && grid[i - 1][j] == 1) {
            count++;
        }
        if (i < grid.length - 1 && grid[i + 1][j] == 1) {
            count++;
        }
        if (j > 0 && grid[i][j - 1] == 1) {
            count++;
        }
        if (j < grid[0].length - 1 && grid[i][j + 1] == 1) {
            count++;
        }
        return count;
    }
}

class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int perimeter = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += countSides(grid, i, j);
                }
            }
        }
        return perimeter;
    }
    private int countSides(int[][] grid, int i, int j) {
        int count = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                count++;
            }           
        }
        return count;
    }
}
class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int perimrter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    perimrter += 4;
                    if (i > 0 && grid[i - 1][j] == 1) {
                        perimrter -= 2;
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        perimrter -= 2;
                    }
                }
            }
        }
        return perimrter;
    }
}

class Solution {
    private int res;
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    floodFill(grid, i, j);
                    
                }
            }
        }
        return res;
    }
    
    private void floodFill(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return;
        }
        grid[i][j] = -1;
        if (i == 0 || grid[i - 1][j] == 0) {
            res++;
        }
        if (i == grid.length - 1 || grid[i + 1][j] == 0) {
            res++;
        }
        if (j == 0 || grid[i][j - 1] == 0) {
            res++;
        }
        if (j == grid[0].length - 1 || grid[i][j + 1] == 0) {
            res++;
        }
        floodFill(grid, i - 1, j);
        floodFill(grid, i + 1, j);
        floodFill(grid, i, j - 1);
        floodFill(grid, i, j + 1);
    }
}
