class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int len = nums.length;
        int prod = 1;
        int left = 0;
        int count = 0;
        
        for (int i = 0; i < len; i++) {
            if (nums[i] < k) {
                prod *= nums[i];
                while (prod >= k) {
                    prod /= nums[left];
                    left++;
                }
                // i - left + 1 是以nums[i]结尾的subarray的个数
                count += i - left + 1;
            } else {
                prod = 1;
                left = i + 1;
            }
        }
        return count;
    }
}
