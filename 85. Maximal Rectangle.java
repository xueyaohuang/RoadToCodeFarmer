class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int area = 0;
        int[] heights = new int[n + 1];
        for (int i = 0; i < m; i++) {
            Deque<Integer> stack = new ArrayDeque<>();
            for (int j = 0; j <= n; j++) { 
                if (j < n) {
                    heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
                } 
                while (!stack.isEmpty() && heights[j] < heights[stack.peek()]) {
                    int h = heights[stack.pop()];
                    int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                    int right = j - 1;
                    area = Math.max(area, h * (right - left + 1));
                }
                stack.push(j);
            }
        }
        return area;
    }
}
