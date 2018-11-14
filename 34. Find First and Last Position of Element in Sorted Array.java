class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = 0;
        int end = nums.length - 1;
        int[] bound = new int[2];
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] >= target) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }
        if (nums[start] == target) {
            bound[0] = start;
        } else {
            return new int[]{-1, -1};
        }
        
        start = 0;
        end = nums.length - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (nums[mid] <= target) {
                start = mid;
            }
            else {
                end = mid - 1;
            }
        }
        if (nums[start] == target) {
            bound[1] = start;
        } else {
            return new int[]{-1, -1};
        }
        return bound;
    }
}
