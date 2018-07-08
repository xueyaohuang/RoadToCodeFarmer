class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int area = 0;
        for (int i = 0; i <= heights.length;) {
            int h = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i++);
            }
            else {
                int left = stack.pop();
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                area = Math.max(area, heights[left] * w);
            }
        }
        return area;
    }
}
// stack stores the indexes not heights[i].
// Do not increase i in the for loop, increas i only when push index into stack.
