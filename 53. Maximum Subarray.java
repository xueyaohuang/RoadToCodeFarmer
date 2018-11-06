class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int curEndHere = nums[0];
        int res = nums[0];
        
        for (int i = 1; i < len; i++) {
            curEndHere = Math.max(nums[i], nums[i] + curEndHere);
            res = Math.max(res, curEndHere);
        }
        return res;
    }
}
