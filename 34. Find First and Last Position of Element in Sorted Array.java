class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = searchStart(nums, target);
        if (start == -1) {
            return new int[]{-1, -1};
        }
        int end = searchEnd(nums, target);
        return new int[]{start, end};
    }
    
    // search start 的时候，当nums[mid] == target时，要移动end，因为需要把搜索范围尽量往左边收缩
    private int searchStart(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
    
    // search end 的时候，当nums[mid] == target时，要移动start，因为需要把搜索范围尽量往右边收缩
    private int searchEnd(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}
