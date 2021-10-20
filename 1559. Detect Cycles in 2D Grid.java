/*
At each point (i, j) in grid, do following:
1. Start DFS from (i, j).
2. Only visit a point if it has same character as starting position
3. If we reach a visited point again, and that point is not current point's parent, return true.

The algorithm is taken from Detect cycle in a undirect graph:
https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/261.%20Graph%20Valid%20Tree.java
*/
class Solution {
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public boolean containsCycle(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (hasCycle(grid, visited, i, j, grid[i][j], -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean hasCycle(char[][] grid, int[][] visited, int i, int j, char c, int parent) {
        if (!isValid(grid, i, j) || grid[i][j] != c || visited[i][j] == 2) {
            return false;
        }
        visited[i][j] = 1;
        for (int[] dir : dirs)  {
            int x = i + dir[0];
            int y = j + dir[1];
            if (isValid(grid, x, y)) {
                if (visited[x][y] == 1) {
                    //  If we reach a visited point again, and that point is not current point's parent, return true.
                    if (x * grid[0].length + y != parent) { // for a m * n grid, encode the coordinate by i * n + j
                        return true;
                    }
                } else {
                    if (hasCycle(grid, visited, x, y, c, i * grid[0].length + j)) {
                        return true;
                    }
                }
            }
        }
        visited[i][j] = 2;
        return false;
    }
    
    private boolean isValid(char[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length;
    }
}
