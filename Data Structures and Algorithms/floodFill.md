


___例子___

* 200 

* 463 Island Perimeter

```
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
```
