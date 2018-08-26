class Solution {
    public int findMin(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (nums[end] < nums[mid]) {
                start = mid + 1;
            }
            else if (nums[end] > nums[mid]) {
                end = mid;
            }
            else {
                end--;
            }
        }
        return nums[start]; // 如果用while (start < end)，结束时start==end，所以返回nums[start]或是nums[end]都行
    }
}
// Would allow duplicates affect the run-time complexity? How and why?
// worst O(n) time complexity.
