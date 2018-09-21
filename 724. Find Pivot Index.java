class Solution {
    public int pivotIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        int left = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            if (left == sum - nums[i] - left) {
                return i;
            }
            left += nums[i];
        }
        return -1;
    }
}
