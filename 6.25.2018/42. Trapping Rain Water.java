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