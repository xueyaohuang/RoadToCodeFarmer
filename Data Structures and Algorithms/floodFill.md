# Flood Fill

1. 实际上就是DFS在二维matrix上的应用。通常模板就是一个主函数有两层for loop，内层检查条件，复合后进入helper function。 

2. Helper function应用DFS，上来就是recursion的终结条件，终结条件通常有5个条件或起来，比如： 
```
if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
    return;
}
```
3. 前面四个条件是检查是否出了matrix的边界，最后一个跟matrix[i][j]有关。 

4. 之后把matrix[i][j]改一下之类的操作，然后进入上下左右四个方向上的recursion。

```
folldFill(grid, i - 1, j);
folldFill(grid, i + 1, j);
folldFill(grid, i, j - 1);
folldFill(grid, i, j + 1);
```


___例子___

* 200 Number of Islands

```
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    removeOnes(grid, i, j);
                }
            }
        }
        return count;
    }
    
    private void removeOnes(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        removeOnes(grid, i - 1, j);
        removeOnes(grid, i + 1, j);
        removeOnes(grid, i, j - 1);
        removeOnes(grid, i, j + 1);
    }
}
```

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
