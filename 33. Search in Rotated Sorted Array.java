class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[start]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                }
                else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) {
            return start;
        }
        return -1;
    }
}
