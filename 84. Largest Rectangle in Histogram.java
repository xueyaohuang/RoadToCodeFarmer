// stack stores the indexes not heights[i].
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n;) {
            int h = i == n ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int height = heights[stack.pop()];
                int right = i - 1;
                int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                maxArea = Math.max(maxArea, height * (right - left + 1));
            }
        }
        return maxArea;
    }
}

class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        int len = heights.length;
        for (int i = 0; i <= len; i++) {
            int h = i == len ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && h < heights[stack.peek()]) {
                    int left = stack.pop();
                    int width = 0;
                    if (stack.isEmpty()) {
                        width = i;
                    } else {
                        width = i - stack.peek() - 1;
                    }
                    area = Math.max(area, heights[left] * width);
                }
                stack.push(i);
            }
        }
        return area;
    }
}

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
