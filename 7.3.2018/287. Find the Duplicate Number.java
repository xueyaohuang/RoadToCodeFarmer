class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0;
        int slow = 0;
        int len = nums.length;
        while (fast < len && nums[fast] < len) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (slow == fast) {
                fast = 0;
                while (fast != slow) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return slow;
            }
        }
        return -1;
    }
}

class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count <= mid) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return low;
    }
}
