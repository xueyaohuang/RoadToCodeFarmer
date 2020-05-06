class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return false;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid;
        while (start < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }
            // this breaks the invariant, so in order to keep two parts ordered,
            // increase start and decrease end
            if (nums[start] == nums[mid] && nums[mid] == nums[end]){
                start++;
                end--;
            } else if (nums[mid] >= nums[start]) {
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        } 
        if (nums[start] == target) {
            return true;
        }
        return false;
    }
}
