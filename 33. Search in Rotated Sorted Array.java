/*
The idea is that when rotating the array, there must be one half of the array that is still in sorted order.
1. 找到sorted half。如果nums[mid] >= nums[start]，从nums[start]到nums[mid]是sorted half；如果nums[mid] < nums[start]，从nums[mid]到nums[end]是sorted half
2. 找到target在sorted half的条件。对于nums[mid] >= nums[start]，这一条件是target >= nums[start] && target <= nums[mid]。对于nums[mid] < nums[start]，这一条件是target > nums[mid] && target <= nums[end]
*/
class Solution {
    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            } else {
                // 为了使start = mid + 1这一更新方式不变，target不能等于nums[mid]
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
        }
        return nums[start] == target ? start : -1;
    }
}
