/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
    public Node construct(int[][] grid) {
        if (grid == null) {
            return null;
        }
        int m = grid.length;
        int n = grid[0].length;
        return constructHelper(grid, 0, m - 1, 0, n - 1);
    }
    
    private Node constructHelper(int[][] grid, int rowStart, int rowEnd, int colStart, int colEnd) {
        Node node = new Node();
        int flag = grid[rowStart][colStart];
        
        for (int i = rowStart; i <= rowEnd; i++) {
            for (int j = colStart; j <= colEnd; j++) {
                if (grid[i][j] != flag) {
                    int rowHalf = (rowStart + rowEnd) / 2;
                    int colHalf = (colStart + colEnd) / 2;
                    node.topLeft = constructHelper(grid, rowStart, rowHalf, colStart, colHalf);
                    node.topRight = constructHelper(grid, rowStart, rowHalf, colHalf + 1, colEnd);
                    node.bottomLeft = constructHelper(grid, rowHalf + 1, rowEnd, colStart, colHalf);
                    node.bottomRight = constructHelper(grid, rowHalf + 1, rowEnd, colHalf + 1, colEnd);
                    return node;
                }
            }
        }
        node.isLeaf = true;
        node.val = flag == 1 ? true : false;
        return node;
    }
}
