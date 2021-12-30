class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int maxHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxHere = maxHere > 0 ? maxHere + nums[i] : nums[i];
            maxSoFar = Math.max(maxSoFar, maxHere);
        }
        return maxSoFar;
    }
}

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
