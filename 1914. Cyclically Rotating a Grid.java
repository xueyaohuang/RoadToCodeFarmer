// O(mn)
class Solution {
    public int[][] rotateGrid(int[][] grid, int k) {
        int top = 0, down = grid.length - 1, left = 0, right = grid[0].length - 1;
        while (top < down && left < right) {
            int curK = k % ((down - top + 1) * 2 + (right - left - 1) * 2);
            //1st pass: Loop through current layer to add time into list
            //Both Time and Space complexity will be O(number of item in curr layer)<O(m+n)
            LinkedList<Integer> list = new LinkedList<>();
            //up row
            for (int j = left; j < right; j++) {
                list.add(grid[top][j]);
            }
            //right col
            for (int i = top; i < down; i++) {
                list.add(grid[i][right]);
            }
            //bottom row
            for (int j = right; j > left; j--) {
                list.add(grid[down][j]);
            }
            //left col
            for (int i = down; i > top; i--) {
                list.add(grid[i][left]);
            }
            //Remove head and add to tail O(local_k) time < O(m+n) time and space
            while (curK > 0) {
                list.add(list.poll());
                curK--;
            }

            //2nd pass to update item O(m+n) time and space
            //up row
            for (int j = left; j < right; j++) {
                grid[top][j] = list.poll();
            }
            //right col
            for (int i = top; i < down; i++) {
                grid[i][right] = list.poll();
            }
            //bottom row
            for (int j = right; j > left; j--) {
                grid[down][j] = list.poll();
            }
            //left col
            for (int i = down; i > top; i--) {
                grid[i][left] = list.poll();
            }
            top++;
            down--;
            left++;
            right--;
        }
        return grid;
    }
}
