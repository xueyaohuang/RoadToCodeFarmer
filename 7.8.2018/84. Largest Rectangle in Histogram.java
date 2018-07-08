class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int area = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            }
            else {
                while (!stack.isEmpty() && h < heights[stack.peek()]) {
                    int left = stack.pop();
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    area = Math.max(area, heights[left] * w);
                }
                stack.push(i);
            }
        }
        return area;
    }
}
// stack stores the indexes not heights[i].

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        int[] left = new int[len]; //记录当前i，到左边，第一个比自己低的位置
        int[] right = new int[len];//记录当前i，到右边，第一个比自己低的位置
        left[0] = -1;
        right[len - 1] = len;
        for (int i = 1; i < len; i++) {
            int pre = i - 1;
            while (pre >= 0 && heights[pre] >= heights[i]) {
                pre = left[pre]; // important
            }
            left[i] = pre;
        }
        for (int i = len - 2; i >= 0; i--) {
            int pos = i + 1;
            while (pos < len && heights[pos] >= heights[i]) {
                pos = right[pos];
            }
            right[i] = pos;
        }
        int area = 0;
        for (int i = 0; i < len; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }
}
