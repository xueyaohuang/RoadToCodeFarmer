class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int area = 0;
        int[] heights = new int[col + 1];
        for (int i = 0; i < row; i++) {
            Stack<Integer> stack = new Stack<>();
            for (int j = 0; j <= col; j++) { 
                if (j < col) {
                    if (matrix[i][j] == '1') {
                        heights[j] += 1;
                    }
                    else {
                        heights[j] = 0;
                    }
                } 
                if (stack.isEmpty() || heights[j] >= heights[stack.peek()]) {
                    stack.push(j);
                }
                else {
                    while (!stack.isEmpty() && heights[j] < heights[stack.peek()]) {
                        int left = stack.pop();
                        int w = stack.isEmpty() ? j : j - stack.peek() - 1;
                        area = Math.max(area, heights[left] * w);
                    }
                    stack.push(j);
                }
            }
        }
        return area;
    }
}
