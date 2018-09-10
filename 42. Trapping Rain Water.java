// Instead of calculating area by height*width, we can think it in a cumulative way.
// In other words, sum water amount of each bin(width=1).
// Search from left to right and maintain a max height of left and right separately, which is like a one-side wall of partial container. 
// Fix the higher one and flow water from the lower part. 
// For example, if current height of left is lower, we fill water in the left bin. Until left meets right, we filled the whole container.

class Solution {
    public int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        int leftHeight = 0;
        int rightHeight = 0;
        while (l < r) {
            leftHeight = Math.max(leftHeight, height[l]);
            rightHeight = Math.max(rightHeight, height[r]);
            if (leftHeight < rightHeight) {
                max += leftHeight - height[l];
                l++;
            }
            else {
                max += rightHeight - height[r];
                r--;
            }
        }
        return max;
    }
}
