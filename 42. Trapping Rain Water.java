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
        // l < r 或者 l <= r都可以，因为移动l，r为了找更高的wall，所以最终l==r的时候，停在最高的wall的位置，这时的trap的water是0.
        while (l <= r) {
            leftHeight = Math.max(leftHeight, height[l]);
            rightHeight = Math.max(rightHeight, height[r]);
            if (leftHeight < rightHeight) { // always looking for a higher wall
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

class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int lowerHeight = 0;
        int left = 0;
        int right = height.length - 1;
        int area = 0;
        while (left < right) {
            while (left < right && height[left] <= lowerHeight) {
                area += lowerHeight - height[left];
                left++;
            }
            while (left < right && height[right] <= lowerHeight) {
                area += lowerHeight - height[right];
                right--;
            }
            lowerHeight = Math.min(height[left], height[right]);
        }
        return area;
    }
}
